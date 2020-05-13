package com.chris.bulleyeadmin.system.controller;

import com.chris.bulleyeadmin.common.controller.BaseController;
import com.chris.bulleyeadmin.common.entity.JsonResult;
import com.chris.bulleyeadmin.common.service.BaseService;
import com.chris.bulleyeadmin.common.utils.AuthUtil;
import com.chris.bulleyeadmin.common.utils.OperationLog;
import com.chris.bulleyeadmin.system.pojo.DictionaryData;
import com.chris.bulleyeadmin.system.pojo.DictionaryType;
import com.chris.bulleyeadmin.system.pojo.User;
import com.chris.bulleyeadmin.system.service.DictionaryTypeService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Auther: Chris
 * @Date: 2019-03-16 15:43
 * @Description:
 */
@Api(tags = "字典类型", produces = "字典类型")
@OperationLog("字典类型")
@RestController
@RequestMapping("/dictionarytype")
public class DictionaryTypeController extends BaseController<DictionaryType> {

    @Autowired
    DictionaryTypeService dictionaryTypeService;

    @Override
    public BaseService<DictionaryType> getService() {
        return dictionaryTypeService;
    }

    @Override
    @PostMapping("/create")
    public JsonResult create(@RequestBody DictionaryType obj) throws Exception {
        User user = AuthUtil.getCurrentUser();
        obj.setUserId(user.getId());
        return dictionaryTypeService.add(obj);
    }
}
