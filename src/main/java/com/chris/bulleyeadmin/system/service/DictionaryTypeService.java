package com.chris.bulleyeadmin.system.service;

import com.chris.bulleyeadmin.common.basemapper.BaseMapper;
import com.chris.bulleyeadmin.common.service.BaseService;
import com.chris.bulleyeadmin.system.mapper.DictionaryTypeMapper;
import com.chris.bulleyeadmin.system.pojo.DictionaryType;
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
