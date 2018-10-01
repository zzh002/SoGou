package com.spider.demo.repository;

import com.spider.demo.dataobject.LexiconCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<LexiconCategory , Integer> {
    LexiconCategory findByCategoryName(String categoryName);
}
