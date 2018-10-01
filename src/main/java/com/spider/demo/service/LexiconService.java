package com.spider.demo.service;

import com.spider.demo.dataobject.LexiconInfo;

import java.util.List;

public interface LexiconService {

    LexiconInfo findOne(Integer id);

    LexiconInfo save(LexiconInfo lexiconInfo);

    List<LexiconInfo> findByCategoryId(Integer categoryId);

    List<LexiconInfo> saveAll(List<LexiconInfo> lexiconInfoList);

    Integer count();
}
