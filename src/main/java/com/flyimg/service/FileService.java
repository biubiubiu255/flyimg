package com.flyimg.service;


import com.flyimg.pojo.FileOSS;
import com.flyimg.pojo.UploadResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {

    /**
     * 上传单个文件
     */
    UploadResult uploadOne(MultipartFile file, String directory, Integer userid);

    /**
     * 上传多个文件
     */
    List<UploadResult> uploadList(List<MultipartFile> files, String directory, Integer userid);



    List<FileOSS> selectimg(FileOSS fileOSS);

    Integer deleimg(Integer id);

    Integer countimg(Integer userid);

    FileOSS selectByPrimaryKey(Integer id);

    Integer counts(Integer userid);

    Integer setImg(FileOSS fileOSS);

    Integer deleimgname(String imgname);

    Integer deleall(Integer id);

    List<FileOSS> gettimeimg(String time);

    Integer getusermemory(Integer userid);

    Integer md5Count(String md5key);

    FileOSS selectImgUrlByMD5(String md5key);

}
