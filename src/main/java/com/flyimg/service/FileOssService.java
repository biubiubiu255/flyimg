package com.flyimg.service;


import com.flyimg.pojo.FileOss;

import java.util.List;

public interface FileOssService {

    Integer add(FileOss fileOss);

    FileOss getByUri(String uri, Integer userid);

    Integer updataFileOss(FileOss fileOSS);



    List<FileOss> selectimg(FileOss fileOSS);

    Integer deleimg(Integer id);

    Integer countimg(Integer userid);

    FileOss selectByPrimaryKey(Integer id);

    Integer counts(Integer userid);

    Integer setImg(FileOss fileOSS);

    Integer deleimgname(String imgname);

    Integer deleall(Integer id);

    List<FileOss> gettimeimg(String time);

    Integer getusermemory(Integer userid);

    Integer md5Count(String md5key);

    FileOss selectImgUrlByMD5(String md5key);

}
