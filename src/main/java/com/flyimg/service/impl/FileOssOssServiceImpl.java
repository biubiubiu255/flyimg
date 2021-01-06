package com.flyimg.service.impl;


import com.flyimg.dao.FileListMapper;
import com.flyimg.pojo.FileOss;
import com.flyimg.service.FileOssService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;

@Service
public class FileOssOssServiceImpl implements FileOssService {

    @Resource
    private FileListMapper fileListMapper;


    @Value("${writeDirectory}")
    private String rootDirectory;

    @Override
    public Integer add(FileOss fileOss) {
        return fileListMapper.insert(fileOss);
    }

    @Override
    public FileOss getByUri(String uri, Integer userid) {
        return fileListMapper.selectOneByUri(uri, userid);
    }

    @Override
    public FileOss getByUriGuess(String uri) {
        return fileListMapper.selectOneByUriGuess(uri);
    }

    @Override
    public Long writeOutputStream(String md5, String suffix,  ByteArrayOutputStream byteArrayOutputStream) {
        Long len = 0L;
        try {
            InputStream input = new FileInputStream(new File(rootDirectory + md5));
            BufferedImage buff= ImageIO.read(input);
            //将图片输出给浏览器
            //DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            ImageIO.write(buff, suffix, byteArrayOutputStream);
        } catch (Exception e) {
            e.printStackTrace();
            return len;
        }
        return len;
    }

    @Override
    public Long writeOutputStreamQ(String md5, String suffix, OutputStream outputStream) {
        Long len = 0L;
        try {
            File file = new File(rootDirectory + md5);
            FileInputStream inputStream = new FileInputStream(file);
            byte[] bytes = new byte[inputStream.available()];
            outputStream.write(bytes);
            outputStream.flush();
            //关闭响应输出流
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            return len;
        }
        return len;
    }

    @Override
    public Integer updataFileOss(FileOss fileOSS) {
        return fileListMapper.updateById(fileOSS);
    }


}
