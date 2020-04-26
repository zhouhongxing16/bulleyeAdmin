package com.chris.bulleyeadmin.common.controller;

import com.chris.bulleyeadmin.common.entity.JsonResult;
import com.chris.bulleyeadmin.common.pojo.BizFile;
import com.chris.bulleyeadmin.common.service.BaseService;
import com.chris.bulleyeadmin.common.service.BizFileService;
import com.chris.bulleyeadmin.common.utils.OperationLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date: 2020-04-22 23:50
 */
@Api(tags = "业务文件", produces = "业务文件")
@OperationLog("业务文件")
@RestController
@RequestMapping("/bizfile")
public class BizFileController extends BaseController<BizFile> {

    @Autowired
    BizFileService bizFileService;

    @Override
    public BaseService getService() {
        return bizFileService;
    }


    @PostMapping("/uploadSingleFile")
    @ApiOperation(value = "单文件上传", notes = "单文件上传")
    @ApiImplicitParam(name = "file", value = "")
    public JsonResult uploadSingleFile(MultipartFile file) throws Exception {
        return bizFileService.uploadSingleFile(file);
    }

    @PostMapping("/removeFile/{path}")
    @ApiOperation(value = "删除文件", notes = "删除文件")
    @ApiImplicitParam(name = "path", value = "文件名")
    public JsonResult removeFile(@PathVariable String path) throws IOException {
        return bizFileService.removeFile(path);
    }

}
