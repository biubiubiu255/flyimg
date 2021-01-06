package com.flyimg.controller.api;

import com.flyimg.comm.enums.ResultCode;
import com.flyimg.pojo.*;
import com.flyimg.service.*;
import com.flyimg.service.impl.UploadServiceImpl;
import com.flyimg.comm.utils.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;

@Controller
@RequestMapping(value = "/", headers = {"Host!=127.0.0.1", "Host!=127.0.0.1:8088"})
public class ShowController {

    @Autowired
    private FileOssService fileOssService;

    @Autowired
    private HttpHeaderService httpHeaderService;

    @Autowired
    private RecordService recordService;

    @RequestMapping("/**")
    @ResponseBody
    public void showFile(HttpServletRequest request, HttpServletResponse response){

        String uri = request.getRequestURI();
        FileOss fileOss = fileOssService.getByUriGuess(uri);
        if (fileOss==null){
            response.setStatus(404);
            return;
        }

        String method = request.getMethod();
        HttpHeader httpHeader = httpHeaderService.getById(fileOss.getHeaderCode());
        response.setHeader("Pragma", StringUtils.isBlank(httpHeader.getCacheControl()) ? "max-age=31536000" : httpHeader.getCacheControl());
        response.setHeader("Cache-Control", StringUtils.isBlank(httpHeader.getCacheControl()) ? "max-age=31536000" : httpHeader.getCacheControl());
        response.setHeader("Expires", StringUtils.isBlank(httpHeader.getExpires()) ? DateUtils.toGMTString(System.currentTimeMillis()+31536000) : httpHeader.getExpires());
        response.setHeader("ETag", fileOss.getMd5());
        response.setHeader("Content-Type", ImgUtils.suffixToContentType(fileOss.getSuffix()));
        response.setHeader("Accept-Ranges", "bytes");
        response.setHeader("Content-Type", ImgUtils.suffixToContentType(fileOss.getSuffix()));

        String rqTag = request.getHeader("If-None-Match");
        if (fileOss.getMd5().equals(rqTag)){
            response.setStatus(304);
            return;
        }

        if ("GET".equals(method)){
            try {
                ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
                fileOssService.writeOutputStream(fileOss.getMd5(), fileOss.getSuffix(), byteOut);
                response.setContentLengthLong(byteOut.size());
                byteOut.writeTo(response.getOutputStream());
                byteOut.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if("HEAD".equals(method)){
            response.setHeader("Content-Disposition", StringUtils.isEmpty(httpHeader.getContentDisposition()) ? "attachment;filename="+fileOss.getFilename() : httpHeader.getContentDisposition());
        }

        recordService.addByQueue(fileOss.getUserid(), uri, WebUtil.getRemoteIP(request));

    }



}
