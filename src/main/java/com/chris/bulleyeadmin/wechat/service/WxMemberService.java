package com.chris.bulleyeadmin.wechat.service;

import com.chris.bulleyeadmin.common.basemapper.BaseMapper;
import com.chris.bulleyeadmin.common.service.BaseService;
import com.chris.bulleyeadmin.wechat.mapper.WxMemberMapper;
import com.chris.bulleyeadmin.wechat.pojo.WxMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WxMemberService extends BaseService<WxMember> {

    @Autowired
    WxMemberMapper wxMemberMapper;

    @Override
    public BaseMapper<WxMember> getMapper() {
        return wxMemberMapper;
    }
}
