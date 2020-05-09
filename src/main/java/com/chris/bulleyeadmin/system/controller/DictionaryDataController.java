package com.chris.bulleyeadmin.system.controller;

import com.chris.bulleyeadmin.common.controller.BaseController;
import com.chris.bulleyeadmin.common.entity.JsonResult;
import com.chris.bulleyeadmin.common.entity.PageResult;
import com.chris.bulleyeadmin.common.service.BaseService;
import com.chris.bulleyeadmin.common.utils.OperationLog;
import com.chris.bulleyeadmin.system.dto.DictionaryDataDto;
import com.chris.bulleyeadmin.system.pojo.DictionaryData;
import com.chris.bulleyeadmin.system.service.DictionaryDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: Chris
 * @Date: 2019-03-16 15:45
 * @Description:
 */
@Api(tags = "数据字典", produces = "数据字典")
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

    @PostMapping("/getByTypeCode/{code}")
    @ApiOperation(value = "根据类型code获取字典数据", notes = "")
    @ApiImplicitParam(name = "code", value = "")
    public Object getByTypeCode(@PathVariable String code) throws Exception {
        JsonResult result = new JsonResult();
        result.setSuccess(true);
        result.setMessage("查询成功");
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("typeCode", code);
        List<DictionaryDataDto> list = dictionaryDataService.getDtoListByParams(queryMap);
        PageResult<DictionaryDataDto> page = new PageResult<>();
        page.setList(list);
        page.setTotal(new Long(list.size()));
        result.setPage(page);
        result.setStatus(HttpStatus.OK.value());
        return result;
    }

    @ApiOperation(value = "根据字典code获取一条字典数据", notes = "")
    @ApiImplicitParam(name = "code", value = "")
    @PostMapping("/getDictionaryDataByCode/{code}")
    public JsonResult<DictionaryDataDto> getDictionaryDataByCode(@PathVariable String code) throws Exception {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("typeCode", code);
        DictionaryDataDto dto = dictionaryDataService.getByParams(queryMap);
        return JsonResult.toSuccess(dto, "查询成功");
    }
}
