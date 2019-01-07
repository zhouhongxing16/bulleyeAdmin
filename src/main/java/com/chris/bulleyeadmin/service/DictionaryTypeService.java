package com.chris.bulleyeadmin.service;

import com.chris.bulleyeadmin.config.BaseMapper;
import com.chris.bulleyeadmin.mapper.DictionaryDataMapper;
import com.chris.bulleyeadmin.mapper.DictionaryTypeMapper;
import com.chris.bulleyeadmin.pojo.DictionaryType;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: Chris
 * @Date: 2019-01-07 17:40
 * @Description:
 */
@Service
public class DictionaryTypeService extends BaseService<DictionaryType> {

    @Autowired
    DictionaryTypeMapper dictionaryTypeMapper;

    @Override
    public BaseMapper<DictionaryType> getMapper() {
        return dictionaryTypeMapper;
    }
}
