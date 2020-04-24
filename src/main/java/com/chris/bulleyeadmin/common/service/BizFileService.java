package com.chris.bulleyeadmin.common.service;

import com.chris.bulleyeadmin.common.basemapper.BaseMapper;
import com.chris.bulleyeadmin.common.config.AliOSSConfig;
import com.chris.bulleyeadmin.common.entity.JsonResult;
import com.chris.bulleyeadmin.common.mapper.BizFileMapper;
import com.chris.bulleyeadmin.common.pojo.BizFile;
import com.chris.bulleyeadmin.common.utils.AuthUtil;
import com.chris.bulleyeadmin.common.utils.FileUtil;
import com.chris.bulleyeadmin.system.pojo.User;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.util.Date;

/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date: 2020-04-22 23:50
 */
@Service
public class BizFileService extends BaseService<BizFile> {

    @Value("${uploadConfig.prefix}")
    private String prefix;

    @Value("${uploadConfig.uploadPath}")
    private String uploadPath;

    @Value("${uploadConfig.storageType}")
    private String storageType;

    @Autowired
    private BizFileMapper bizFileMapper;

    @Override
    public BaseMapper<BizFile> getMapper() {
        return bizFileMapper;
    }

    @Autowired
    AliOSSConfig aliOSSConfig;


    public JsonResult uploadSingleFile(MultipartFile multipartFile) throws Exception {
        JsonResult result = new JsonResult();
        if (null == multipartFile) {
            result.setMessage("请上传一个文件！");
        } else {
            if ("local".equals(storageType)) {
                result = localUpload(multipartFile);
            } else if ("aliOSS".equals(storageType)) {

            } else if ("qiniu".equals(storageType)) {

            }
        }
        return result;
    }

    private JsonResult localUpload(MultipartFile multipartFile) throws Exception {
        JsonResult result = new JsonResult();
        User user = AuthUtil.getCurrentUser();
        String filename = multipartFile.getOriginalFilename();
        String departmentPath = user.getOrganizationId() + "/" + user.getDepartmentId();
        String path = uploadPath + "/" + departmentPath;
        String viewPath = prefix + "/" + departmentPath + "/" + filename;
        HashCode hash = Hashing.sha1().hashBytes(multipartFile.getBytes());
        BizFile file = new BizFile();
        file.setUploadStartTime(new Date());
        //上传文件
        FileUtil.uploadFile(multipartFile, path, filename);
        if(FileUtil.isPicture(FileUtil.getSuffix(filename))){
            String p = uploadPath+"/"+departmentPath+"/thumbnail";
            File targetFile = new File(p);
            if (!targetFile.exists()) {
                targetFile.mkdirs();
            }
            Image bi = ImageIO.read(new File(path+"/"+filename));
            file.setWidth(bi.getWidth(null));
            file.setHeight(bi.getHeight(null));
            String thumbnailPath = prefix + "/" + departmentPath + "/thumbnail/" + filename;
            file.setThumbnail(thumbnailPath);
            //图片压缩
            Thumbnails.of(path+"/"+filename).size(160, 160).toFile(path+"/thumbnail/"+filename);
        }
        file.setSize(multipartFile.getSize());
        file.setFileHash(hash.toString());
        file.setOriginalFileName(multipartFile.getOriginalFilename());
        file.setSuffix(FileUtil.getSuffix(multipartFile.getOriginalFilename()));
        file.setUploadEndTime(new Date());
        file.setStorageType(storageType);
        file.setFilePath(path);
        file.setFullFilePath(viewPath);
        file.setUserId(user.getId());
        file.setStatus(1);
        int count = bizFileMapper.insert(file);
        result.setSuccess(count > 0 ? true : false);
        result.setData(file);
        return result;
    }




    public JsonResult removeFile(String fileName) {
        JsonResult result = new JsonResult();
        result.setSuccess(false);
        if (StringUtils.isEmpty(fileName)) {
            result.setMessage("[" + fileName + "]删除文件失败：文件key为空");
        } else {
            if ("local".equals(storageType)) {
                BizFile file = new BizFile();
                file.setOriginalFileName(fileName);
                file.setStorageType(storageType);
                int count = bizFileMapper.delete(file);
                result.setSuccess(count > 0 ? true : false);
                result.setMessage("删除成功");
                result.setSuccess(true);
            } else if ("aliOSS".equals(storageType)) {

            } else if ("qiniu".equals(storageType)) {

            }
        }
        return result;
    }

}
