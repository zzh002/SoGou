package com.spider.demo.dto;

import com.spider.demo.dataobject.LexiconInfo;
import lombok.Data;

import java.util.List;

@Data
public class LexiconDTO {

    private Integer id;

    private String categoryName;

    private List<LexiconInfo> lexiconInfoList;
}
