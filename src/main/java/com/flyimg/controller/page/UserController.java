package com.flyimg.controller.page;

import com.flyimg.comm.enums.ResultCode;
import com.flyimg.comm.utils.*;
import com.flyimg.pojo.*;
import com.flyimg.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private SysConfigService sysConfigService;



    @PostMapping("/register")
    public Result register(HttpSession httpSession, @Valid User u, Integer verifyCode) {
        // 从request域里取出验证码，并校验，匹配失败则抛出异常
        Object verification = httpSession.getAttribute("verification");
        AssertUtil.isNotNullsEx(ResultCode.PARAM_VERIFY_CODE_EX, verification);
        AssertUtil.isTrue(verification.equals(verifyCode), ResultCode.PARAM_VERIFY_CODE_ERROR);

        // 写入user对象并入库
        SysConfig sysConfig = sysConfigService.get();
        u.setKey(CryptoUtils.encodeMD5(UUID.randomUUID().toString()));
        u.setLevel(0);
        u.setMemory(sysConfig.getUserMemory());
        u.setCreatedTime(System.currentTimeMillis());
        Integer userid = userService.register(u);

        // 登录成功，生成token
        String sign = JWTUtil.sign(userid.toString());

        // 帮浏览器设置cookie，使用双通道模式
        Cookie c = new Cookie("oss_u_token",sign);
        c.setPath("/");
        c.setMaxAge(3600);

        return Result.success(new HashMap(){{ put("userid", userid); put("oss_u_token", sign); }});
    }


    @PostMapping("/login")
    public Result login(HttpServletResponse response, HttpSession httpSession, String email, String password, Integer verifyCode) {
        // 从request域里取出验证码，并校验，匹配失败则抛出异常
        Object verification = httpSession.getAttribute("verification");
        AssertUtil.isNotNullsEx(ResultCode.PARAM_VERIFY_CODE_EX, verification);
        AssertUtil.isTrue(verification.equals(verifyCode), ResultCode.PARAM_VERIFY_CODE_ERROR);

        // 登录成功，生成token
        Integer userid = userService.login(email, password);
        String sign = JWTUtil.sign(userid.toString());

        // 帮浏览器设置cookie，使用双通道模式
        Cookie c = new Cookie("oss_u_token",sign);
        c.setPath("/");
        c.setMaxAge(3600);
        response.addCookie(c);
        //response.setHeader("Set-Cookie", "oss_u_token=" + sign);

        return Result.success(new HashMap(){{ put("userid", userid); put("oss_u_token", sign); }});
    }


    /**
     * 用户登出
     */
    @GetMapping("/logout")
    public Result exit(HttpServletRequest request, HttpServletResponse response, HttpSession session) {

        Cookie c = new Cookie("oss_u_token", "null");
        c.setPath("/");
        c.setMaxAge(0);
        response.addCookie(c);
        return Result.success();
    }


    /**
     * 获取会话验证码
     */
    @GetMapping("/verification")
    public String verification(HttpSession session, Integer type) {
        Random random = new Random();
        int numA = random.nextInt(100);
        int numB = random.nextInt(100);
        String result;
        Integer answer;
        switch (numA%3){
            case 0:
                result = numA + "加上" + numA%3 + "是多少？";
                answer = numA + numA%3;
                break;
            case 1:
                result = numA + "加上" + (numB+numB%3) + "是多少？";
                answer = numA + (numB+numB%3);
                break;
            case 2:
                result = numA + "3个" + numB%5 + "相乘是多少？";
                answer = numA * (numB+numB%3);
                break;
            default:
                result = numA + "7个" + numB%5 + "相乘是多少？";
                answer = numA * (numB+numB%3);
                break;
        }
        session.setAttribute("verification", answer);
        return result;
    }



}
