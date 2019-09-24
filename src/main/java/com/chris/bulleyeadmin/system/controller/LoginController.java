package com.chris.bulleyeadmin.system.controller;

import com.chris.bulleyeadmin.common.utils.OperationLog;
import com.chris.bulleyeadmin.common.utils.ValidateCodeUtils;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/***
 *
 */
@OperationLog("登陆")
@RestController
@Api("swaggerDemoController相关的api")
public class LoginController {


    @GetMapping(value = {"/imagevcode"}, produces = {"image/jpeg"})
    public byte[] validateCodeImage(HttpServletRequest request) throws IOException {
        BufferedImage image = ValidateCodeUtils.getValidateCodeImage(request.getSession());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", baos);
        return baos.toByteArray();
    }
}
