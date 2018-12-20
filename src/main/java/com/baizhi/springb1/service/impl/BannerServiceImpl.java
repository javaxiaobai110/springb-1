package com.baizhi.springb1.service.impl;

import com.baizhi.springb1.dao.BannerMapper;
import com.baizhi.springb1.entity.Banner;
import com.baizhi.springb1.entity.DtoBanner;
import com.baizhi.springb1.service.BannerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BannerServiceImpl implements BannerService {

    @Autowired
    private BannerMapper bannerMapper;

    @Override
    public DtoBanner findByPage(int page, int rows) {
        DtoBanner dtoBanner = new DtoBanner();
        RowBounds rb = new RowBounds((page-1)*rows,rows);
        List<Banner> banners = bannerMapper.selectByRowBounds(new Banner(),rb);
        int i = bannerMapper.selectCount(new Banner());
        dtoBanner.setRows(banners);
        dtoBanner.setTotal(i);
        return dtoBanner;
    }

    @Override
    public Banner findOneById(Integer id) {
        Banner banner = bannerMapper.selectByPrimaryKey(id);
        return  banner;
    }

    @Override
    public void update(Banner banner) {
        bannerMapper.updateByPrimaryKey(banner);
        return;
    }

    @Override
    public void add(Banner banner) {
        bannerMapper.insertSelective(banner);
    }

    @Override
    public void delete(Integer id) {
        bannerMapper.deleteByPrimaryKey(id);
    }
}
