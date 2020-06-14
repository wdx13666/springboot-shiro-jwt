package com.wdx.springbootshirojwt.contrroller;

import com.wdx.springbootshirojwt.JwtToken;
import com.wdx.springbootshirojwt.utils.JwtUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @PostMapping("/login")
    public String login(String username, String password, String code) {
        try {
            Subject subject = SecurityUtils.getSubject();
            String token = JwtUtil.sign(username, "123456");
            JwtToken jwtToken = new JwtToken(token);
            subject.login(jwtToken);
            return "登录成功";
        } catch (UnknownAccountException ex) {
            return "用户不存在！";
        } catch (IncorrectCredentialsException ex) {
            return "用户名无效！";
        } catch (AuthenticationException ae) {
            return "密码错误！";
        }
    }


/*
    @RequestMapping(value = "/validateCode")
    public String validateCode(HttpServletResponse response) {
        // 设置响应的类型格式为图片格式
        response.setContentType("image/jpeg");
        //禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        ValidateCodeUtil code = new ValidateCodeUtil(110, 34, 4, 50);
        //SessionUtils.getSession().setAttribute(SessionUtils.IMG_CODE,code.getCode());
        try {
            code.write(response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
*/
}
