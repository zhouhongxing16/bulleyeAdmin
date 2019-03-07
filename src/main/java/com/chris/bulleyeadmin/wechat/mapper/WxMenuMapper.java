package com.chris.bulleyeadmin.wechat.mapper;


import com.chris.bulleyeadmin.common.basemapper.BaseMapper;
import com.chris.bulleyeadmin.wechat.pojo.WxMenu;
import com.chris.bulleyeadmin.wechat.pojo.WxReply;

import java.util.List;

/**
 * @Auther: Chris
 * @Date: 2019-01-11 18:00
 * @Description:
 */
public interface WxMenuMapper extends BaseMapper<WxMenu> {

    List<WxMenu> selectlist(WxMenu wxMenu);

}
