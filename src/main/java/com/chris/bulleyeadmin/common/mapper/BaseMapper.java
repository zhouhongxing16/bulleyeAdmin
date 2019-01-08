package com.chris.bulleyeadmin.common.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author Chris
 * @date 2018/12/11.
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {

     List<T> listByPage(Map<String, Object> map);

}
