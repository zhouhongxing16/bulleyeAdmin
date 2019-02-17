package com.chris.bulleyeadmin.wechat.mapper;

import com.chris.bulleyeadmin.common.basemapper.BaseMapper;
import com.chris.bulleyeadmin.wechat.pojo.WxMember;
import org.apache.ibatis.annotations.Param;

/**
 * @Auther: Chris
 * @Date: 2019-01-11 18:01
 * @Description:
 */
public interface WxMemberMapper extends BaseMapper<WxMember> {
    WxMember getMemberByOpenId(@Param("openId") String openId);
}
