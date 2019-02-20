package com.chris.bulleyeadmin.wechat.mapper;


import com.chris.bulleyeadmin.common.basemapper.BaseMapper;
import com.chris.bulleyeadmin.wechat.pojo.WxReply;

import java.util.List;
import java.util.Map;

/**
 * @Auther: Chris
 * @Date: 2019-01-11 18:00
 * @Description:
 */
public interface WxReplyMapper extends BaseMapper<WxReply> {

    List<WxReply> getListByParams(Map<String,Object> map);
}
