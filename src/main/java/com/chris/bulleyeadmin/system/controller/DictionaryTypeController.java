package com.chris.bulleyeadmin.system.controller;

import com.chris.bulleyeadmin.common.controller.BaseController;
import com.chris.bulleyeadmin.common.service.BaseService;
import com.chris.bulleyeadmin.common.utils.OperationLog;
import com.chris.bulleyeadmin.system.pojo.DictionaryType;
import com.chris.bulleyeadmin.system.service.DictionaryTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Chris
 * @Date: 2019-03-16 15:43
 * @Description:
 */
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
}
