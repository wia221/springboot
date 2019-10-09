package org.spring.springboot.controller;

import org.apache.log4j.Logger;
import org.spring.springboot.util.ImageUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/verifyCode/")
public class VerifyCodeImgController {
    private Logger logger = Logger.getLogger(VerifyCodeImgController.class);

    @GetMapping(value = "/code")
    public String getCode(HttpServletRequest request, HttpServletResponse response) throws Exception{
        response.setContentType("image/jpeg");
        //禁止图像缓存
        response.setHeader("Pragma","no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        HttpSession session = request.getSession();
        ImageUtil imageUtil = new ImageUtil(120, 40, 5,30);
        session.setAttribute("code", imageUtil.getCode());
        imageUtil.write(response.getOutputStream());
        return null;
    }
}
