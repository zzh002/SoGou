package com.spider.demo.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class FileFinderUtilTest {


    @Test
    public void findFiles() {
        List<File> files = FileFinderUtil.findFiles("D:\\cell","斗罗",0);
        for (File file : files)
        {
            System.out.println(FileFinderUtil.getFileName(file));
            //System.out.println(file.getAbsolutePath());
        }
    }

    @Test
    public  void ScelToTxt() throws Exception {

        File[] files = FileFinderUtil.ScelToTxt("D:\\cell","D:\\文本",0);
    }
}