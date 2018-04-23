package com.house.dao;

import com.house.model.Article;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zhuangyq
 * @create 2018-04-23 下午 14:17
 **/
@Mapper
public interface ArticleDAO{

    public Article findById(Long id);
}
