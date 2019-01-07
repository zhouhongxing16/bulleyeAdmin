package com.chris.bulleyeadmin.system.service;

import com.chris.bulleyeadmin.common.mapper.BaseMapper;
import com.chris.bulleyeadmin.common.service.BaseService;
import com.chris.bulleyeadmin.system.mapper.DictionaryDataMapper;
import com.chris.bulleyeadmin.system.pojo.DictionaryData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: Chris
 * @Date: 2019-01-07 17:39
 * @Description:
 */
@Service
public class DictionaryDataService extends BaseService<DictionaryData> {

    @Autowired
    DictionaryDataMapper dictionaryDataMapper;

    @Override
    public BaseMapper<DictionaryData> getMapper() {
        return dictionaryDataMapper;
    }
}
