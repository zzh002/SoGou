package com.spider.demo.utils;

import com.spider.demo.sogou.SoGou;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileFinderUtil {


    /**
     * 查找文件。
     *
     * @param baseDirName    待查找的目录
     * @param targetFileName 目标文件名，支持通配符形式
     * @param count          期望结果数目，如果为0，则表示查找全部。
     * @return 满足查询条件的文件名列表
     */
    public static List<File> findFiles(String baseDirName, String targetFileName, int count) {

        List<File> fileList = new ArrayList();
        //判断目录是否存在
        File baseDir = new File(baseDirName);
        if (!baseDir.exists() || !baseDir.isDirectory()) {
            System.out.println("文件查找失败：" + baseDirName + "不是一个目录！");
            return fileList;
        }
        if (targetFileName == null || targetFileName.equals("")) {
            System.out.println("文件查找失败：输入的关键词为空！");
            return fileList;
        }
        File[] files = baseDir.listFiles();
        for (File file : files) {
            //从目录中读取文件

            //如果是文件则根据文件名与目标文件名进行匹配
            String tempName = file.getName();
            if (tempName.contains(targetFileName)) {
                //匹配成功，将文件名添加到结果集
                fileList.add(file.getAbsoluteFile());
                //如果已经达到指定的数目，则退出循环
                if ((count != 0) && (fileList.size() >= count)) {
                    return fileList;
                }
            }
        }

        return fileList;
    }

    //获取不带后缀名的文件名
    public static String getFileName(File file) {
        String file_name = file.getName();
        return file_name.substring(0, file_name.lastIndexOf("."));
    }

    public static File[] ScelToTxt(String baseDirPath, String targetDirPayh , Integer count) throws Exception {

        //判断目录是否存在
        File baseDir = new File(baseDirPath);
        if (!baseDir.exists() || !baseDir.isDirectory()) {
            System.out.println("文件查找失败：不是一个目录！");

        }
        File[] files = baseDir.listFiles();
        int i = 0;

        for (File file : files){
            File f = new File(targetDirPayh + File.separator + FileFinderUtil.getFileName(file)+".txt");
            if(!f.exists()) {
                SoGou sogou = new SoGou();
                System.out.println(FileFinderUtil.getFileName(file));
                sogou.toTxt(file.getAbsolutePath(),
                        targetDirPayh + File.separator + FileFinderUtil.getFileName(file) + ".txt",
                        false);
            }
            i++;
            if (i>=count&&count!=0)
                break;
        }
        return files;
    }
}
