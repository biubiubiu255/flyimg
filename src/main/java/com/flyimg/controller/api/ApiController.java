package com.flyimg.controller.api;

import com.flyimg.comm.enums.ResultCode;
import com.flyimg.pojo.*;
import com.flyimg.service.*;
import com.flyimg.comm.utils.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
/**
 * 公开文件上传Api类
 * @Author：Asher
 * @Description：1.0.1
 * @Date：2020/12/31 13:57
 */
@Api(tags = "开放Api")
@RestController
public class ApiController {
    @Autowired
    private UserService userService;

    @Autowired
    private UploadService uploadService;

    // 上传门票
    private static ConcurrentHashMap<String, Integer> ticketMap = new ConcurrentHashMap<String, Integer>();


    @PostMapping(value = "/up/1")
    @ApiOperation(tags = "文件上传", value = "通用上传接口，账号密码验权")
    public Result up1(
            @RequestParam("file") List<MultipartFile> files,
            UploadParam uploadParam,
            String email,
            String password) {

        AssertUtil.isNotNullsEx(ResultCode.PARAM_IS_BLANK, email, password);
        Integer userid = userService.login(email, password);
        List<UploadResult> uploadResults = uploadService.uploadList(files, uploadParam, userid);
        return Result.success(uploadResults);
    }


    @PostMapping(value = "/up/2")
    @ApiOperation(tags = "文件上传", value = "通用上传接口，私匙验权")
    public Result up2(
            @RequestParam("file") List<MultipartFile> files,
            UploadParam uploadParam,
            String key) {

        AssertUtil.isNotNullsEx(ResultCode.PARAM_IS_BLANK, key);
        User user = userService.getByKey(key);
        AssertUtil.isNotNullsEx(ResultCode.USER_NOT_EXIST, user);

        List<UploadResult> uploadResults = uploadService.uploadList(files, uploadParam, user.getId());
        return Result.success(uploadResults);
    }

    @PostMapping(value = "/up/3")
    @ApiOperation(tags = "文件上传", value = "通用上传接口，临时票据验权")
    public Result up3(
            @RequestParam("file") List<MultipartFile> files,
            UploadParam uploadParam,
            String ticket) {

        AssertUtil.isNotNullsEx(ResultCode.PARAM_IS_BLANK, ticket);

        Integer userid = null;
        synchronized(this){
            // 判断票据是否存在，不存在则抛出异常
            AssertUtil.isTrue(ticketMap.containsKey(ticket), ResultCode.PARAM_TICKET_NOT_FOUND);
            userid = ticketMap.get(ticket);
            ticketMap.remove(ticket);
        }
        List<UploadResult> uploadResults = uploadService.uploadList(files, uploadParam, userid);
        return Result.success(uploadResults);
    }


    @PostMapping(value = "/up/ticket")
    @ApiOperation(tags = "文件上传", value = "获取临时上传票据")
    public Result cicket(String token) {
        // 校验用户私匙是否正确
        AssertUtil.isNotNullsEx(ResultCode.PARAM_IS_BLANK, token);
        User user = userService.getByKey(token);
        AssertUtil.isNotNullsEx(ResultCode.USER_NOT_EXIST, user);
        // 生成临时票据，写入到线程安全map中
        String tk = UUID.randomUUID().toString() ;
        ticketMap.put(tk, user.getId());
        return Result.success(tk);
    }



    @GetMapping("/getNotice")
    @ResponseBody
    public Msg getNotice() {
        Msg msg = new Msg();
        String url = "http://tc.hellohao.cn/getNoticeText";
        if(TestUrl.testUrlWithTimeOut(url,2000)){
            String urls =url;
            //msg.setData(HttpUtil.get(urls));
        }else{
            msg.setData("暂无公告");
        }
        return msg;
    }




}
