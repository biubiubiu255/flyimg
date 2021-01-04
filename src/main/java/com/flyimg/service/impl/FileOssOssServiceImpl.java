package com.flyimg.service.impl;


import com.flyimg.dao.FileListMapper;
import com.flyimg.pojo.FileOss;
import com.flyimg.pojo.Keys;
import com.flyimg.service.FileOssService;
import com.flyimg.comm.utils.Print;

import org.apache.commons.net.ftp.FTPClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@Service
public class FileOssOssServiceImpl implements FileOssService {

    @Resource
    private FileListMapper fileListMapper;

    @Override
    public Integer add(FileOss fileOss) {
        return fileListMapper.insert(fileOss);
    }

    @Override
    public FileOss getByUri(String uri, Integer userid) {
        return fileListMapper.selectOneByUri(uri, userid);
    }

    @Override
    public Integer updataFileOss(FileOss fileOSS) {
        return fileListMapper.updateById(fileOSS);
    }

    @Override
    public List<FileOss> selectimg(FileOss fileOSS) {
        // TODO Auto-generated method stub
        return fileListMapper.selectList(fileOSS);
    }

    @Override
    public Integer deleimg(Integer id) {
        // TODO Auto-generated method stub
        return fileListMapper.deleteById(id);
    }


    public FileOss selectByPrimaryKey(Integer id) {
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
    public Integer setImg(FileOss fileOSS) {
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
    public List<FileOss> gettimeimg(String time) {
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
    public FileOss selectImgUrlByMD5(String md5key) {
        return fileListMapper.selectUriByMd5(md5key);
    }
}
