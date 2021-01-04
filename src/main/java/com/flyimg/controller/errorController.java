package com.flyimg.controller;

import com.alibaba.fastjson.JSONObject;
import com.flyimg.comm.enums.ResultCode;
import com.flyimg.pojo.MyException;
import com.flyimg.pojo.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class errorController {

    @RequestMapping("/errorException")
    public Result errorException(HttpServletRequest httpServletRequest) {
        Exception exception = (Exception)httpServletRequest.getAttribute("filter.error.exception");
        MyException ex = (MyException)exception.getCause();

        Result failure = ex!=null ? new Result(ex.getCode(), ex.getMsg()) : Result.failure(ResultCode.SYSTEM_INNER_ERROR);
        return failure;
    }

}
