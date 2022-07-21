package com.soft.forum.service.impl;

import com.soft.forum.Utils.Res;
import com.soft.forum.Utils.resCodeEnum;
import com.soft.forum.service.MailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

@Service
public class MailServiceImpl implements MailService {

    @Value("${spring.mail.username}")
    private String adminEmail;

    @Resource
    private JavaMailSender mailSender;

    @Resource
    private TemplateEngine templateEngine;

    @Override
    public Res sendMail(String activationUrl, String email) {
        // 创建邮件对象
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        // 获取信息编辑对象
        MimeMessageHelper message;
        try {
            message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            message.setFrom(adminEmail);
            message.setTo(email);
            message.setSentDate(new Date());
            message.setSubject("欢迎来到【南航校园】- 个人账号激活");
            Context context = new Context();
            context.setVariable("activationUrl",activationUrl);
            String text = templateEngine.process("active-account.html",context);
            message.setText(text,true);
        } catch (MessagingException e) {
            return Res.error(resCodeEnum.EMAIL_ERROR);
        }
        mailSender.send(mimeMessage);
        return Res.right();
    }


}
