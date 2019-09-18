package org.spring.springboot.test;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

/**
 * java自带方式不完善
 */
public class EmailTestTwo {
    public static void main(String[] args) {
        String from = "xxx@outlook.com";// 发件人电子邮箱
        String to = "xxx@qq.com";// 收件人电子邮箱
        //获取系统属性，主要用于设置邮件相关的参数。
        Properties properties = System.getProperties();
        //设置邮件传输服务器，由于本次是发送邮件操作，所需我们需要配置smtp协议，按outlook官方同步邮件的要求，依次配置协议地址，端口号和加密方法
        properties.setProperty("mail.smtp.host", "smtp.office365.com");
        properties.setProperty("mail.smtp.port", "587");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        //用户验证并返回Session，开启用户验证，设置发送邮箱的账号密码。
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.debug", "true");// 设置是否显示debug信息 true 会在控制台显示相关信息
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("xxx@outlook.com", "xxxx");//账号密码
            }
        });
        //创建MimeMessage消息对象，消息头配置了收发邮箱的地址，消息体包含了邮件标题和邮件内容。接收者类型：TO代表直接发送，CC代表抄送，BCC代表秘密抄送。
        try {
            MimeMessage message = new MimeMessage(session);
            message.addHeader("X-Mailer","Microsoft Outlook Express 6.00.2900.2869");
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("《GreedFall》现已在 Steam 上推出！");
//          message.setText("具体内容请见附件!");
//		    Transport.send(message);
//		    System.out.println("发送成功！");

            // 1.创建复合消息体
            Multipart multipart = new MimeMultipart();
            // 2.添加附件
            BodyPart filePart = new MimeBodyPart();
            String filePath = "/data/ISC2019130.xlsx";
            DataSource source = new FileDataSource(filePath);
            filePart.setDataHandler(new DataHandler(source));
            filePart.setFileName(source.getName());
           // multipart.addBodyPart(filePart);
            // 3.添加文本内容
            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText("测试包含文本和附件的邮件！");
            multipart.addBodyPart(textPart);
            // 4.绑定消息对象
            message.setContent(multipart);
            // 5.发送邮件
            Transport.send(message);
            System.out.println("成功");
        } catch (MessagingException e) {
            System.out.println("失败");
            e.printStackTrace();
        }

    }

}
