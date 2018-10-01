package com.spider.demo.sogou;

import cn.tyoui.sogou.SoGou;


public class SoGouSpider {

    public void getSoGouSpider(String filePath , String targetFilePath) throws Exception {
        SoGou sogou = new SoGou();
        //下载搜狗词库所有url地址。并保存在本项目下的sogou.txt文件中
        //sogou.download_sogou();
        //讲文本下的url地址下载到D盘下的cell文件夹中
        //sogou.download_url("D://cell");
        //将搜狗文件转化成txt文本
        System.out.println(filePath+"  "+targetFilePath);
        sogou.toTxt(filePath, targetFilePath, false);
    }
}
