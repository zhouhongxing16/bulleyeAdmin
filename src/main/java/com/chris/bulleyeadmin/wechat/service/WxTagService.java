package com.chris.bulleyeadmin.wechat.service;

import com.chris.bulleyeadmin.common.basemapper.BaseMapper;
import com.chris.bulleyeadmin.common.service.BaseService;
import com.chris.bulleyeadmin.wechat.mapper.WxTagMapper;
import com.chris.bulleyeadmin.wechat.pojo.WxTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WxTagService extends BaseService<WxTag> {

    @Autowired
    WxTagMapper wxTagMapper;

    @Override
    public BaseMapper<WxTag> getMapper() {
        return wxTagMapper;
    }

}
