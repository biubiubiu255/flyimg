package com.flyimg.components.filter;

import javax.servlet.*;
import java.io.IOException;


/**
 * 非框架层异常监听器，对所有filter进行异常拦截，然后统一forward到/errorException进行标准风格输出
 * @author Asher
 */
public class ExceptionFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        try {
            chain.doFilter(request, response);
            //response.setContentType("text/html;charset=UTF-8");
        } catch (Exception e) {
            // 异常捕获，发送到error controller
            request.setAttribute("filter.error.exception", e);
            //将异常分发到/error/exthrow控制器
            request.getRequestDispatcher("/errorException").forward(request, response);
        }
    }

    @Override
    public void destroy() {

    }

}
