package org.spring.springboot.test;

import org.apache.commons.mail.HtmlEmail;

/**
 * 使用commons.mail发送邮件
 */
public class EmailTest {
    /**
     * TLS协议
     * TLS(Transport Layer Security，传输层安全协议)，用于两个应用程序之间提供保密性和数据完整性。该协议由两层组成：TLS记录协议和TLS握手协议。
     *
     * SSL是Netscape开发的专门用户保护Web通讯的，目前版本为3.0。最新版本的TLS 1.0是IETF(工程任务组)制定的一种新的协议，它建立在SSL 3.0协议规范之上，是SSL 3.0的后续版本。两者差别极小，可以理解为SSL 3.1，它是写入了RFC的。
     *
     * SSL协议与TLS协议关系
     * 最新版本的TLS（Transport Layer Security，传输层安全协议）是IETF（Internet Engineering Task Force，Internet工程任务组）制定的一种新的协议，它建立在SSL 3.0协议规范之上，是SSL 3.0的后续版本。在TLS与SSL3.0之间存在着显著的差别，主要是它们所支持的加密算法不同，所以TLS与SSL3.0不能互操作。
     * @param args
     */
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
            email.setAuthentication(sendAddress, "xxx");
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
