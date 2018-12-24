package com.baizhi.springb1.service.impl;

import com.baizhi.springb1.dao.AlbumMapper;
import com.baizhi.springb1.dao.ChapterMapper;
import com.baizhi.springb1.entity.Album;
import com.baizhi.springb1.entity.Chapter;
import com.baizhi.springb1.entity.ChapterExample;
import com.baizhi.springb1.entity.DtoAlbum;
import com.baizhi.springb1.excp.AlbumUpdateFailException;
import com.baizhi.springb1.service.AlbumService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumMapper albumMapper;

    @Autowired
    private ChapterMapper chapterMapper;



    @Override
    public DtoAlbum findAll(int page, int rows) {

        DtoAlbum dtoAlbum = new DtoAlbum();
        int total = albumMapper.selectCount(new Album());
        dtoAlbum.setTotal(total);
        List<Album> albums = albumMapper.selectByRowBounds(new Album(),new RowBounds((page-1)*rows,rows));
        albums.forEach(s-> System.out.println(s));
        for (Album album : albums){
            ChapterExample chapterExample = new ChapterExample();
            chapterExample.createCriteria().andAlbIdEqualTo(album.getId());
            log.info(album.getId()+"---------------------------");
            List<Chapter> chapters = chapterMapper.selectByExample(chapterExample);
            album.setChildren(chapters);
        }
        albums.forEach(s-> System.out.println(s));
        dtoAlbum.setRows(albums);
        return dtoAlbum;
    }

    @Override
    public void add(Album album) {
            albumMapper.insert(album);
    }

    @Override
    public List<Album> exportAll() {
        List<Album> albums = albumMapper.selectAll();
        for (Album album : albums){
            ChapterExample chapterExample = new ChapterExample();
            chapterExample.createCriteria().andAlbIdEqualTo(album.getId());
            List<Chapter> chapters = chapterMapper.selectByExample(chapterExample);
            album.setChildren(chapters);
        }
        return albums;
    }

    @Override
    public void update(Album album) {
        try{
            albumMapper.updateByPrimaryKey(album);
        }catch (Exception e){
            throw new AlbumUpdateFailException("更新失败");
        }

    }


}
