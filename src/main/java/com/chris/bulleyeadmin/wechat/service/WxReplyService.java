package com.chris.bulleyeadmin.wechat.service;

import com.chris.bulleyeadmin.common.basemapper.BaseMapper;
import com.chris.bulleyeadmin.common.service.BaseService;
import com.chris.bulleyeadmin.wechat.mapper.WxReplyMapper;
import com.chris.bulleyeadmin.wechat.pojo.WxReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class WxReplyService extends BaseService<WxReply> {

    @Autowired
    WxReplyMapper wxReplyMapper;

    @Override
    public BaseMapper<WxReply> getMapper() {
        return wxReplyMapper;
    }

    public List<WxReply> getListByParams(Map<String,Object> map) {
        return wxReplyMapper.getListByParams(map);
    }
}
