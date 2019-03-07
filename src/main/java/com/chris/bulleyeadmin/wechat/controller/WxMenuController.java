package com.chris.bulleyeadmin.wechat.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chris.bulleyeadmin.common.controller.BaseController;
import com.chris.bulleyeadmin.common.service.BaseService;
import com.chris.bulleyeadmin.wechat.pojo.WxMenu;
import com.chris.bulleyeadmin.wechat.service.WxMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/wxmenu")
public class WxMenuController extends BaseController<WxMenu> {

    @Autowired
    WxMenuService wxMenuService;

    @Override
    public BaseService<WxMenu> getService() {
        return wxMenuService;
    }

    @Override
    public String getViewPrefix() {
        return "wxmenu";
    }

    @ResponseBody
    @GetMapping("/getWxMenu/{appId}")
    public Object listPage(@PathVariable String appId) {
        //根据不同的参数配置,有些传递的是offset
        JSONArray array = new JSONArray();

        WxMenu wxMenuOne = new WxMenu();
        wxMenuOne.setAppId(appId);
        wxMenuOne.setParentId("0");
        List<WxMenu> wxMenuListOne = wxMenuService.selectlist(wxMenuOne);
        for (WxMenu menuOne:wxMenuListOne){
            JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSON(menuOne).toString());

            WxMenu wxMenuTwo = new WxMenu();
            wxMenuTwo.setAppId(appId);
            wxMenuTwo.setParentId(menuOne.getId());
            jsonObject.put("children", wxMenuService.selectlist(wxMenuTwo));
            array.add(jsonObject);
        }

        Map<String,Object> jsonMap = new HashMap<>();
        jsonMap.put("wxMenu", array);
        return jsonMap;
    }
}
