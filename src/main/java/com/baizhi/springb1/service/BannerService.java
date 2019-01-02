package com.baizhi.springb1.service;

import com.baizhi.springb1.entity.Banner;
import com.baizhi.springb1.entity.DtoBanner;

import java.util.List;

public interface BannerService {

    public DtoBanner findByPage(int page, int rows);

    public Banner findOneById(Integer id);

    public void update(Banner banner);

    public void add(Banner banner);

    public void delete(Integer id);

    public List<Banner> findByFirstPage();

}
