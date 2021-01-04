package com.flyimg.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.flyimg.pojo.FileOss;
import com.flyimg.pojo.FileSave;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FileListMapper extends BaseMapper<FileOss> {

    @Select("select * from file_list where uri = #{uri} and userid = #{userid} limit 1")
    FileOss selectOneByUri(String uri, Integer userid);

    List<FileOss> selectList(FileOss fileOSS);

    Integer count(@Param("userid") Integer userid);

    Integer deleteById(@Param("id") Integer id);

    FileOss selectByPrimaryKey(@Param("id") Integer id);

    Integer counts(@Param("userid") Integer userid);

    Integer updateByFilename(FileOss fileOSS);

    Integer deleteByFilename(@Param("filename") String filename);

    Integer deleAll(@Param("id") Integer id);

    List<FileOss> selectListFromTime(@Param("startTime") String startTime);

    Integer selectMoneyUsed(@Param("userid") Integer userid);

    Integer md5Count(@Param("md5") String md5);

    FileOss selectUriByMd5(@Param("md5") String md5);


}
