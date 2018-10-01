package com.spider.demo.sogou;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SoGouSpiderTest {

    @Test
    public void getSoGouSpider() throws Exception {
        SoGouSpider soGouSpider = new SoGouSpider();
        soGouSpider.getSoGouSpider("D:\\cell\\89个节日.scel","D:\\cell\\文本\\89个节日.txt");
    }
}