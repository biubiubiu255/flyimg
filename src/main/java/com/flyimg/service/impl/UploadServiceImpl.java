package com.flyimg.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.flyimg.comm.enums.ResultCode;
import com.flyimg.pojo.*;
import com.flyimg.comm.utils.*;
import com.flyimg.pojo.vo.MyException;
import com.flyimg.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;

/**
 * @author Hellohao
 * @version 1.0
 * @date 2020/1/9 15:46
 */

@Slf4j
@Service
public class UploadServiceImpl implements UploadService {

    @Resource
    private UserService userService;

    @Resource
    private FileSaveService fileSaveService;

    @Resource
    private FileOssService fileOssService;

    @Resource
    private HttpHeaderService httpHeaderService;

    @Value("${writeDirectory}")
    private String rootDirectory;

    @Value("${defalutHost}")
    private String defalutHost;

    @Override
    public UploadResult uploadOne(MultipartFile file, UploadParam uploadParam, Integer userid){

        // 判断剩余容量是否足够，不够则用断言类抛出异常
        Long memAndIncr = userService.getMemAndUsedMemIncr(userid, file.getSize());
        AssertUtil.isTrue(memAndIncr >= 0, ResultCode.USER_MEM_LESS);

        // 下面是纯文件上传流程
        String uri;
        String md5;
        String filename;
        String dir = makeDirectory(uploadParam.getNewDirectory());
        if (uploadParam.getVaildSec()==null){
            uploadParam.setVaildSec(365*30*86400);  // 默认是一个月
        }

        try {
            md5 = CryptoUtils.encodeMD5(file.getBytes());
            filename = makeFilename(uploadParam.getNewFilename(), file.getOriginalFilename(), md5);
            filename = URLEncoder.encode(filename, "utf-8");
            log.info("文件名 {} {}", file.getOriginalFilename(), file.getName(), file.getContentType());
            uri = "/" + dir + filename;
            FileSave global = fileSaveService.getByMd5(md5);
            if (global==null){
                FileSave fileSave = new FileSave();
                fileSave.setMd5(md5);
                fileSave.setSize((int)file.getSize());
                fileSave.setUri(uri);
                fileSave.setPointId(0);
                fileSaveService.add(fileSave);
                LocalFileUtils.copyByFileChannelWithBuffer(file.getInputStream(), new File(rootDirectory + md5));
            }
            FileOss fileOss = fileOssService.getByUri(uri, userid);
            FileOss fileOssNew = new FileOss();
            fileOssNew.setMd5(md5);
            fileOssNew.setHeaderCode(getHeaderCode(uploadParam.getHeaderList(), userid));
            fileOssNew.setExpire(System.currentTimeMillis()+uploadParam.getVaildSec()*1000);
            fileOssNew.setUpdatedTime(System.currentTimeMillis());
            if (fileOss!=null){
                fileOssNew.setId(fileOss.getId());
                fileOssService.updataFileOss(fileOssNew);
            }else {
                fileOssNew.setUserid(userid);
                fileOssNew.setUri(uri);
                fileOssNew.setDir(dir);
                fileOssNew.setFilename(filename);
                fileOssNew.setSize((int)file.getSize());
                fileOssNew.setSuffix(makeSuffix(filename));
                fileOssService.add(fileOssNew);
            }
        } catch (IOException e) {
            throw new MyException(ResultCode.DATA_FILE_WRITE_ERROR);
        }
        UploadResult uploadResult = new UploadResult();
        uploadResult.setMd5(md5);
        uploadResult.setFilename(file.getOriginalFilename());
        uploadResult.setUrl("http://" + defalutHost + dir + "/" + filename);
        uploadResult.setStatus(1);
        return  uploadResult;
    }



    @Override
    public List<UploadResult> uploadList(List<MultipartFile> files, UploadParam uploadParam, Integer userid) {
        // 只是循环调用单个上传的方法，里面会校验容量是否足够
        List<UploadResult> list = new ArrayList<UploadResult>();
        Map<String, MultipartFile> map = new HashMap<>();
        for (MultipartFile multipartFile : files) {
            try {
                list.add(uploadOne(multipartFile, uploadParam, userid));
            }catch (Exception e){
                e.printStackTrace();
                break;
            }
        }
        return list;
    }


    /**
     * 修正目录名格式，返回 xxxxx/
     */
    private static String makeDirectory(String dir){
        String dirNew = dir;
        if (StringUtils.isEmpty(dirNew)){
            dirNew = "";
        }else {
            while (dirNew.length()>0 && "/".equals(dirNew.substring(0, 1))){
                dirNew = dirNew.substring(1);
            }
            while (dirNew.length()>1 && ("//").equals(dirNew.substring(dirNew.length()-2))){
                dirNew = dirNew.substring(0, dirNew.length()-2);
            }
            if (!("/").equals(dirNew.substring(dirNew.length()-1))){
                dirNew += "/";
            }
        }
        return dirNew;
    }

    /**
     * 修正文件名格式
     */
    private static String makeFilename(String filename1, String filename2, String filename3){
        if (StringUtils.isNoneBlank(filename1)){
            return filename1;
        }
        if (StringUtils.isNoneBlank(filename2)){
            return filename2;
        }
        if (StringUtils.isNoneBlank(filename3)){
            return filename3;
        }
        return "";
    }

    /**
     * 修正文件名格式
     */
    private static String makeSuffix(String filename){
        return filename.contains(".") ? filename.substring(filename.lastIndexOf(".") + 1) : "";
    }

    /**
     * 根据用户传入的参数headers获取用户headers模板
     * 返回模板id
     */
    private Integer getHeaderCode(List<String> headerList, Integer userid){
        Integer code = 0;
        HttpHeader httpHeader = new HttpHeader();
        if (headerList!=null && !headerList.isEmpty()){
            for (String line : headerList){
                String head = line.toLowerCase();
                String key = StringUtils.substringBefore(head, ":");
                String val = StringUtils.substringAfter(head, ":");
                switch (key){
                    case "content-type":
                        httpHeader.setContentType(val);
                        break;
                    case "content-encoding":
                        httpHeader.setContentEncoding(val);
                        break;
                    case "content-language":
                        httpHeader.setContentLanguage(val);
                        break;
                    case "content-disposition":
                        httpHeader.setContentDisposition(val);
                        break;
                    case "cache-control":
                        httpHeader.setCacheControl(val);
                        break;
                    case "expires":
                        httpHeader.setExpires(val);
                        break;
                    default:
                        httpHeader.setOthers(StringUtils.isEmpty(httpHeader.getOthers()) ? line : httpHeader.getOthers()+"\n"+line);
                        break;
                }
            }
            QueryWrapper<HttpHeader> queryWrapper = new QueryWrapper<>(httpHeader) ;
            HttpHeader one = httpHeaderService.getOne(queryWrapper);
            if (one==null){
                boolean save = httpHeaderService.save(httpHeader);
                one = httpHeaderService.getOne(queryWrapper);
            }
            code = one.getId();
        }
        return code;
    }

}



