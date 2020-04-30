package com.chris.bulleyeadmin.wechat.service;

import com.chris.bulleyeadmin.common.basemapper.BaseMapper;
import com.chris.bulleyeadmin.common.entity.JsonResult;
import com.chris.bulleyeadmin.common.service.BaseService;
import com.chris.bulleyeadmin.wechat.mapper.WxAccountMapper;
import com.chris.bulleyeadmin.wechat.mapper.WxMemberMapper;
import com.chris.bulleyeadmin.wechat.pojo.WxAccount;
import com.chris.bulleyeadmin.wechat.pojo.WxMember;
import lombok.AllArgsConstructor;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class WxMemberService extends BaseService<WxMember> {

    @Autowired
    private WxMemberMapper wxMemberMapper;

    @Autowired
    private WxAccountMapper wxAccountMapper;

    @Autowired
    private final WxMpService wxService;

    @Override
    public BaseMapper<WxMember> getMapper() {
        return wxMemberMapper;
    }

    public WxMember getMemberByOpenId(String openId) {
       return wxMemberMapper.getMemberByOpenId(openId);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public JsonResult setTag (String sourceId, List<String> openids) {
        //获取相关接口
        WxAccount queryaccount = new WxAccount();
        queryaccount.setSourceId(sourceId);

        WxAccount account = wxAccountMapper.selectOne(queryaccount);
        WxMpService wxService = this.wxService.switchoverTo(account.getAppId());

        return null;
    }
}
