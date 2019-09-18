package org.spring.springboot.test;

import org.apache.commons.mail.HtmlEmail;

/**
 * 使用commons.mail发送邮件
 */
public class EmailTest {

    public static void main(String[] args) {
        HtmlText htmlText = new HtmlText();
        try {
            HtmlEmail email = new HtmlEmail();
            email.setDebug(true);//开启debug模式
            //这里是SMTP发送服务器的名字
            email.setHostName("smtp.office365.com");//注意本测试只针对outlook邮件(邮箱加密方式在邮箱设置自寻配置)
            //端口号不为空时,用户自定义的端口号为SMTP发送服务器端口号
            email.setSmtpPort(587);
           // email.setSendPartial(true);
            email.setTLS(true);//outlook是使用的TLS协议各大邮箱协议均不同
            //字符编码集的设置
            email.setCharset("UTF-8");
            //收件人的邮箱
            email.addTo("xxxx@qq.com");
            //发送人的邮箱
            String sendAddress = "xxxx@outlook.com";
            String value = sendAddress.split("@")[0];
            System.out.println("value" + value);
            email.setFrom(sendAddress,value);//发送人邮箱
            // 如果需要认证信息的话，设置认证：用户名-密码。分别为发件人在邮件服务器上的注册名称和密码
            email.setAuthentication(sendAddress, "xxx@klo");
            // 要发送的邮件主题
            email.setSubject("《GreedFall》现已在 Steam 上推出！");
            // 要发送的信息，由于使用了HtmlEmail，可以在邮件内容中使用HTML标签

            email.addHeader("X-Mailer","Microsoft Outlook Express 6.00.2900.2869");//可不填
            email.setHtmlMsg(htmlText.value);//html方式
       //     email.attach(attachment);//附件
            // 发送
            email.send();
            System.out.println("成功");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("失败");
        }
    }
}
