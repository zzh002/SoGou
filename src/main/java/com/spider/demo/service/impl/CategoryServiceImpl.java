package com.spider.demo.service.impl;

import com.spider.demo.dataobject.LexiconCategory;
import com.spider.demo.repository.CategoryRepository;
import com.spider.demo.service.CategoryService;
import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public LexiconCategory findOne(Integer categoryId) {
        return categoryRepository.findById(categoryId).get();
    }

    @Override
    public LexiconCategory findByCategoryName(String categoryName) {
        return categoryRepository.findByCategoryName(categoryName);
    }

    @Override
    public LexiconCategory save(LexiconCategory lexiconCategory) {
        System.out.println(lexiconCategory.getCategoryName());
        if (StringUtil.isBlank(lexiconCategory.getCategoryName())) {
            return null;
        }
        LexiconCategory category = categoryRepository.findByCategoryName(lexiconCategory.getCategoryName());
        if (category!=null) {
            return category;
        }
        return categoryRepository.save(lexiconCategory);
    }

    @Override
    public List<LexiconCategory> findAll() {
        return categoryRepository.findAll();
    }
}
