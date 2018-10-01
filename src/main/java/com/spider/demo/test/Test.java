package com.spider.demo.test;

import com.spider.demo.sogou.SoGou;

/**
 * @author ZZH
 * @date 2018/10/1 0001 18:03
 **/
public class Test {
    public static void main(String[] args) throws Exception {
        SoGou soGou = new SoGou();
        //下载搜狗词库所有url地址。并保存在本项目下的sogou.txt文件中
        soGou.download_sogou();
        //讲文本下的url地址下载到D盘下的cell文件夹中
        soGou.download_url("D://cell");
        //将搜狗文件转化成txt文本
        //soGou.toTxt("D:\\cell\\89个节日.scel", "D:\\cell\\89个节日.txt", false);
    }
}
