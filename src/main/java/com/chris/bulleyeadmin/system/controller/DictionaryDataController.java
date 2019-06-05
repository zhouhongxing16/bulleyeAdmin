package com.chris.bulleyeadmin.system.controller;

import com.chris.bulleyeadmin.common.controller.BaseController;
import com.chris.bulleyeadmin.common.service.BaseService;
import com.chris.bulleyeadmin.common.utils.OperationLog;
import com.chris.bulleyeadmin.system.pojo.DictionaryData;
import com.chris.bulleyeadmin.system.service.DictionaryDataService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Chris
 * @Date: 2019-03-16 15:45
 * @Description:
 */
@Api(tags = "dictionarydata", description = "数据字典")
@OperationLog("数据字典")
@RestController
@RequestMapping("/dictionarydata")
public class DictionaryDataController extends BaseController<DictionaryData> {

    @Autowired
    DictionaryDataService dictionaryDataService;

    @Override
    public BaseService<DictionaryData> getService() {
        return dictionaryDataService;
    }
}
