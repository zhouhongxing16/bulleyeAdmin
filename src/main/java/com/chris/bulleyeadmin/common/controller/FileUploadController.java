package com.chris.bulleyeadmin.common.controller;


import com.chris.bulleyeadmin.common.entity.JsonResult;
import com.chris.bulleyeadmin.common.pojo.AttachFiles;
import com.chris.bulleyeadmin.common.service.BaseService;
import com.chris.bulleyeadmin.common.service.FileUploadService;
import com.chris.bulleyeadmin.common.utils.OperationLog;
import com.chris.bulleyeadmin.system.pojo.Logger;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;


/**
 * @Auther: Chris
 * @Date: 2019-05-27 13:43
 * @Description:
 */
@Api(tags = "file", description = "文件上传")
@OperationLog("文件上传")
@RestController
@RequestMapping("/file")
public class FileUploadController extends BaseController<AttachFiles> {

    @Value("${serverConfig.isDebug}")
    private Boolean isDebug;

    @Value("${sysconfig.organizationId}")
    private String organizationId;

    @Value("${serverConfig.uploadPath}")
    private String uploadPath;

    @Autowired
    FileUploadService fileUploadService;


    @Override
    public BaseService<AttachFiles> getService() {
        return fileUploadService;
    }


    private static Map<String, String> images = new HashMap<String, String>();

    static {
        try {
            images.put("bmp", "bmp");
            images.put("tiff", "tiff");
            images.put("gif", "gif");
            images.put("png", "png");
            images.put("jpeg", "jpeg");
            images.put("jpg", "jpg");
        } catch (Exception e) {
            Logger.error(e.getMessage());
        }

    }

    /**
     * 通用文件上传用于文件上传组件
     *
     * @param multipartFile
     * @return
     */
    @ApiOperation(value = "文件上传", notes = "文件上传接口")
    @ApiImplicitParam(name = "文件上传接口", value = "参数:fileUpload")
    @PostMapping(value = "/uploadfile")
    @OperationLog("文件上传")
    public Object fileUpload(@RequestParam("fileUpload") MultipartFile multipartFile, String bizId) {
        System.out.println("hospitalId:===" + organizationId);
        System.out.println("bizId:===" + bizId);
        return fileUploadService.fileUpload(multipartFile, organizationId, bizId);
    }

    /**
     * 通用文件上传用于文件上传组件
     *
     * @param multipartFile
     * @return
     */
    /*@RequestMapping(value = "/jQueryFileUpload")
    public Object jQueryFileUpload(@RequestParam("fileUpload") MultipartFile multipartFile, String bizId, HttpServletResponse response) throws IOException {
        FileUploadResult result = fileUploadService.fileUpload(multipartFile, schoolId, bizId);
        String imageUrl = result.getUrl();
        String url = "";
        try {
            // 同域时直接返回json，跨域需redirect
            url = editorCallBackPath + "?error=0&url=" + imageUrl;
        } catch (Exception e) {
            e.printStackTrace();
            url = editorCallBackPath + "?error=1&message=" + "错误信息";
        }
        response.sendRedirect(url);
        return url;
    }

    @PostMapping("/upload")
    public String upload(@RequestParam("fileUpload") MultipartFile multipartFile, String bizId, HttpServletResponse response) throws IOException {

        FileUploadResult result = fileUploadService.fileUpload(multipartFile, schoolId, bizId);
        String imageUrl = result.getUrl();
        String url = "";
        try {
            // 同域时直接返回json，跨域需redirect
            url = editorCallBackPath + "?error=0&url=" + imageUrl;
        } catch (Exception e) {
            e.printStackTrace();
            url = editorCallBackPath + "?error=1&message=" + "错误信息";
        }
        response.sendRedirect(url);
        return url;
    }*/

    @ApiOperation(value = "根据业务ID获取附件列表", notes = "根据业务ID获取附件列表")
    @ApiImplicitParam(name = "根据业务ID获取附件列表", value = "参数:业务ID{id}")
    @GetMapping("/getFileListByBizId/{id}")
    @OperationLog("根据业务ID获取附件列表")
    public Object getFileListByBizId(@PathVariable String id) {
        Map<String, Object> map = new HashMap<>();
        map.put("bizId", id);
        return fileUploadService.getFileListByBizId(map);
    }

