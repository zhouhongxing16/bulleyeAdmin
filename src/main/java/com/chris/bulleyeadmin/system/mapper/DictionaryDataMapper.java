package com.chris.bulleyeadmin.system.mapper;

import com.chris.bulleyeadmin.common.basemapper.BaseMapper;
import com.chris.bulleyeadmin.system.dto.DictionaryDataDto;
import com.chris.bulleyeadmin.system.pojo.DictionaryData;

import java.util.List;
import java.util.Map;

/**
 * @Auther: Chris
 * @Date: 2019-01-07 17:35
 * @Description:
 */
public interface DictionaryDataMapper extends BaseMapper<DictionaryData> {

    List<DictionaryDataDto> getDtoListByParams(Map<String,String> map);

    DictionaryDataDto getByParams(Map<String,String> map);
}
