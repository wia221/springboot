package org.spring.springboot.util;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EmailUtil {
    private static String hostName;
    public static String getHostName() {
        return hostName;
    }
    @Value("${email.hostName}")
    public void setHostName(String hostName) {//一定要注意这里不是静态方法
        EmailUtil.hostName = hostName;
    }

    private static String port;
    public static String getPort() {
        return port;
    }
    @Value("${email.smtpPort}")
    public void setPort(String port) {//一定要注意这里不是静态方法
        EmailUtil.port = port;
    }

    private static String form;
    public static String getForm() {
        return form;
    }
    @Value("${email.setFrom}")
    public void setForm(String form) {//一定要注意这里不是静态方法
        EmailUtil.form = form;
    }

    private static String userName;
    public static String getUserName() {
        return userName;
    }
    @Value("${email.userName}")
    public void setUserName(String userName) {//一定要注意这里不是静态方法
        EmailUtil.userName = userName;
    }

    private static String password;
    public static String getPassword() {
        return password;
    }
    @Value("${email.password}")
    public void setPassword(String password) {//一定要注意这里不是静态方法
        EmailUtil.password = password;
    }

    private static String subject;
    public static String getSubject() {
        return subject;
    }
    @Value("${email.subject}")
    public void setSubject(String subject) {//一定要注意这里不是静态方法
        EmailUtil.subject = subject;
    }

    public static boolean sendEmail(String recipient, String code) throws EmailException {
        HtmlEmail email = new HtmlEmail();
        email.setDebug(false);
        //这里是SMTP发送服务器的名字
        email.setHostName(hostName);
        //端口号不为空时,用户自定义的端口号为SMTP发送服务器端口号
        int smtPort = Integer.valueOf(port).intValue();
        email.setSmtpPort(smtPort);
        //协议
        email.setSSLOnConnect(true);
        //字符编码集的设置
        email.setCharset("UTF-8");
        //收件人的邮箱
        email.addTo(recipient);
        //发送人的邮箱
        email.setFrom(form);
        // 如果需要认证信息的话，设置认证：用户名-密码。分别为发件人在邮件服务器上的注册名称和密码
        email.setAuthentication(userName, password);
        // 要发送的邮件主题
        email.setSubject(subject);
        // 要发送的信息，由于使用了HtmlEmail，可以在邮件内容中使用HTML标签
        email.setHtmlMsg(VerifyCodeUtil.getCode(code,recipient));
        // email.attach(attachment);附件
        // 发送
        email.send();
        return true;
    }
}
