package com.baizhi.springb1.service;

import com.baizhi.springb1.entity.Album;
import com.baizhi.springb1.entity.DtoAlbum;

import java.util.List;

public interface AlbumService {

    public DtoAlbum findAll(int page, int rows);

    public void add(Album album);

    public List<Album> exportAll();

    public void update(Album album);
}
