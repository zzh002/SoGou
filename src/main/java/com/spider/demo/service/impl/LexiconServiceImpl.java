package com.spider.demo.service.impl;

import com.spider.demo.dataobject.LexiconInfo;
import com.spider.demo.repository.LexiconRepository;
import com.spider.demo.service.LexiconService;
import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LexiconServiceImpl implements LexiconService {
    @Autowired
    private LexiconRepository lexiconRepository;

    @Override
    public LexiconInfo findOne(Integer id) {
        return lexiconRepository.findById(id).get();
    }

    @Override
    public LexiconInfo save(LexiconInfo lexiconInfo) {
        if (StringUtil.isBlank(lexiconInfo.getKeyword()))
            return null;
        LexiconInfo lexicon = new LexiconInfo();
        lexicon = lexiconRepository.findByCategoryIdAndKeyword(lexiconInfo.getCategoryId(),lexiconInfo.getKeyword());
        if (lexicon!=null)
            return lexicon;
        return lexiconRepository.save(lexiconInfo);
    }

    @Override
    public List<LexiconInfo> findByCategoryId(Integer categoryId) {
        return lexiconRepository.findByCategoryId(categoryId);
    }

    @Override
    public List<LexiconInfo> saveAll(List<LexiconInfo> lexiconInfoList) {
        List<LexiconInfo> infoList = lexiconRepository.findByCategoryId(lexiconInfoList.get(0).getCategoryId());
        if (infoList.size()==0)
        return lexiconRepository.saveAll(lexiconInfoList);
        else
            return infoList;
    }

    @Override
    public Integer count() {
        Integer n = Math.toIntExact(lexiconRepository.count());
        n += 6;
        return n;
    }
}
