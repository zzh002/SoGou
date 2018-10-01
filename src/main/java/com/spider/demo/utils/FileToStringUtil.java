package com.spider.demo.utils;

import java.io.*;

public class FileToStringUtil {
    /**
     * 文件->String
     *
     * @param filepath
     * @return
     */
    public static String[] readToString(String filepath) {
        String encoding = "UTF-8";
        File file = new File(filepath);
        BufferedReader reader = null;
        String line = null;
        String[] str = null;
        String[] f = new String[100000];
        int i = 0;
        try {
            reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null) {
                str = line.split("\t");
                f[i++] = str[1];
            }
            f[i] = null;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            return f;
        } catch (Exception e) {
            System.err.println("The OS does not support " + encoding);
            e.printStackTrace();
            return null;
        }
    }
}
