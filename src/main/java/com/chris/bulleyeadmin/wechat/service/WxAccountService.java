package com.chris.bulleyeadmin.wechat.service;

import com.chris.bulleyeadmin.common.basemapper.BaseMapper;
import com.chris.bulleyeadmin.common.service.BaseService;
import com.chris.bulleyeadmin.wechat.mapper.WxAccountMapper;
import com.chris.bulleyeadmin.wechat.pojo.WxAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WxAccountService extends BaseService<WxAccount> {

    @Autowired
    WxAccountMapper wxAccountMapper;

    @Override
    public BaseMapper<WxAccount> getMapper() {
        return wxAccountMapper;
    }

    public List<WxAccount> getAll(){
       return wxAccountMapper.selectAll();
    }
}
