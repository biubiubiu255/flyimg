package com.flyimg.controller.page;

import com.flyimg.comm.enums.ResultCode;
import com.flyimg.comm.utils.AssertUtil;
import com.flyimg.comm.utils.DateUtils;
import com.flyimg.comm.utils.JWTUtil;
import com.flyimg.pojo.User;
import com.flyimg.pojo.WebPanel;
import com.flyimg.pojo.vo.Result;
import com.flyimg.service.RecordService;
import com.flyimg.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

@Api(tags = "用户信息类", produces = "application/json, application/xml")
@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RecordService recordService;


    @PostMapping("/password")
    @ApiOperation(tags = "修改密码", value = "标准")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "password", value = "旧密码", dataType = "String", required = true),
            @ApiImplicitParam(paramType = "query", name = "newPassword", value = "新密码", dataType = "String", required = true)
    })
    public Result password(@ApiIgnore HttpServletRequest request, String password, String newPassword) {
        // 如有参数错误，业务方法里，会抛出异常
        userService.updatePassword((int) request.getAttribute("userid"), password, newPassword);
        return Result.success();
    }


    @GetMapping("/statistics")
    @ApiOperation(tags = "获取用户统计数据", value = "标准")
    public Result Statistics(@ApiIgnore HttpServletRequest request) {
        // 如有参数错误，业务方法里，会抛出异常
        Integer days = Integer.valueOf(DateUtils.dateToString(new Date(), "yyyyMMdd"));
        Integer userid = (Integer)request.getAttribute("userid");
        WebPanel webPanel = recordService.getWebStatistics(userid, days);
        return Result.success(webPanel);
    }


    @GetMapping("/info")
    @ApiOperation(tags = "获取用户信息", value = "标准")
    public Result info(@ApiIgnore HttpServletRequest request) {
        // 如有参数错误，业务方法里，会抛出异常
        Integer userid = (Integer)request.getAttribute("userid");
        User user = userService.get(userid);
        if (user!=null){
            user.setPassword(null);
        }
        return Result.success(user);
    }



}
