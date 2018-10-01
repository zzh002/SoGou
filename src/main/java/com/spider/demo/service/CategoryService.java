package com.spider.demo.service;

import com.spider.demo.dataobject.LexiconCategory;

import java.util.List;

public interface CategoryService {
    LexiconCategory findOne(Integer categoryId);

    LexiconCategory findByCategoryName(String categoryName);

    LexiconCategory save(LexiconCategory lexiconCategory);

    List<LexiconCategory> findAll();
}
