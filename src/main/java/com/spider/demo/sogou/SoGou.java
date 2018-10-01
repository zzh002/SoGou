package com.spider.demo.sogou;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author tyoui
 * 爬虫搜狗
 */
public class SoGou extends SoGouToTxT {
    private Set<String> set = new HashSet<>();
    private String loadPath = "./sogou_url.txt";

    /**
     * 分析搜狗爬取的数据
     *
     * @throws IOException 异常
     */
    private void analysisSoGou() throws IOException {
        String root = "https://pinyin.sogou.com/dict/cate/index/";
        for (int i = 0; i <= 437; i++) {
            String u = root + i + "/default/";
            int f = 1;
            for (int flag = 1; flag <= f; flag++) {
                Document document = Jsoup.connect(u + flag).get();
                if (flag == 1) {
                    String num = document.getElementsByClass("cate_title").text();
                    int start = num.indexOf("共有") + 2;
                    int end = num.indexOf("个词库");
                    f = Integer.parseInt(num.substring(start, end)) / 10 + 1;
                }
                Elements name = document.getElementsByClass("detail_title");
                List<String> listName = new ArrayList<>();
                for (Element n : name) {
                    String names = n.select("a").text();
                    listName.add(names);
                }
                Elements elements = document.getElementsByClass("dict_dl_btn");
                for (int j = 0; j < elements.size(); j++) {
                    String url = elements.get(j).select("a").attr("href");
                    url = url + "\t" + listName.get(j);
                    set.add(url);
                    System.out.println(url);
                }
            }
        }
    }

    /**
     * 写入数据
     *
     * @throws IOException 异常
     */
    private void write() throws IOException {
        Writer writer = new FileWriter(new File(loadPath));
        for (String line : set)
            writer.write(line + "\n");
        writer.flush();
        writer.close();
    }

    /**
     * 下载搜狗url
     *
     * @throws IOException 异常
     */
    public void download_sogou() throws IOException {
        analysisSoGou();
        write();
    }

    /**
     * 根据搜狗ulr下载词库文件
     *@param dic 保存的搜狗文件的文件夹地址
     * @throws Exception
     */
    public void download_url(String dic) throws Exception {
        File file = new File(loadPath);
        URL url;
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        File f = new File(dic);
        if (!f.exists())
            f.mkdirs();
        while (bufferedReader.ready()) {
            String str = bufferedReader.readLine();
            String name = str.split("\t")[1];
            name = name.replaceAll("[|/、*;\".?<>\\\\]", "");
            FileOutputStream fileOutputStream = new FileOutputStream(dic+File.separator + name + ".scel");
            String url_str = str.split("\t")[0];
            url = new URL(url_str);
            DataInputStream dataInputStream = new DataInputStream(url.openStream());
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;
            while ((length = dataInputStream.read(buffer)) > 0)
                output.write(buffer, 0, length);
            fileOutputStream.write(output.toByteArray());
            fileOutputStream.close();
            dataInputStream.close();
            System.out.println(url_str + "下载成功！");
        }
        bufferedReader.close();
    }
}
