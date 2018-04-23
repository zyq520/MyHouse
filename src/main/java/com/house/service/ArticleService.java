package com.house.service;

import com.house.model.Article;

/**
 * @author zhuangyq
 * @create 2018-04-23 下午 14:20
 **/
public interface ArticleService extends BaseService<Article>{

    public Article getById(Long id);
}
