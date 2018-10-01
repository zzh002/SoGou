package com.spider.demo.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class FileToStringUtilTest {

    @Test
    public void readToString() {

        String[] s = FileToStringUtil.readToString("D:\\cell\\文本\\89个节日.txt");
        int i = 0;
        while (s[i]!=null)
        System.out.println(s[i++]);
    }
}