    @ApiOperation(value = "文件下载", notes = "根据ID进行下载")
    @ApiImplicitParam(name = "文件下载接口", value = "参数:{id}")
    @GetMapping(value = {"/download/{id}"})
    @OperationLog("文件下载")
    public void download(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") String id) {

        JsonResult jsonResult = fileUploadService.getById(id);
        AttachFiles attachFiles = (AttachFiles) jsonResult.getData();
        ServletOutputStream out = null;
        InputStream input = null;
        try {
            String fileName = attachFiles.getPath();
            out = response.getOutputStream();
            if (out != null) {
                String image_type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()) : null;
                Logger.info("filePath===UTF-8====================" + new String((uploadPath + fileName).getBytes("UTF-8")));
                Logger.info("filePath===GBK====================" + new String((uploadPath + fileName).getBytes("GBK")));
                Logger.info("image_type==========" + image_type);
                String filePath = uploadPath + fileName;
                File file = new File(filePath);
                if (!file.exists()) {
                    file = new File(new String(filePath.getBytes("UTF-8")));
                    Logger.info("Charset=========UTF-8==============" + file.getAbsolutePath());
                }
                if (!file.exists()) {
                    file = new File(new String(filePath.getBytes("GBK")));
                    Logger.info("Charset=========GBK==============" + file.getAbsolutePath());
                }

                input = new FileInputStream(file);
                response.setHeader("Content-Disposition", "attachment;fileName=\"" + new String(attachFiles.getName().getBytes("UTF-8"), "iso-8859-1") + "\"");
                //遍历，开始下载
                byte[] buffer = new byte[4096];// 缓冲区
                int n = -1;
                while ((n = input.read(buffer, 0, 4096)) > -1) {
                    out.write(buffer, 0, n);
                }
                out.flush();   //不可少
                response.flushBuffer();//不可少
                Logger.info("Download File Success!");
                //更新下载次数
                attachFiles.setDownloadCount(attachFiles.getDownloadCount() + 1);
                fileUploadService.update(attachFiles);
            }

        } catch (Exception e) {
            e.printStackTrace();
            Logger.error("Download Failed!");
            Logger.error("Download Failed!" + e.getMessage());
        } finally {
            try {
                if (out != null) {
                    out.close();
                    input.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @ApiOperation(value = "图片预览", notes = "根据ID进行预览或者下载")
    @ApiImplicitParam(name = "图片预览接口", value = "参数:{id}")
    @GetMapping(value = {"/view/{id}"})
    @OperationLog("图片预览/文件下载")
    public void view(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") String id) {

        JsonResult jsonResult = fileUploadService.getById(id);
        AttachFiles attachFiles = (AttachFiles) jsonResult.getData();
        ServletOutputStream out = null;
        InputStream input = null;
        try {
            String fileName = attachFiles.getPath();
            out = response.getOutputStream();
            if (out != null) {
                String image_type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()) : null;
                String filePath = uploadPath + fileName;
                File file = new File(filePath);
                if (!file.exists()) {
                    file = new File(new String(filePath.getBytes("UTF-8")));
                    Logger.info("Charset=========UTF-8==============" + file.getAbsolutePath());
                }
                if (!file.exists()) {
                    file = new File(new String(filePath.getBytes("GBK")));
                    Logger.info("Charset=========GBK==============" + file.getAbsolutePath());
                }

                input = new FileInputStream(file);
                if (image_type != null && images.containsKey(image_type.toLowerCase())) {
                    response.setContentType("text/html; charset=UTF-8");
                    response.setContentType("image/jpeg");
                    String fullFileName = uploadPath + fileName;
                    FileInputStream fis = new FileInputStream(fullFileName);
                    try {
                        int count = 0;
                        byte[] buffer = new byte[1024 * 1024];
                        while ((count = fis.read(buffer)) != -1)
                            out.write(buffer, 0, count);
                        out.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        if (out != null)
                            out.close();
                        if (fis != null)
                            fis.close();
                    }
                    Logger.info("View Image Success!");
                } else {
                    response.setHeader("Content-Disposition", "attachment;fileName=\"" + new String(attachFiles.getName().getBytes("UTF-8"), "iso-8859-1") + "\"");
                    //遍历，开始下载
                    byte[] buffer = new byte[4096];// 缓冲区
                    int n = -1;
                    while ((n = input.read(buffer, 0, 4096)) > -1) {
                        out.write(buffer, 0, n);
                    }
                    out.flush();   //不可少
                    response.flushBuffer();//不可少
                    Logger.info("Download File Success!");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            Logger.error("Download Failed!");
            Logger.error("Download Failed!" + e.getMessage());
        } finally {
            try {
                if (out != null) {
                    out.close();
                    input.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
