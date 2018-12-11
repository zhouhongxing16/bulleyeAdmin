package com.chris.bulleyeadmin.service;

import com.chris.bulleyeadmin.config.BaseMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author zj
 * @date 2017/7/19.
 */
public abstract class BaseService<T> {

    public abstract BaseMapper<T> getMapper();

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean add(T obj) {
        int insertCount = getMapper().insert(obj);
        return insertCount>0?true:false;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean update(T obj) {
        int updateCount = getMapper().updateByPrimaryKeySelective(obj);
        return updateCount>0?true:false;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(T obj) {
        int deleteCount = getMapper().delete(obj);
        return deleteCount>0?true:false;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean deleteById(String id) {
        int deleteCount = getMapper().deleteByPrimaryKey(id);
        return deleteCount>0?true:false;
    }

    public T getById(String hId) {
        return (T)getMapper().selectByPrimaryKey(hId);
    }

    @Override
    public String toString() {
        return "BaseService{}";
    }

    public PageInfo<T> listByPage(Map<String, Object> params)
    {
        List<T> data = null;
        if (params != null) {
            PageHelper.startPage(params);
            data = getMapper().listByPage(params);
        } else {
            data = new ArrayList<>();
        }
        return new PageInfo<T>(data);
    }

    public PageInfo<T> listPageById(Map<String, Object> params)
    {
        List<T> data = null;
        if (params != null) {
            PageHelper.startPage(params);
            data = getMapper().listPageById(params);
        } else {
            data = new ArrayList<>();
        }
        return new PageInfo<T>(data);
    }

    public List<T> getAll(Map<String, Object> params) {
        List<T> data;
        if (params != null) {
            PageHelper.startPage(params);
            data = getMapper().selectAll();
        } else {
            data = new ArrayList<T>();
        }
        return data;
    }

    public List<T> listByObj(T t) {
        return getMapper().select(t);
    }
}
