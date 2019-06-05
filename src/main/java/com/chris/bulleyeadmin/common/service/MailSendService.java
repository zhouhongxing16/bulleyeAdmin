package com.chris.bulleyeadmin.common.service;

import com.chris.bulleyeadmin.common.entity.JsonResult;
import com.chris.bulleyeadmin.system.pojo.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import java.io.File;


@Service
public class MailSendService {


    @Value("${spring.mail.username}")
    private String from;


    /**
     * spring 提供的邮件发送类
     */
    @Resource
    private JavaMailSender mailSender;


    @Async("mailAsync")
    public void sendSimpleEmail(String[] to, String subject, String content) {
        //创建简单邮件消息
        SimpleMailMessage message = new SimpleMailMessage();
        //设置发送人
        message.setFrom(from);
       /* //设置收件人
        message.setTo(to);*/

        //同时发送给多人
        message.setTo(to);

        //设置主题
        message.setSubject(subject);
        //设置内容
        message.setText(content);
        try {
            //执行发送邮件
            mailSender.send(message);
            Logger.info("简单邮件已经发送。");
        } catch (Exception e) {
            e.printStackTrace();
            Logger.error("发送简单邮件时发生异常！");
        }
    }

    @Async("mailAsync")
    public void sendHtmlEmail(String to, String subject, String content) throws Exception {
        //创建一个MINE消息
        MimeMessage message = mailSender.createMimeMessage();

        //true表示需要创建一个multipart message
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content, true);

        mailSender.send(message);
        Logger.info("html邮件发送成功");

    }

    /**通用邮件发送方法
     * @param to       {"xxx@qq.com","yyy@qq.com"}
     * @param subject
     * @param content
     * @param filePath
     */
    @Async("mailAsync")
    public Object sendEmail(String[] to, String subject, String content, String filePath) {
        JsonResult result = new JsonResult();
        //创建一个MINE消息
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            // true表示这个邮件是有附件的
            helper.setText(content, true);

            if (!StringUtils.isEmpty(filePath)) {
                //创建文件系统资源
                FileSystemResource file = new FileSystemResource(new File(filePath));
                String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
                //添加附件
                helper.addAttachment(fileName, file);
            }
            result.setSuccess(true);
            result.setMessage("邮件发送成功。");
            mailSender.send(message);
            Logger.info("邮件已经发送。");
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage("邮件发送失败。");
            Logger.error("发送邮件时发生异常！");
        }
        return result;

    }

    @Async("mailAsync")
    public void sendInlineResourceEmail(String to, String subject, String content, String rscPath, String rscId) {
        MimeMessage message = mailSender.createMimeMessage();

        try {

            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            FileSystemResource res = new FileSystemResource(new File(rscPath));

            //添加内联资源，一个id对应一个资源，最终通过id来找到该资源
            //添加多个图片可以使用多条 <img src='cid:" + rscId + "' > 和 helper.addInline(rscId, res) 来实现
            helper.addInline(rscId, res);
            mailSender.send(message);
            Logger.info("嵌入静态资源的邮件已经发送。");
        } catch (Exception e) {
            e.printStackTrace();
            Logger.error("发送嵌入静态资源的邮件时发生异常！");
        }

    }

}
