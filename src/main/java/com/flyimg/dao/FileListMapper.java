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

    @Select("select * from file_list where uri = #{uri} limit 1")
    FileOss selectOneByUriGuess(String uri);

}
