package com.chris.bulleyeadmin.common.service;

import com.chris.bulleyeadmin.common.basemapper.BaseMapper;
import com.chris.bulleyeadmin.common.config.AliOSSConfig;
import com.chris.bulleyeadmin.common.entity.JsonResult;
import com.chris.bulleyeadmin.common.mapper.BizFileMapper;
import com.chris.bulleyeadmin.common.pojo.BizFile;
import com.chris.bulleyeadmin.common.utils.AuthUtil;
import com.chris.bulleyeadmin.common.utils.DateUtils;
import com.chris.bulleyeadmin.common.utils.FileUtil;
import com.chris.bulleyeadmin.common.utils.SnowFlake;
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
import java.io.IOException;
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

    public JsonResult upload(MultipartFile[] files) throws IOException {
        JsonResult result = new JsonResult();
        User user = AuthUtil.getCurrentUser();
        if (null == files || files.length == 0) {
            result.setMessage("请至少上传一个文件！");
        } else {

            result.setSuccess(true);
        }

        // 文件存储入OSS，Object的名称为fileKey。详细请参看“SDK手册 > Java-SDK > 上传文件”。
        // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/upload_object.html?spm=5176.docoss/user_guide/upload_object

        return result;
    }

    public JsonResult uploadSingleFile(MultipartFile multipartFile) throws Exception {
        JsonResult result = new JsonResult();
        if (null == multipartFile) {
            result.setMessage("请上传一个文件！");
        } else {
            if ("local".equals(storageType)) {
                result = localUpload(multipartFile);
            } else if ("aliOSS".equals(storageType)) {
                result = aliOSSUpload(multipartFile);
            } else if ("qiniu".equals(storageType)) {

            }
        }
        return result;
    }

    private JsonResult localUpload(MultipartFile multipartFile) throws Exception {
        JsonResult result = new JsonResult();
        User user = AuthUtil.getCurrentUser();
        String filename = multipartFile.getOriginalFilename();
        if (filename.length() > 40) {
            SnowFlake snowFlake = new SnowFlake(1, 1);
            filename = DateUtils.getCurrentTimeMillis() + snowFlake.nextId() + FileUtil.getSuffix(multipartFile.getOriginalFilename());
        }
        String departmentPath = user.getOrganizationId() + "/" + user.getDepartmentId();
        String path = uploadPath + "/" + departmentPath;
        String viewPath = prefix + "/" + departmentPath + "/" + filename;

        //计算文件hash
        HashCode hash = Hashing.sha1().hashBytes(multipartFile.getBytes());
        BizFile file = new BizFile();
        file.setOrganizationId(user.getOrganizationId());
        file.setDepartmentId(user.getDepartmentId());

        file.setUploadStartTime(new Date());
        //上传文件
        FileUtil.uploadFile(multipartFile, path, filename);

        //计算图片信息
        if (FileUtil.isPicture(FileUtil.getSuffix(filename))) {
            String p = uploadPath + "/" + departmentPath + "/thumbnail";
            File targetFile = new File(p);
            if (!targetFile.exists()) {
                targetFile.mkdirs();
            }
            Image bi = ImageIO.read(new File(path + "/" + filename));
            file.setWidth(bi.getWidth(null));
            file.setHeight(bi.getHeight(null));
            String thumbnailPath = prefix + "/" + departmentPath + "/thumbnail/" + filename;
            file.setThumbnail(thumbnailPath);
            //图片压缩
            Thumbnails.of(path + "/" + filename).size(160, 160).toFile(path + "/thumbnail/" + filename);
        }


        file.setSize(multipartFile.getSize());
        file.setFileHash(hash.toString());
        file.setOriginalFileName(filename);
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

    private JsonResult aliOSSUpload(MultipartFile multipartFile) throws IOException {
        JsonResult result = new JsonResult();
        User user = AuthUtil.getCurrentUser();
        if (null == multipartFile) {
            result.setMessage("请上传一个文件！");
        } else {

        }
        // 文件存储入OSS，Object的名称为fileKey。详细请参看“SDK手册 > Java-SDK > 上传文件”。
        // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/upload_object.html?spm=5176.docoss/user_guide/upload_object

        return result;
    }


    public JsonResult removeFile(String fileName) {
        JsonResult result = new JsonResult();
        User user = AuthUtil.getCurrentUser();
        result.setSuccess(false);
        if (StringUtils.isEmpty(fileName)) {
            result.setMessage("[" + fileName + "]删除文件失败：文件key为空");
        } else {
            if ("local".equals(storageType)) {
                BizFile file = new BizFile();
                file.setOriginalFileName(fileName);
                file.setStorageType(storageType);
                String departmentPath = user.getOrganizationId() + "/" + user.getDepartmentId();
                String path = uploadPath + "/" + departmentPath;
                FileUtil.deleteFile(path + "/" + fileName);
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
