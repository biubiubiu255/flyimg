package com.flyimg.controller;

import com.flyimg.comm.enums.ResultCode;
import com.flyimg.pojo.vo.MyException;
import com.flyimg.pojo.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.validation.ConstraintViolationException;

/**
 * Created by jonty. 统一异常处理
 * ControllerAdvice类注解, 作用于 整个 Spring 工程. ControllerAdvice 注解定义了一个全局的异常处理器
 * 需要注意的是, ExceptionHandler 的优先级比 ControllerAdvice 高
 *   即 Controller 抛出的异常如果既可以让 ExceptionHandler 标注的方法处理,又可以让 ControllerAdvice 标注的类中的方法处理,
 *   则优先让 ExceptionHandler 标注的方法处理.
 *
 * Spring Boot 中, 当用户访问了一个不存在的链接时, Spring 默认会将页面重定向到 /error 上,而不会抛出异常.
 * 既然如此,那我们就告诉 Spring Boot,当出现 404错误时,抛出一个异常即可.在 application.properties 中添加两个配置:
 * spring.mvc.throw-exception-if-no-handler-found=true
 * spring.resources.add-mappings=false
 *  上面的配置中, 第一个 spring.mvc.throw-exception-if-no-handler-found 告诉 SpringBoot 当出现 404 错误时, 直接抛出异常. 第二个 spring.resources.add-mappings 告诉 SpringBoot 不要为我们工程中的资源文件建立映射. 这两个配置正是 RESTful 服务所需要的
 * @author wen
 */
@Slf4j
@EnableWebMvc
@RestControllerAdvice
public class GlobalExceptionHandler
{

    /**
     * 400 - Bad Request
     */
    @ExceptionHandler({HttpMessageNotReadableException.class,
            MissingServletRequestParameterException.class,
            BindException.class,
            ServletRequestBindingException.class,
            MethodArgumentNotValidException.class,
            ConstraintViolationException.class})
    public Result handleHttpMessageNotReadableException(Exception e) {
        log.warn("参数解析失败: " + e.getClass().getName()+": "+e.getMessage());
        Throwable cause = e.getCause();
        String localizedMessage = e.getLocalizedMessage();
        if (localizedMessage!=null && localizedMessage.contains("default message")){
            localizedMessage = StringUtils.substringAfterLast(localizedMessage, "default message");
        }
        if (e instanceof BindException){
            return Result.failure(ResultCode.PARAM_TYPE_BIND_ERROR, localizedMessage);
        }
        else if (e instanceof MissingServletRequestParameterException){
            return Result.failure(ResultCode.PARAM_NOT_COMPLETE);
        }
        return Result.failure(ResultCode.PARAM_TYPE_BIND_ERROR);
    }

    /**
     * 405 - Method Not Allowed
     * 带有@ResponseStatus注解的异常类会被ResponseStatusExceptionResolver 解析
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.warn("不支持当前请求方法: "+ e.getClass().getName()+": "+e.getMessage());
        return Result.failure(ResultCode.PARAM_METHOD_NOT_ALLOWED);
    }

    /**
     * 数据解析异常.
     */
    @ExceptionHandler(NumberFormatException.class)
    public Result handleException(NumberFormatException e) {
        e.printStackTrace();
        log.warn("数据解析异常: " + e.getClass().getName()+": "+e.getMessage());
        return Result.failure(ResultCode.DATA_PARSE_ERROR);
    }


    /**
     * @RequestParam中的参数解析，异常时会到这里
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public Result handleException(MethodArgumentTypeMismatchException e) {
        log.warn("参数类型异常: " + e.getClass().getName()+": "+e.getMessage());
        return Result.failure(ResultCode.PARAM_TYPE_BIND_ERROR);
    }


    /**
     * NoHandlerFoundException
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NoHandlerFoundException.class)
    public Result handleException(NoHandlerFoundException e) {
        log.warn("访问地址不存在: " + e.getClass().getName()+": "+e.getMessage());
        return Result.failure(ResultCode.HTTP_NOT_FOUND);
    }

    /**
     * 其他全局异常在此捕获
     */
    @ExceptionHandler(Throwable.class)
    public Result handleException(Throwable e) {
        log.warn(e.getClass().getName() +": "+ e.getMessage());
        Result result = null;
        if (e instanceof MyException){
            MyException exs = (MyException)e;
            result = new Result(exs.getCode(), exs.getMsg());
        }else {
            e.printStackTrace();
            result = Result.failure(ResultCode.SYSTEM_INNER_ERROR);
        }
        return result;
    }


}
