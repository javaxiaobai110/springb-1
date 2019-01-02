package com.baizhi.springb1.service;

import com.baizhi.springb1.entity.Article;

import java.util.List;

public interface ArticleService {
    public List<Article> findByUId(Integer id);

    public List<Article> findByNotUId(Integer id);

    public Article findOneArticle(Integer uid, Integer id);
}
