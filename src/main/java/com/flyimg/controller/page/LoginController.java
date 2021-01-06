package com.flyimg.controller.page;

import com.flyimg.comm.enums.ResultCode;
import com.flyimg.comm.utils.*;
import com.flyimg.pojo.*;
import com.flyimg.pojo.vo.Result;
import com.flyimg.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Random;

@Api(tags = "登录注册类", produces = "application/json, application/xml")
@RestController
@RequestMapping(value = "/user")
public class LoginController {
    @Autowired
    private UserService userService;


    @PostMapping("/register")
    @ApiOperation(tags = "用户注册", value = "标准")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "email", value = "用户名", dataType = "String", required = true),
            @ApiImplicitParam(paramType = "query", name = "password", value = "密码", dataType = "String", required = true),
            @ApiImplicitParam(paramType = "query", name = "verifyCode", value = "验证码", dataType = "String", required = true)
    })
    public Result register(@ApiIgnore HttpSession httpSession, @ApiIgnore HttpServletResponse response, @Valid @ApiIgnore User user, Integer verifyCode) {
        // 校验session里是否有验证码，否则抛出异常
        Object verification = httpSession.getAttribute("verification");
        AssertUtil.isNotNullsEx(ResultCode.PARAM_VERIFY_CODE_EX, verification);
        // 校验验证码是否一致
        AssertUtil.isTrue(verification.equals(verifyCode), ResultCode.PARAM_VERIFY_CODE_ERROR);
        // 校验参数是否为空
        AssertUtil.isNotNullsEx(ResultCode.PARAM_NOT_COMPLETE, user, user.getEmail(), user.getPassword());
        // 进行注册
        Integer userid = userService.register(user);
        // 注册完成，生成token
        String sign = JWTUtil.sign(userid.toString());
        Cookie c = new Cookie("oss_u_token",sign);
        c.setPath("/");
        c.setMaxAge(3600);
        response.addCookie(c);
        return Result.success(new HashMap(){{ put("userid", userid); put("oss_u_token", sign); }});
    }



    @PostMapping("/login")
    @ApiOperation(tags = "用户登录", value = "登录成功返回用户id及token")
    public Result login(@ApiIgnore HttpServletResponse response, @ApiIgnore HttpSession httpSession, String email, String password, Integer verifyCode) {
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
    @ApiOperation(tags = "用户注销", value = "返回true或者fase，并清除cookie")
    public Result exit(@ApiIgnore HttpServletResponse response) {
        Cookie c = new Cookie("oss_u_token", "null");
        c.setPath("/");
        c.setMaxAge(0);
        response.addCookie(c);
        response.addCookie(c);
        return Result.success();
    }


    /**
     * 获取会话验证码
     */
    @GetMapping(value = "/verification", produces = "text/html;charset=UTF-8")
    @ApiOperation(tags = "获取验证码", value = "可用于各类业务，返回纯text，非json")
    public String verification(@ApiIgnore HttpSession session) {
        Random random = new Random();
        int numA = random.nextInt(15);
        int numB = random.nextInt(15);
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
                result = numA + "个" + numB%5 + "相乘是多少？";
                answer = numA * (numB%5);
                break;
            default:
                result = numA + "个" + numB%5 + "相乘是多少？";
                answer = numA * (numB%5);
                break;
        }
        session.setAttribute("verification", answer);

        return result;
    }



}
