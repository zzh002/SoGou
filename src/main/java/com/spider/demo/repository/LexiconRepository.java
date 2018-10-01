package com.spider.demo.repository;

import com.spider.demo.dataobject.LexiconInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LexiconRepository extends JpaRepository<LexiconInfo , Integer> {
    List<LexiconInfo> findByCategoryId(Integer categoryId);
    LexiconInfo findByCategoryIdAndKeyword(Integer categoryId,String keyword);
}
