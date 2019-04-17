package com.chris.bulleyeadmin.wechat.mapper;

import com.chris.bulleyeadmin.common.basemapper.BaseMapper;
import com.chris.bulleyeadmin.wechat.pojo.WxMaterial;

import java.util.List;
import java.util.Map;

public interface WxMaterialMapper extends BaseMapper<WxMaterial> {

    List<WxMaterial> getListByParams(Map<String,Object> map);
}
