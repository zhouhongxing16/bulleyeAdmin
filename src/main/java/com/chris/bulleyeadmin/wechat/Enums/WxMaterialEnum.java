package com.chris.bulleyeadmin.wechat.Enums;

public enum WxMaterialEnum {

    //图文
    news("news"),
    //图片
    image("image"),
    //视频
    video("video"),
    //缩略图
    thumb("thumb"),
    //音频
    voice("voice");

    private String type;
    WxMaterialEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
