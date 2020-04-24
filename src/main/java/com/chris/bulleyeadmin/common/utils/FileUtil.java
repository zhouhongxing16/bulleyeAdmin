package com.chris.bulleyeadmin.common.utils;

import com.chris.bulleyeadmin.system.pojo.Logger;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Arrays;
import java.util.Properties;
import java.util.UUID;

public class FileUtil {
    private static final String[] PICTURE_SUFFIXS = {".jpg", ".jpeg", ".png", ".gif", ".bmp", ".svg"};

    public static boolean isPicture(String suffix) {
        return !StringUtils.isEmpty(suffix) && Arrays.asList(PICTURE_SUFFIXS).contains(suffix.toLowerCase());
    }

    public static void uploadFile(MultipartFile multipartFile, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath+"/"+fileName);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        multipartFile.transferTo(targetFile);
//        setFileReadMode(targetFile.getAbsolutePath());
       /* FileOutputStream out = new FileOutputStream(filePath + fileName);
        out.write(file);
        out.flush();
        out.close();*/
    }

    private static void setFileReadMode(String path) {
        //默认增加可读权限
        String accessFile = "chmod a+r " + path;
        try {
            Properties prop = System.getProperties();
            String os = prop.getProperty("os.name");
            if (os != null && os.toLowerCase().indexOf("windows") == -1) {
                Runtime.getRuntime().exec(accessFile);
            }
        } catch (Exception ex) {
            Logger.error(ex.getMessage());
        }
    }

    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static String renameToUUID(String name) {
        return UUID.randomUUID() + "." + name.substring(name.lastIndexOf(".") + 1);
    }
    public static String getSuffixByUrl(String imgUrl) {
        String defaultSuffix = ".png";
        if (StringUtils.isEmpty(imgUrl)) {
            return defaultSuffix;
        }
        String fileName = imgUrl;
        if(imgUrl.contains("/")) {
            fileName = imgUrl.substring(imgUrl.lastIndexOf("/"));
        }
        String fileSuffix = getSuffix(fileName);
        return StringUtils.isEmpty(fileSuffix) ? defaultSuffix : fileSuffix;
    }

    public static String getSuffix(String fileName) {
        int index = fileName.lastIndexOf(".");
        index = -1 == index ? fileName.length() : index;
        return fileName.substring(index);
    }
}
