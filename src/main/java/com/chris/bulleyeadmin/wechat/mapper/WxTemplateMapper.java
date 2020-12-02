package com.chris.bulleyeadmin.wechat.mapper;

import com.chris.bulleyeadmin.common.basemapper.BaseMapper;
import com.chris.bulleyeadmin.wechat.pojo.WxTemplate;

import java.util.List;

public interface WxTemplateMapper extends BaseMapper<WxTemplate> {

    int deleteBySourceId(String sourceId);

    int insertByList(List<WxTemplate> list);

}
