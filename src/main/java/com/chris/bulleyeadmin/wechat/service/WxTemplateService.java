package com.chris.bulleyeadmin.wechat.service;

import com.chris.bulleyeadmin.common.basemapper.BaseMapper;
import com.chris.bulleyeadmin.common.service.BaseService;
import com.chris.bulleyeadmin.wechat.mapper.WxTemplateMapper;
import com.chris.bulleyeadmin.wechat.pojo.WxTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class WxTemplateService extends BaseService<WxTemplate> {

    @Autowired
    WxTemplateMapper wxTemplateMapper;

    @Override
    public BaseMapper<WxTemplate> getMapper() {
        return wxTemplateMapper;
    }
}
