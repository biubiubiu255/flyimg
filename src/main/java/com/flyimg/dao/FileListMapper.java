package com.flyimg.dao;

import com.flyimg.pojo.FileOSS;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FileListMapper {



    List<FileOSS> selectList(FileOSS fileOSS);

    Integer count(@Param("userid") Integer userid);

    Integer deleteById(@Param("id") Integer id);

    FileOSS selectByPrimaryKey(@Param("id") Integer id);

    Integer counts(@Param("userid") Integer userid);

    Integer updateByFilename(FileOSS fileOSS);

    Integer deleteByFilename(@Param("filename") String filename);

    Integer deleAll(@Param("id") Integer id);

    List<FileOSS> selectListFromTime(@Param("startTime") String startTime);

    Integer selectMoneyUsed(@Param("userid") Integer userid);

    Integer md5Count(@Param("md5") String md5);

    FileOSS selectUriByMd5(@Param("md5") String md5);


}
