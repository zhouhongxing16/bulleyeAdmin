package com.chris.bulleyeadmin.wechat.utils;

public class ErrorCode {
    /**
     * 微信的错误返回码
     */
    public static String MessageError(int num) {
        String error = null;
        if (num == -1) {
            error = "系统繁忙，此时请开发者稍候再试";
        }
        if (num == 40001) {
            error = "获取access_token时AppSecret错误，或者access_token无效。请开发者认真比对AppSecret的正确性，或查看是否正在为恰当的公众号调用接口";
        }
        if (num == 40002) {
            error = "不合法的凭证类型";
        }
        if (num == 40003) {
            error = "不合法的OpenID，请开发者确认OpenID（该用户）是否已关注公众号，或是否是其他公众号的OpenID";
        }
        if (num == 40004) {
            error = "不合法的媒体文件类型";
        }
        if (num == 40005) {
            error = "不合法的文件类型";
        }
        if (num == 40006) {
            error = "不合法的文件大小";
        }
        if (num == 40007) {
            error = "不合法的媒体文件id";
        }
        if (num == 40008) {
            error = "不合法的消息类型";
        }
        if (num == 40009) {
            error = "不合法的图片文件大小";
        }
        if (num == 40012) {
            error = "不合法的缩略图文件大小";
        }
        if (num == 40013) {
            error = "不合法的AppID，请开发者检查AppID的正确性，避免异常字符，注意大小写";
        }
        if (num == 40014) {
            error = "不合法的access_token，请开发者认真比对access_token的有效性（如是否过期），或查看是否正在为恰当的公众号调用接口";
        }
        if (num == 40015) {
            error = "不合法的菜单类型";
        }
        if (num == 40016) {
            error = "不合法的按钮个数";
        }
        if (num == 40017) {
            error = "不合法的按钮个数";
        }
        if (num == 40018) {
            error = "不合法的按钮名字长度";
        }
        if (num == 40019) {
            error = "不合法的按钮KEY长度";
        }
        if (num == 40020) {
            error = "不合法的按钮URL长度";
        }
        if (num == 40021) {
            error = "不合法的菜单版本号";
        }
        if (num == 40022) {
            error = "不合法的子菜单级数";
        }
        if (num == 40023) {
            error = "不合法的子菜单按钮个数";
        }
        if (num == 40024) {
            error = "不合法的子菜单按钮类型";
        }
        if (num == 40025) {
            error = "不合法的子菜单按钮名字长度";
        }
        if (num == 40026) {
            error = "不合法的子菜单按钮KEY长度";
        }
        if (num == 40027) {
            error = "不合法的子菜单按钮URL长度";
        }
        if (num == 40028) {
            error = "不合法的自定义菜单使用用户";
        }
        if (num == 40029) {
            error = "不合法的oauth_code";
        }
        if (num == 40030) {
            error = "不合法的refresh_token";
        }
        if (num == 40031) {
            error = "不合法的openid列表";
        }
        if (num == 40032) {
            error = "不合法的openid列表长度";
        }
        if (num == 40033) {
            error = "不合法的请求字符，不能包含'\'uxxxx格式的字符";
        }
        if (num == 40035) {
            error = "不合法的参数";
        }
        if (num == 40038) {
            error = "不合法的请求格式";
        }
        if (num == 40039) {
            error = "不合法的URL长度";
        }
        if (num == 40050) {
            error = "不合法的分组id";
        }
        if (num == 40051) {
            error = "分组名字不合法";
        }
        if (num == 40060) {
            error = "删除单篇图文时，指定的 article_idx 不合法";
        }
        if (num == 40117) {
            error = "分组名字不合法";
        }
        if (num == 40118) {
            error = "media_id大小不合法";
        }
        if (num == 40119) {
            error = "button类型错误";
        }
        if (num == 40120) {
            error = "button类型错误";
        }
        if (num == 40121) {
            error = "不合法的media_id类型";
        }
        if (num == 40132) {
            error = "微信号不合法";
        }
        if (num == 40137) {
            error = "不支持的图片格式";
        }
        if (num == 40155) {
            error = "请勿添加其他公众号的主页链接";
        }
        if (num == 41001) {
            error = "缺少access_token参数";
        }
        if (num == 41002) {
            error = "缺少appid参数";
        }
        if (num == 41003) {
            error = "缺少refresh_token参数";
        }
        if (num == 41004) {
            error = "缺少secret参数";
        }
        if (num == 41005) {
            error = "缺少多媒体文件数据";
        }
        if (num == 41006) {
            error = "缺少media_id参数";
        }
        if (num == 41007) {
            error = "缺少子菜单数据";
        }
        if (num == 41008) {
            error = "缺少oauth code";
        }
        if (num == 41009) {
            error = "缺少openid";
        }
        if (num == 42001) {
            error = "access_token超时，请检查access_token的有效期，请参考基础支持-获取access_token中，对access_token的详细机制说明";
        }
        if (num == 42002) {
            error = "refresh_token超时";
        }
        if (num == 42003) {
            error = "oauth_code超时";
        }
        if (num == 42007) {
            error = "用户修改微信密码，accesstoken和refreshtoken失效，需要重新授权";
        }
        if (num == 43001) {
            error = "需要GET请求";
        }
        if (num == 43002) {
            error = "需要POST请求";
        }
        if (num == 43003) {
            error = "需要HTTPS请求";
        }
        if (num == 43004) {
            error = "需要接收者关注";
        }
        if (num == 43005) {
            error = "需要好友关系";
        }
        if (num == 43019) {
            error = "需要将接收者从黑名单中移除";
        }
        if (num == 44001) {
            error = "多媒体文件为空";
        }
        if (num == 44002) {
            error = "POST的数据包为空";
        }
        if (num == 44003) {
            error = "图文消息内容为空";
        }
        if (num == 44004) {
            error = "文本消息内容为空";
        }
        if (num == 45001) {
            error = "多媒体文件大小超过限制";
        }
        if (num == 45002) {
            error = "消息内容超过限制";
        }
        if (num == 45003) {
            error = "标题字段超过限制";
        }
        if (num == 45004) {
            error = "描述字段超过限制";
        }
        if (num == 45005) {
            error = "链接字段超过限制";
        }
        if (num == 45006) {
            error = "图片链接字段超过限制";
        }
        if (num == 45007) {
            error = "语音播放时间超过限制";
        }
        if (num == 45008) {
            error = "图文消息超过限制";
        }
        if (num == 45009) {
            error = "接口调用超过限制";
        }
        if (num == 45010) {
            error = "创建菜单个数超过限制";
        }
        if (num == 45011) {
            error = "API调用太频繁，请稍候再试";
        }
        if (num == 45015) {
            error = "回复时间超过限制";
        }
        if (num == 45016) {
            error = "系统分组，不允许修改";
        }
        if (num == 45017) {
            error = "分组名字过长";
        }
        if (num == 45018) {
            error = "分组数量超过上限";
        }
        if (num == 45047) {
            error = "客服接口下行条数超过上限";
        }
        if (num == 45065) {
            error = "相同 clientmsgid 已存在群发记录，返回数据中带有已存在的群发任务的 msgid";
        }
        if (num == 46001) {
            error = "不存在媒体数据";
        }
        if (num == 46002) {
            error = "不存在的菜单版本";
        }
        if (num == 46003) {
            error = "不存在的菜单数据";
        }
        if (num == 46004) {
            error = "不存在的用户";
        }
        if (num == 47001) {
            error = "解析JSON/XML内容错误";
        }
        if (num == 48001) {
            error = "api功能未授权，请确认公众号已获得该接口，可以在公众平台官网-开发者中心页中查看接口权限";
        }
        if (num == 48002) {
            error = "粉丝拒收消息（粉丝在公众号选项中，关闭了“接收消息”）";
        }
        if (num == 48004) {
            error = "api接口被封禁，请登录mp.weixin.qq.com查看详情";
        }
        if (num == 48005) {
            error = "api禁止删除被自动回复和自定义菜单引用的素材";
        }
        if (num == 48006) {
            error = "api禁止清零调用次数，因为清零次数达到上限";
        }
        if (num == 50001) {
            error = "用户未授权该api";
        }
        if (num == 50002) {
            error = "用户受限，可能是违规后接口被封禁";
        }
        if (num == 61451) {
            error = "参数错误(invalid parameter)";
        }
        if (num == 61452) {
            error = "无效客服账号(invalid kf_account)";
        }
        if (num == 61453) {
            error = "客服帐号已存在(kf_account exsited)";
        }
        if (num == 61454) {
            error = "客服帐号名长度超过限制(仅允许10个英文字符，不包括@及@后的公众号的微信号)(invalid   kf_acount length)";
        }
        if (num == 61455) {
            error = "客服帐号名包含非法字符(仅允许英文+数字)(illegal character in     kf_account)";
        }
        if (num == 61456) {
            error = "客服帐号个数超过限制(10个客服账号)(kf_account count exceeded)";
        }
        if (num == 61457) {
            error = "无效头像文件类型(invalid   file type)";
        }
        if (num == 61450) {
            error = "系统错误(system error)";
        }
        if (num == 61500) {
            error = "日期格式错误";
        }
        if (num == 65301) {
            error = "不存在此menuid对应的个性化菜单";
        }
        if (num == 65302) {
            error = "没有相应的用户";
        }
        if (num == 65303) {
            error = "没有默认菜单，不能创建个性化菜单";
        }
        if (num == 65304) {
            error = "MatchRule信息为空";
        }
        if (num == 65305) {
            error = "个性化菜单数量受限";
        }
        if (num == 65306) {
            error = "不支持个性化菜单的帐号";
        }
        if (num == 65307) {
            error = "个性化菜单信息为空";
        }
        if (num == 65308) {
            error = "包含没有响应类型的button";
        }
        if (num == 65309) {
            error = "个性化菜单开关处于关闭状态";
        }
        if (num == 65310) {
            error = "填写了省份或城市信息，国家信息不能为空";
        }
        if (num == 65311) {
            error = "填写了城市信息，省份信息不能为空";
        }
        if (num == 65312) {
            error = "不合法的国家信息";
        }
        if (num == 65313) {
            error = "不合法的省份信息";
        }
        if (num == 65314) {
            error = "不合法的城市信息";
        }
        if (num == 65316) {
            error = "该公众号的菜单设置了过多的域名外跳（最多跳转到3个域名的链接）";
        }
        if (num == 65317) {
            error = "不合法的URL";
        }
        if (num == 9001001) {
            error = "POST数据参数不合法";
        }
        if (num == 9001002) {
            error = "远端服务不可用";
        }
        if (num == 9001003) {
            error = "Ticket不合法";
        }
        if (num == 9001004) {
            error = "获取摇周边用户信息失败";
        }
        if (num == 9001005) {
            error = "获取商户信息失败";
        }
        if (num == 9001006) {
            error = "获取OpenID失败";
        }
        if (num == 9001007) {
            error = "上传文件缺失";
        }
        if (num == 9001008) {
            error = "上传素材的文件类型不合法";
        }
        if (num == 9001009) {
            error = "上传素材的文件尺寸不合法";
        }
        if (num == 9001010) {
            error = "上传失败";
        }
        if (num == 9001020) {
            error = "帐号不合法";
        }
        if (num == 9001021) {
            error = "已有设备激活率低于50%，不能新增设备";
        }
        if (num == 9001022) {
            error = "设备申请数不合法，必须为大于0的数字";
        }
        if (num == 9001023) {
            error = "已存在审核中的设备ID申请";
        }
        if (num == 9001024) {
            error = "一次查询设备ID数量不能超过50";
        }
        if (num == 9001025) {
            error = "设备ID不合法";
        }
        if (num == 9001026) {
            error = "页面ID不合法";
        }
        if (num == 9001027) {
            error = "页面参数不合法";
        }
        if (num == 9001028) {
            error = "一次删除页面ID数量不能超过10";
        }
        if (num == 9001029) {
            error = "页面已应用在设备中，请先解除应用关系再删除";
        }
        if (num == 9001030) {
            error = "一次查询页面ID数量不能超过50";
        }
        if (num == 9001031) {
            error = "时间区间不合法";
        }
        if (num == 9001032) {
            error = "保存设备与页面的绑定关系参数错误";
        }
        if (num == 9001033) {
            error = "门店ID不合法";
        }
        if (num == 9001034) {
            error = "设备备注信息过长";
        }
        if (num == 9001035) {
            error = "设备申请参数不合法";
        }
        if (num == 9001036) {
            error = "查询起始值begin不合法";
        }

        return error;

    }
}
