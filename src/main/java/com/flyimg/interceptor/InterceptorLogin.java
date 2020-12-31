package com.flyimg.interceptor;

import com.flyimg.comm.enums.ResultCode;
import com.flyimg.comm.utils.JWTUtil;
import com.flyimg.comm.utils.WebUtil;
import com.flyimg.pojo.MyException;
import com.flyimg.pojo.User;
import com.flyimg.service.impl.UserServiceImpl;
import com.flyimg.comm.utils.SpringContextHolder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Component
public class InterceptorLogin implements HandlerInterceptor {

    /**
     * 进入controller层之前拦截请求
     * 这个方法是在访问接口之前执行的，我们只需要在这里写验证登陆状态的业务逻辑，就可以在用户调用指定接口之前验证登陆状态了
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 从request里取出token，支持cooke与header两种方式
        String token  = WebUtil.getCookie(request, "oss_u_token");
        if (StringUtils.isNotEmpty(token)){
            token = request.getHeader("oss_u_token");
        }

        // 使用jwt校验token是否有效，无效则抛出异常，全局异常拦截器会处理成标准json返回
        String userid = JWTUtil.parseValueAndVerify(token);
        if (StringUtils.isNotEmpty(userid)){
            request.setAttribute("userid", Integer.valueOf(userid));
        }else {
            throw new MyException(ResultCode.PERMISSION_NO_ACCESS);
        }
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }

}
