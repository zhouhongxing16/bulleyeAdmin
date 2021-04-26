package com.chris.bulleyeadmin.wechat.service;

import com.chris.bulleyeadmin.common.basemapper.BaseMapper;
import com.chris.bulleyeadmin.common.entity.JsonResult;
import com.chris.bulleyeadmin.common.service.BaseService;
import com.chris.bulleyeadmin.wechat.mapper.WxTemplateMapper;
import com.chris.bulleyeadmin.wechat.pojo.WxTemplate;
import me.chanjar.weixin.mp.bean.template.WxMpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class WxTemplateService extends BaseService<WxTemplate> {

    @Autowired
    WxTemplateMapper wxTemplateMapper;

    @Override
    public BaseMapper<WxTemplate> getMapper() {
        return wxTemplateMapper;
    }

    public JsonResult insertByList(List<WxMpTemplate> list, String sourceId) {
        try {
            List<WxTemplate> wxTemplateList = new ArrayList<>();

            for (WxMpTemplate wxMpTemplate: list) {
                WxTemplate wxTemplate = new WxTemplate();
                wxTemplate.setId(UUID.randomUUID().toString());
                wxTemplate.setSourceId(sourceId);
                wxTemplate.setTemplateId(wxMpTemplate.getTemplateId());
                wxTemplate.setTitle(wxMpTemplate.getTitle());
                wxTemplate.setPrimaryIndustry(wxMpTemplate.getPrimaryIndustry());
                wxTemplate.setDeputyIndustry(wxMpTemplate.getDeputyIndustry());
                wxTemplate.setContent(wxMpTemplate.getContent());
                wxTemplate.setExample(wxMpTemplate.getExample());

                wxTemplateList.add(wxTemplate);
            }

            wxTemplateMapper.deleteBySourceId(sourceId);

            wxTemplateMapper.insertByList(wxTemplateList);

            return new JsonResult(true,null,"获取成功！",null, HttpStatus.OK.value());
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(true,null,"获取失败！",null, HttpStatus.OK.value());
        }

    }
}
