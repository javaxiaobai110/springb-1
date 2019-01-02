package com.baizhi.springb1.service.impl;

import com.baizhi.springb1.dao.AlbumMapper;
import com.baizhi.springb1.dao.ChapterMapper;
import com.baizhi.springb1.entity.Album;
import com.baizhi.springb1.entity.Chapter;
import com.baizhi.springb1.excp.AddChapterException;
import com.baizhi.springb1.service.ChapterService;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
            if(album.getCount()==null)
                album.setCount(0);
            album.setCount(album.getCount()+1);
            albumMapper.updateByPrimaryKey(album);
        }catch (Exception e){
            throw new AddChapterException("章节添加失败");
        }
    }

    @Override
    public List<Chapter> findByFirstPage() {
        PageHelper.startPage(1, 2).setOrderBy("upload_date desc");
        List<Chapter> chapters = chapterMapper.selectAll();
        return chapters;
    }

    @Override
    public List<Chapter> findAll() {
        return chapterMapper.selectAll();
    }
}
