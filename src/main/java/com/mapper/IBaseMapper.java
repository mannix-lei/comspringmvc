package com.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Tellyes_worker on 2017/12/4/0004.
 */
public interface IBaseMapper {
    void update(Object object);
    void del(Object object);
    void add(Object object);

    /**
     * xml获取方法par[0]
     * @param args
     * @param <T>
     * @return
     */
    <T>List<T> findFirst(@Param("par") Object ...args);

    /**
     * xml获取方法par[0]
     * @param args
     * @param <T>
     * @return
     */
    <T>List<T> findAll(@Param("par") Object ...args);

    /**
     * 获取表记录条数
     * @param args
     * @return
     */
    int count(@Param("par") Object ...args);
}
