package com.chris.bulleyeadmin.common.utils;

import com.chris.bulleyeadmin.common.entity.FileUploadPath;
import com.chris.bulleyeadmin.common.entity.FileUploadResult;
import jdk.nashorn.internal.runtime.logging.DebugLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class FileUploadUtils {
    private static Logger logger = LoggerFactory.getLogger(DebugLogger.class);

    /**
     * 文件上传
     *
     * @param file
     * @param schoolId
     * @param fileUploadResult
     * @param fileUploadPath
     * @return
     */
    public static FileUploadResult fileUpload(MultipartFile file, String schoolId,
                                              FileUploadResult fileUploadResult, FileUploadPath fileUploadPath) {

        Map<String, Object> map = new HashMap<String, Object>();
        File targetFile = null;

        if (file.isEmpty()) {
            fileUploadResult.setStatus(0);
            fileUploadResult.setMsg("上传失败,文件不存在!");
        } else {

            String fileName = file.getOriginalFilename();
            //获取格式
            String suffix = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
            if (suffix.equals(".jpg") || suffix.equals(".png") || suffix.equals(".gif") || suffix.equals(".tif") || suffix.equals(".bmp") ||
                    suffix.equals(".rar") || suffix.equals(".zip") || suffix.equals(".xls") || suffix.equals(".doc") || suffix.equals(".ppt") ||
                    suffix.equals(".mdb") || suffix.equals(".htm") || suffix.equals(".pdf")) {
                try {
                    String filePath = getUploadPath(schoolId, fileUploadPath);
                    String finalName = getRandomNum() + fileName;
                    targetFile = new File(filePath, finalName);
                    String viewUrl = "/"+schoolId+"/"+finalName;
                    file.transferTo(targetFile);
                    fileUploadResult.setMsg("上传成功!");
                    fileUploadResult.setSuccess(true);
                    fileUploadResult.setStatus(1);
                    fileUploadResult.setError(0);
                    fileUploadResult.setUploaded("1");
                    fileUploadResult.setFileName(fileName);
                    fileUploadResult.setUrl(viewUrl);
                    fileUploadResult.setUrlpath(viewUrl);
                } catch (Exception e) {
                    e.printStackTrace();
                    fileUploadResult.setMsg("系统异常，上传失败!");
                    fileUploadResult.setStatus(0);
                    fileUploadResult.setUploaded("1");
                    fileUploadResult.setError(1);
                    fileUploadResult.setSuccess(false);
                }
            } else {
                fileUploadResult.setMsg("请上传图片,zip,rar,及office文件!");
                fileUploadResult.setStatus(1);
                fileUploadResult.setError(1);
                fileUploadResult.setSuccess(false);
            }

        }

        return fileUploadResult;
    }

    /**
     * 删除已上传的附件 by zj
     * relativePath
     *
     * @return
     */
    public static boolean removeFile(String filePath) {
        try {
            File f = new File(filePath);
            logger.debug("============文件路径：" + filePath);
            if (f.exists()) {
                f.delete();
                return true;
            }
        } catch (Exception ex) {
            logger.debug("============文件删除=====" + ex.getMessage());
        }

        return false;
    }


    /**
     * 获取上传路径
     *
     * @param hospitalId
     * @param fileUploadPath
     * @return
     */
    public static String getUploadPath(String hospitalId, FileUploadPath fileUploadPath) {
        String filePath = fileUploadPath.getUploadPath();

        if (!StringUtils.isEmpty(hospitalId)) {
            filePath += "/" + hospitalId;
        }

        //封装上传文件位置的全路径
        File uploadPat = new File(filePath);
        if (!uploadPat.exists() && !uploadPat.isDirectory()) {
            uploadPat.mkdirs();
        }

        return filePath;
    }


    /**
     * 时间戳随机数
     *
     * @return
     */
    public static String getRandomNum() {
        SimpleDateFormat simpledateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Random random = new Random();
        return simpledateFormat.format(new Date()) + random.nextInt(10000);
    }
}
