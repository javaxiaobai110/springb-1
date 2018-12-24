package com.baizhi.springb1.service.impl;

import com.baizhi.springb1.dao.AlbumMapper;
import com.baizhi.springb1.dao.ChapterMapper;
import com.baizhi.springb1.entity.Album;
import com.baizhi.springb1.entity.Chapter;
import com.baizhi.springb1.excp.AddChapterException;
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

    @Autowired
    private AlbumMapper albumMapper;

    @Override
    public void add(Chapter chapter) {
        try{
            chapterMapper.insertSelective(chapter);//使用数据库默认值
            Album album = albumMapper.selectByPrimaryKey(chapter.getAlbId());
            album.setCount(album.getCount()+1);
            albumMapper.updateByPrimaryKey(album);
        }catch (Exception e){
            throw new AddChapterException("章节添加失败");
        }
    }
}
