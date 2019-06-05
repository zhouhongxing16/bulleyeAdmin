package com.chris.bulleyeadmin.wechat.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chris.bulleyeadmin.common.controller.BaseController;
import com.chris.bulleyeadmin.common.entity.JsonResult;
import com.chris.bulleyeadmin.common.service.BaseService;
import com.chris.bulleyeadmin.common.utils.OperationLog;
import com.chris.bulleyeadmin.wechat.pojo.WxMenu;
import com.chris.bulleyeadmin.wechat.service.WxMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@OperationLog("公众号菜单管理")
@RestController
@RequestMapping("/wxmenu")
public class WxMenuController extends BaseController<WxMenu> {

    @Autowired
    WxMenuService wxMenuService;

    @Override
    public BaseService<WxMenu> getService() {
        return wxMenuService;
    }

    /**
     * 通过accountId获取菜单列表
     * @param accountId
     * @return
     */
    @ResponseBody
    @GetMapping("/getWxMenu/{accountId}")
    public Object listPage(@PathVariable String accountId) {
        //根据不同的参数配置,有些传递的是offset
        JSONArray array = new JSONArray();

        WxMenu wxMenuOne = new WxMenu();
        wxMenuOne.setAccountId(accountId);
        wxMenuOne.setParentId("0");
        List<WxMenu> wxMenuListOne = wxMenuService.selectlist(wxMenuOne);
        for (WxMenu menuOne:wxMenuListOne){
            JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSON(menuOne).toString());

            WxMenu wxMenuTwo = new WxMenu();
            wxMenuTwo.setAccountId(accountId);
            wxMenuTwo.setParentId(menuOne.getId());
            jsonObject.put("children", wxMenuService.selectlist(wxMenuTwo));
            array.add(jsonObject);
        }

        Map<String,Object> jsonMap = new HashMap<>();
        jsonMap.put("wxMenu", array);
        return jsonMap;
    }

    //创建菜单
    @GetMapping("/createWxMenu/{accountId}")
    @ResponseBody
    public JsonResult create(@PathVariable String accountId)  throws Exception {
        return wxMenuService.createWxMenu(accountId);
    }

}
