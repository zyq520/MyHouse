package com.house.dao;

import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * @author zhuangyq
 * @create 2018-04-23 上午 9:46
 **/
@Mapper
public abstract interface BaseDAO<T> {

    public abstract <T> List<T> query(T param);

    public abstract <T> Integer update(T param);

    public abstract <T> Integer delete(Long id);

    public abstract <T> Integer insert(T param);

    public abstract <T> T findById(Long id);

    public abstract <T> T findByName(String name);
}
