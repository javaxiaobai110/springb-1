package com.baizhi.springb1.service.impl;

import com.baizhi.springb1.dao.ArticleMapper;
import com.baizhi.springb1.dao.GuruMapper;
import com.baizhi.springb1.dao.UserMapper;
import com.baizhi.springb1.entity.Article;
import com.baizhi.springb1.entity.ArticleExample;
import com.baizhi.springb1.entity.User;
import com.baizhi.springb1.excp.ArticleException;
import com.baizhi.springb1.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private GuruMapper guruMapper;

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<Article> findByUId(Integer id) {
        User user = userMapper.selectByPrimaryKey(id);
        ArticleExample articleExample = new ArticleExample();
        articleExample.createCriteria().andGuruIdEqualTo(user.getGuruId());
        List<Article> articles = articleMapper.selectByExample(articleExample);
        return articles;
    }

    @Override
    public List<Article> findByNotUId(Integer id) {
        User user = userMapper.selectByPrimaryKey(id);
        ArticleExample articleExample = new ArticleExample();
        articleExample.createCriteria().andGuruIdNotEqualTo(user.getGuruId());
        List<Article> articles = articleMapper.selectByExample(articleExample);
        return articles;
    }

    @Override
    public Article findOneArticle(Integer uid, Integer id) {
        if (uid == null || id == null){
            throw new ArticleException("查询文章详情出错");
        }else{
            Article article = articleMapper.selectByPrimaryKey(id);
            return article;
        }
    }
}
