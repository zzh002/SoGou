package com.spider.demo.sogou;

import java.util.List;
import java.util.Map;

public class SouGouModel {
    private Map<String, List<String>> wordMap;
    private String name;
    private String type;

    public Map<String, List<String>> getWordMap() {
        return wordMap;
    }

    void setWordMap(Map<String, List<String>> wordMap) {
        this.wordMap = wordMap;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}