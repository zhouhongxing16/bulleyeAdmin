package com.chris.bulleyeadmin.common.basemapper;

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

     List<T> getListByParams(Map<String, Object> map);

}
