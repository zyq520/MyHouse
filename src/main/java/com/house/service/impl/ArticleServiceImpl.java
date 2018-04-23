package com.house.service.impl;


import com.house.dao.ArticleDAO;
import com.house.model.Article;
import com.house.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhuangyq
 * @create 2018-04-23 下午 14:20
 **/
@Service
public class ArticleServiceImpl implements ArticleService{
    @Autowired
    private ArticleDAO articleDAO;
    @Override
    public Article getById(Long id) {
        return articleDAO.findById(id);
    }

    @Override
    public List<Article> query(Article param) {
        return null;
    }

    @Override
    public Integer update(Article param) {
        return null;
    }

    @Override
    public Integer delete(Long id) {
        return null;
    }

    @Override
    public Integer insert(Article param) {
        return null;
    }

    @Override
    public Article findById(Long id) {
        return null;
    }
}
