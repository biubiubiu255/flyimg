package com.flyimg.controller;

import com.flyimg.comm.enums.ResultCode;
import com.flyimg.pojo.vo.MyException;
import com.flyimg.pojo.vo.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class errorController {

    @RequestMapping("/errorException")
    public Result errorException(HttpServletRequest httpServletRequest) {
        Exception exception = (Exception)httpServletRequest.getAttribute("filter.error.exception");

        Result failure;
        if (exception.getCause() instanceof MyException){
            MyException ex = (MyException)exception.getCause();
            failure = new Result(ex.getCode(), ex.getMsg());
        }else {
            failure = new Result(ResultCode.SYSTEM_INNER_ERROR.getCode(), exception.getMessage());
        }

        return failure;
    }

}
