package com.flyimg.service.impl;


import com.flyimg.comm.enums.ResultCode;
import com.flyimg.comm.utils.AssertUtil;
import com.flyimg.comm.utils.CryptoUtils;
import com.flyimg.comm.utils.LocalFileUtils;
import com.flyimg.dao.FileListMapper;
import com.flyimg.pojo.FileOSS;
import com.flyimg.pojo.Keys;
import com.flyimg.pojo.MyException;
import com.flyimg.pojo.UploadResult;
import com.flyimg.service.FileService;
import com.flyimg.comm.utils.Print;

import com.flyimg.service.UserService;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FileServiceImpl implements FileService {
    @Resource
    private FileListMapper fileListMapper;
    @Resource
    private UserService userService;

    @Value("${writeDirectory}")
    private String rootDirectory;


    @Override
    public UploadResult uploadOne(MultipartFile file, String directory, Integer userid){

        // 判断剩余容量是否足够，不够则用断言类抛出异常
        Long memAndIncr = userService.getMemAndIncr(userid, file.getSize());
        AssertUtil.isTrue(memAndIncr >= 0, ResultCode.USER_MEM_LESS);

        // 下面是纯文件上传流程
        String md5 = null;
        try {
            md5 = CryptoUtils.encodeMD5(file.getBytes());
            LocalFileUtils.copyByFileChannelWithBuffer(file.getInputStream(), new File(rootDirectory + directory + md5));
        } catch (IOException e) {
            throw new MyException(ResultCode.DATA_FILE_WRITE_ERROR);
        }
        UploadResult uploadResult = new UploadResult();
        uploadResult.setMd5(md5);
        uploadResult.setFileName(file.getOriginalFilename());
        return  uploadResult;
    }


    @Override
    public List<UploadResult> uploadList(List<MultipartFile> files, String directory, Integer userid) {
        // 只是循环调用单个上传的方法，里面会校验容量是否足够
        List<UploadResult> list = new ArrayList<UploadResult>();
        Map<String, MultipartFile> map = new HashMap<>();
        for (MultipartFile multipartFile : files) {
            try {
                list.add(uploadOne(multipartFile, directory, userid));
            }catch (Exception e){
               break;
            }
        }
        return list;
    }


    @Override
    public List<FileOSS> selectimg(FileOSS fileOSS) {
        // TODO Auto-generated method stub
        return fileListMapper.selectList(fileOSS);
    }

    @Override
    public Integer deleimg(Integer id) {
        // TODO Auto-generated method stub
        return fileListMapper.deleteById(id);
    }


    public FileOSS selectByPrimaryKey(Integer id) {
        return fileListMapper.selectByPrimaryKey(id);
    }

    public void delectFTP(Keys key, String fileName) {
        FTPClient ftp = new FTPClient();
        String[] host = key.getEndpoint().split("\\:");
        String h = host[0];
        Integer p = Integer.parseInt(host[1]);
        try {
            if(!ftp.isConnected()){
                ftp.connect(h,p);
            }
            ftp.login(key.getAccessKey(), key.getAccessSecret());
            ftp.deleteFile(fileName);
        } catch (IOException e) {
            e.printStackTrace();
            Print.warning("删除FTP存储的图片失败");
        }
    }
    @Override
    public Integer counts(Integer userid) {
        // TODO Auto-generated method stub
        return fileListMapper.counts(userid);
    }

    @Override
    public Integer countimg(Integer userid) {
        // TODO Auto-generated method stub
        return fileListMapper.count(userid);
    }

    @Override
    public Integer setImg(FileOSS fileOSS) {
        return fileListMapper.updateByFilename(fileOSS);
    }

    @Override
    public Integer deleimgname(String imgname) {
        return fileListMapper.deleteByFilename(imgname);
    }

    @Override
    public Integer deleall(Integer id) {
        return fileListMapper.deleAll(id);
    }

    @Override
    public List<FileOSS> gettimeimg(String time) {
        return fileListMapper.selectListFromTime(time);
    }

    @Override
    public Integer getusermemory(Integer userid) {
        return fileListMapper.selectMoneyUsed(userid);
    }

    @Override
    public Integer md5Count(String md5key) {
        return fileListMapper.md5Count(md5key);
    }

    @Override
    public FileOSS selectImgUrlByMD5(String md5key) {
        return fileListMapper.selectUriByMd5(md5key);
    }
}
