package com.flyimg.controller.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.flyimg.comm.enums.ResultCode;
import com.flyimg.pojo.*;
import com.flyimg.service.*;
import com.flyimg.comm.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


@RestController
public class ApiController {
    @Autowired
    private UserService userService;
    @Autowired
    private KeysService keysService;
    @Autowired
    private ConfigService configService;
    @Autowired
    private SysConfigService sysConfigService;
    @Autowired
    private FileService fileService;

    // 上传门票
    private static Set<String> ticketSet = Collections.synchronizedSet(new HashSet<>());

    @Value("${systemupdate}")
    private String systemupdate;


    @PostMapping(value = "/up/1")
    public Result up1(
            HttpServletRequest request,
            @RequestParam("file") List<MultipartFile> files,
            String directory,
            String email,
            String pass) {

        AssertUtil.isNotNullsEx(ResultCode.PARAM_IS_BLANK, email, pass);
        Integer userid = userService.login(email, pass);
        List<UploadResult> uploadResults = fileService.uploadList(files, directory, userid);
        return Result.success(uploadResults);
    }

    @PostMapping(value = "/up/2")
    public Result up2(
            HttpServletRequest request,
            @RequestParam("file") List<MultipartFile> files,
            String directory,
            String key) {

        AssertUtil.isNotNullsEx(ResultCode.PARAM_IS_BLANK, key);
        User user = userService.getByKey(key);
        AssertUtil.isNotNullsEx(ResultCode.USER_NOT_EXIST, user);

        List<UploadResult> uploadResults = fileService.uploadList(files, directory, user.getId());
        return Result.success(uploadResults);
    }

    @PostMapping(value = "/up/3")
    public Result up3(
            HttpServletRequest request,
            @RequestParam("file") List<MultipartFile> files,
            String directory,
            String key) {

        AssertUtil.isNotNullsEx(ResultCode.PARAM_IS_BLANK, key);
        User user = userService.getByKey(key);
        AssertUtil.isNotNullsEx(ResultCode.USER_NOT_EXIST, user);

        List<UploadResult> uploadResults = fileService.uploadList(files, directory, user.getId());
        return Result.success(uploadResults);
    }


    @PostMapping(value = "/up/ticket")
    public Result cicket(
            HttpServletRequest request,
            @RequestParam("file") List<MultipartFile> files,
            String directory,
            String key) {

        AssertUtil.isNotNullsEx(ResultCode.PARAM_IS_BLANK, key);
        User user = userService.getByKey(key);
        AssertUtil.isNotNullsEx(ResultCode.USER_NOT_EXIST, user);
        String tk = UUID.randomUUID().toString() ;
        ticketSet.add(tk);
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
