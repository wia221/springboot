package org.spring.springboot.util;

import java.util.Random;

/**
 * 生成验证码
 */
public class VerifyCodeUtil {
    //验证码生成范围
    //public static final String VERIFY_CODES = "23456789ABCDEFGHJKLMNPQRSTUVWXYZ";
    public static final String VERIFY_CODES = "0123456789";

    /**
     * 使用系统默认字符源生成验证码
     * @param verifySize 验证码长度
     * @return
     */
    public static String generateVerifyCode(int verifySize){
        return generateVerifyCode(verifySize, VERIFY_CODES);
    }

    /**
     * 使用指定源生成验证码
     * @param verifySize 验证码长度
     * @param sources 验证码字符源
     * @return
     */
    public static String generateVerifyCode(int verifySize, String sources){
        if(sources == null || sources.length() == 0){
            sources = VERIFY_CODES;
        }
        int codesLen = sources.length();
        Random rand = new Random(System.currentTimeMillis());
        StringBuilder verifyCode = new StringBuilder(verifySize);
        for(int i = 0; i < verifySize; i++){
            verifyCode.append(sources.charAt(rand.nextInt(codesLen - 1)));
        }
        return verifyCode.toString();
    }

    public static String getCode(String code, String email) {
        String html = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<div>\n" +
                "    <br>\n" +
                "     尊敬的用户： <br>\n" +
                "     您的登录邮箱是 "+ email +"。<br>\n" +
                "     验证码：<u>"+ code +"</u> <br>\n" +
                "     该验证码在 3分钟有效，3分钟后需要重新发送验证邮件 <br>\n" +
                "     此为系统邮件，请勿回复 <br>\n" +
                "     蜜投广告平台 <br>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>";
        return html;
    }
}
