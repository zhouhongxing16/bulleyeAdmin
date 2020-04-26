package com.chris.bulleyeadmin.common.mapper;

import com.chris.bulleyeadmin.common.basemapper.BaseMapper;
import com.chris.bulleyeadmin.common.pojo.BizFile;

import java.util.Map;

/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2020-04-22 23:50
 */
public interface BizFileMapper extends BaseMapper<BizFile> {
     BizFile getByParams(Map<String,String> map);
}