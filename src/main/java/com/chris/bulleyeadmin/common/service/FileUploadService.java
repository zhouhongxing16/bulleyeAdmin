package com.chris.bulleyeadmin.common.service;


import com.chris.bulleyeadmin.common.basemapper.BaseMapper;
import com.chris.bulleyeadmin.common.entity.FileUploadPath;
import com.chris.bulleyeadmin.common.entity.FileUploadResult;
import com.chris.bulleyeadmin.common.entity.JsonResult;
import com.chris.bulleyeadmin.common.mapper.AttachFilesMapper;
import com.chris.bulleyeadmin.common.pojo.AttachFiles;
import com.chris.bulleyeadmin.common.utils.FileUploadUtils;
import com.chris.bulleyeadmin.common.utils.Help;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class FileUploadService extends BaseService<AttachFiles>{


    @Autowired
    FileUploadResult fileUploadResult;

    @Autowired
    FileUploadPath fileUploadPath;

    @Autowired
    AttachFilesMapper attachFilesMapper;


    @Override
    public BaseMapper<AttachFiles> getMapper() {
        return attachFilesMapper;
    }

    @Override
    public PageInfo listByPage(Map<String, Object> params) {
        List<AttachFiles> data;
        if (params != null) {
            Help.startPage( params );
            data = attachFilesMapper.getListByParams(params);
        } else {
            data = new ArrayList<>();
        }
        PageInfo page = new PageInfo(data);
        return page;
    }


    public JsonResult getFileListByBizId(Map<String, Object> params) {
        JsonResult result = new JsonResult();
        List<AttachFiles> list =  attachFilesMapper.getListByParams(params);
        result.setSuccess(true);
        result.setData(list);
        result.setMessage("数据查询成功！");
        return result;
    }


    public FileUploadResult fileUpload(MultipartFile file, String schoolId, String bizId) {
        if(file.isEmpty()){
            fileUploadResult.setStatus(0);
            fileUploadResult.setMsg("上传失败,文件不存在!");
        }else if(StringUtils.isEmpty(schoolId)){
            fileUploadResult.setStatus(0);
            fileUploadResult.setMsg("用户信息不存在,请重新登录!");
        }else{
            fileUploadResult =  FileUploadUtils.fileUpload(file,schoolId,fileUploadResult,fileUploadPath);
            if(fileUploadResult.getSuccess()){

                AttachFiles attachFiles = new AttachFiles();
                attachFiles.setBizId(bizId);
                attachFiles.setDownloadCount(0);
                attachFiles.setName(fileUploadResult.getFileName());
                attachFiles.setPath(fileUploadResult.getUrl());
                attachFiles.setStatus(1);
                attachFilesMapper.insert(attachFiles);
                fileUploadResult.setId(attachFiles.getId());
                String path = fileUploadPath.getUploadUrl()+"/file/view/"+fileUploadResult.getId();
                fileUploadResult.setUrl(path);
                fileUploadResult.setUrlpath(path);
            }

        }
        return fileUploadResult;
    }

    @Override
    public JsonResult deleteById(String id) {
        AttachFiles attachFiles = attachFilesMapper.selectByPrimaryKey(id);
        if(attachFiles!=null){
            FileUploadUtils.removeFile(attachFiles.getPath());
        }
        int deleteCount = getMapper().deleteByPrimaryKey(id);
        String msg = deleteCount>0?"成功删除"+deleteCount+"条记录":"数据删除失败！";
        return new JsonResult(deleteCount>0?true:false,null,msg,null, HttpStatus.OK.value());
    }
}
