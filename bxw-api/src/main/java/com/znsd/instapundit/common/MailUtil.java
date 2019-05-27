package com.znsd.instapundit.common;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 邮件工具类
 */
public class MailUtil {

    public static Map<String, Template> TEMPLATE = new HashMap<>();

    static {
        TEMPLATE.put("sms", new Template("尊敬的用户:您好! \n 注册验证码为:", "\n (有效期为90秒)"));
        TEMPLATE.put("email", new Template("尊敬的用户:您好! \n 您的验证码为:", "\n (有效期为90秒)"));
        TEMPLATE.put("pass", new Template("尊敬的用户:您好! \n 您修改密码的验证码为:", "\n (有效期为90秒)"));

    }

    /**
     * 发送邮件
     *
     * @param to   给谁发
     * @param text 发送内容
     */
    public static void send_mail(String to, String text) throws MessagingException, javax.mail.MessagingException {
        //创建连接对象 连接到邮件服务器
        Properties properties = new Properties();
        //设置发送邮件的基本参数
        //发送邮件服务器(注意，此处根据你的服务器来决定，如果使用的是QQ服务器，请填写smtp.qq.com)
        // properties.put("mail.smtp.host", "smtp.huic188.com");
        properties.put("mail.smtp.host", "smtp.qq.com");
        //发送端口（根据实际情况填写，一般均为25）
        properties.put("mail.smtp.port", "25");
        properties.put("mail.smtp.auth", "true");
        //设置发送邮件的账号和密码
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                //两个参数分别是发送邮件的账户和密码(注意，如果配置后不生效，请检测是否开启了 POP3/SMTP 服务，QQ邮箱对应设置位置在: [设置-账户-POP3/IMAP/SMTP/Exchange/CardDAV/CalDAV服务])
                return new PasswordAuthentication("1464495648@qq.com", "vyiehgaqmvkoheai");//hxzswopyjkrzgfhf
            }
        });

        //创建邮件对象
        Message message = new MimeMessage(session);
        //设置发件人
        message.setFrom(new InternetAddress("1464495648@qq.com"));
        //设置收件人
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        //设置主题
        message.setSubject("知识分享会账号注册");
        //设置邮件正文  第二个参数是邮件发送的类型
        message.setContent(text, "text/html;charset=UTF-8");
        //发送一封邮件
        Transport.send(message);
    }

    //注册获取验证码
    public static String getRegister(String email) {
        Random random = new Random();
        String authCode = random.achieveCode();
        try {
            MailUtil.send_mail(email, TEMPLATE.get("sms").getPrefix() + authCode + TEMPLATE.get("sms").getSuffix());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return authCode;
    }

    //获取验证码
    public static String getEmail(String email) {
        Random random = new Random();
        String authCode = random.achieveCode();
        try {
            MailUtil.send_mail(email, TEMPLATE.get("email").getPrefix() + authCode + TEMPLATE.get("email").getSuffix());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return authCode;
    }

    //修改密码获取验证码
    public static String getPass(String email) {
        Random random = new Random();
        String authCode = random.achieveCode();
        try {
            MailUtil.send_mail(email, TEMPLATE.get("pass").getPrefix() + authCode + TEMPLATE.get("pass").getSuffix());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return authCode;
    }
}
