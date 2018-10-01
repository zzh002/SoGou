package com.spider.demo.sogou;

import java.io.*;
import java.util.*;

/**
 * @author tyoui
 * 解析搜狗文件
 */
public class SoGouToTxT {
    private ByteArrayOutputStream output = new ByteArrayOutputStream();

    /**
     * 读入文件
     *
     * @param filePath       文件的路径
     * @param targetFilePath 输出的路径
     * @param isAppend       是否追加
     */
    public void toTxt(String filePath, String targetFilePath, boolean isAppend) {
        File input = new File(filePath);
        FileInputStream in;
        SouGouModel model;
        try {
            in = new FileInputStream(input);
            model = readModel(in);
            if (model == null)
                return;
            writeToTargetFile(model, targetFilePath, isAppend);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 讲搜狗文件内容转化成对象
     *
     * @param in 搜狗文件的输入流
     * @return 对象
     */
    private SouGouModel readModel(InputStream in) {
        SouGouModel model = new SouGouModel();
        DataInputStream input = new DataInputStream(in);
        int read;
        try {
            byte[] bytes = new byte[4];
            input.readFully(bytes);
            assert (bytes[0] == 0x40 && bytes[1] == 0x15 && bytes[2] == 0 && bytes[3] == 0);
            input.readFully(bytes);
            int flag = bytes[0];
            assert (bytes[1] == 0x43 && bytes[2] == 0x53 && bytes[3] == 0x01);
            int[] reads = new int[]{8};
            model.setName(readString(input, 0x130, reads));
            model.setType(readString(input, 0x338, reads));
            read = reads[0];
            input.skip(0x1540 - read);
            read = 0x1540;
            input.readFully(bytes);
            read += 4;
            assert (bytes[0] == (byte) 0x9D && bytes[1] == 0x01 && bytes[2] == 0 && bytes[3] == 0);
            bytes = new byte[128];
            Map<Integer, String> pyMap = new LinkedHashMap<>();
            while (true) {
                int mark = readUnsignedShort(input);
                int size = input.readUnsignedByte();
                input.skip(1);
                read += 4;
                assert (size > 0 && (size % 2) == 0);
                input.readFully(bytes, 0, size);
                read += size;
                String py = new String(bytes, 0, size, "UTF-16LE");
                pyMap.put(mark, py);
                if ("zuo".equals(py))
                    break;
            }
            if (flag == 0x44) {
                input.skip(0x2628 - read);
            } else if (flag == 0x45) {
                input.skip(0x26C4 - read);
            } else {
                throw new RuntimeException("出现意外");
            }
            StringBuilder buffer = new StringBuilder();
            Map<String, List<String>> wordMap = new LinkedHashMap<>();
            while (true) {
                int size = readUnsignedShort(input);
                if (size < 0)
                    break;
                int count = readUnsignedShort(input);
                int len = count / 2;
                assert (len * 2 == count);
                buffer.setLength(0);
                for (int i = 0; i < len; i++) {
                    int key = readUnsignedShort(input);
                    buffer.append(pyMap.get(key)).append("'");
                }
                buffer.setLength(buffer.length() - 1);
                String py = buffer.toString();
                List<String> list = wordMap.get(py);
                if (list == null) {
                    list = new ArrayList<>();
                    wordMap.put(py, list);
                }
                for (int i = 0; i < size; i++) {
                    count = readUnsignedShort(input);
                    if (count > bytes.length)
                        bytes = new byte[count];
                    input.readFully(bytes, 0, count);
                    String word = new String(bytes, 0, count, "UTF-16LE");
                    input.skip(12);
                    list.add(word);
                }
            }
            model.setWordMap(wordMap);
            return model;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 将对象写到文本
     *
     * @param souGouModel    搜狗对象
     * @param targetFilePath 写入文件的路径
     * @param isAppend       是否追加
     * @throws IOException 异常
     */
    private void writeToTargetFile(SouGouModel souGouModel, String targetFilePath, boolean isAppend)
            throws IOException {
        FileOutputStream out = new FileOutputStream(targetFilePath, isAppend);
        Map<String, List<String>> words = souGouModel.getWordMap();
        Set<Map.Entry<String, List<String>>> set = words.entrySet();
        Iterator<Map.Entry<String, List<String>>> iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, List<String>> entry = iterator.next();
            List<String> list = entry.getValue();
            for (String word : list) {
                out.write((entry.getKey() + "\t").getBytes());
                out.write((word + "\n").getBytes());
            }
        }
        out.close();
    }

    /**
     * 转为short型整数:
     *
     * @param in 输入流
     * @return 最小数
     * @throws IOException 异常
     */
    private int readUnsignedShort(InputStream in) throws IOException {
        int ch1 = in.read();
        int ch2 = in.read();
        if ((ch1 | ch2) < 0)
            return Integer.MIN_VALUE;
        return (ch2 << 8) + ch1;
    }

    private String readString(DataInputStream input, int pos, int[] reads) throws IOException {
        int read = reads[0];
        input.skip(pos - read);
        read = pos;
        output.reset();
        while (true) {
            int c1 = input.read();
            int c2 = input.read();
            read += 2;
            if (c1 == 0 && c2 == 0)
                break;
            else {
                output.write(c1);
                output.write(c2);
            }
        }
        reads[0] = read;
        return new String(output.toByteArray(), "UTF-16LE");
    }
}
