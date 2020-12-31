package com.flyimg.comm.utils;

import com.flyimg.dao.CodeMapper;
import com.flyimg.pojo.Code;
import com.flyimg.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class SetFiles {
    // 转换文件方法
    public static File changeFile(MultipartFile multipartFile) {
        // 获取文件名
        String fileName = multipartFile.getName();//multipartFile.getOriginalFilename();
        // 获取文件后缀
        String prefix = fileName.substring(fileName.lastIndexOf("."));
        // todo 修改临时文件文件名
        File file = null;
        try {
            file = File.createTempFile(fileName, prefix);
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    // 转换文件方法
    public static File changeFile_new(MultipartFile multipartFile) {
        // 获取文件名
        String fileName = multipartFile.getOriginalFilename();//getOriginalFilename
        // 获取文件后缀
        String prefix = fileName.substring(fileName.lastIndexOf("."));
        // todo 修改临时文件文件名
        File file = null;
        try {
            file = File.createTempFile(fileName, prefix);
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }


    //判断文件是否是图片格式
    public static Boolean VerifyImage(MultipartFile multipartFile){
        byte[] b = new byte[3];
        FileInputStream fis = null;
        String suf = null;
        try {
            fis = new FileInputStream(SetFiles.changeFile(multipartFile).getPath());
            fis.read(b, 0, b.length);
            suf = ImgUtils.bytesToHexString(b);
            suf = suf.toUpperCase();
            if(fis!=null){fis.close();}
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(ImgUtils.checkPictureType(suf).equals("0000")) {
            return false;
        }else{
            return true;
        }
    }


    /**
     * @author Hellohao
     * @version 1.0
     * @date 2019-08-11 14:21
     */
    @Service
    public static class CodeServiceImpl implements CodeService {

        @Autowired
        private CodeMapper codeMapper;


        @Override
        public List<Code> selectCode(String value) {
            return codeMapper.selectCode(value);
        }

        @Override
        public Code selectCodekey(String code) {
            return codeMapper.selectCodekey(code);
        }

        @Override
        public Integer addCode(Code code) {
            return codeMapper.addCode(code);
        }

        @Override
        public Integer deleteCode(String code) {
            return codeMapper.deleteCode(code);
        }
    }
}
