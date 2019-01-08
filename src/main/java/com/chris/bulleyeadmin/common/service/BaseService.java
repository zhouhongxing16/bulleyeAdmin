package com.chris.bulleyeadmin.common.service;

import com.chris.bulleyeadmin.common.mapper.BaseMapper;
import com.chris.bulleyeadmin.common.utils.Help;
import com.chris.bulleyeadmin.system.dto.StaffDto;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.Mapper;

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

    public PageInfo listByPage(Map<String, Object> params) {
        List<T> data = null;
        if(params.get("pageNum") != null){
            if(params.get("pageSize").toString().equals( "0" )){
                params.remove( "pageNum" );
                params.remove( "pageSize" );
            } else {
                PageHelper.startPage(params);
            }
        }
        data = getMapper().listByPage(params);

        return new PageInfo(data);
    }

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
}
