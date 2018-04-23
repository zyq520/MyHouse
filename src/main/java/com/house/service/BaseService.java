package com.house.service;

import java.util.List;

/**
 * @author zhuangyq
 * @create 2018-04-23 上午 9:46
 **/
public interface BaseService<T>{
    public List<T> query(T param);

    public Integer update(T param);

    public Integer delete(Long id);

    public Integer insert(T param);

    public T findById(Long id);
}
