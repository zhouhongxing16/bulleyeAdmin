package com.chris.bulleyeadmin.common.controller;

import com.chris.bulleyeadmin.common.config.AccessLimit;
import com.chris.bulleyeadmin.common.config.NoRepeatSubmit;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;

/**
 * @author Chris
 * @date 2022-01-05 14:52
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @PostMapping("/rsa/{str}")
    @NoRepeatSubmit
    public String test(@PathVariable String str) throws Exception {
        byte[] b = str.getBytes(StandardCharsets.UTF_8);
        return new String(b);
    }

    @PostMapping("/limit")
    @AccessLimit(sec = 5,limit = 5)
    public String limit() throws Exception {
        return "new String(b)";
    }
}
