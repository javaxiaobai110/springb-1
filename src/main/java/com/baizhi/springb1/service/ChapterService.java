package com.baizhi.springb1.service;

import com.baizhi.springb1.entity.Chapter;

import java.util.List;

public interface ChapterService {

    public void add(Chapter chapter);

    public List<Chapter> findByFirstPage();

    public List<Chapter> findAll();
}
