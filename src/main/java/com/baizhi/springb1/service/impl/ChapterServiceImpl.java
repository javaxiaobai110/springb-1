package com.baizhi.springb1.service.impl;

import com.baizhi.springb1.dao.ChapterMapper;
import com.baizhi.springb1.entity.Chapter;
import com.baizhi.springb1.service.ChapterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class ChapterServiceImpl implements ChapterService {

    @Autowired
    private ChapterMapper chapterMapper;

    @Override
    public void add(Chapter chapter) {
        chapterMapper.insertSelective(chapter);//使用数据库默认值
    }
}
