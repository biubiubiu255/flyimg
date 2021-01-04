package com.flyimg.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.flyimg.pojo.FileSave;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface FileSaveMapper extends BaseMapper<FileSave> {

    @Select("select * from file_save where md5 = #{md5} limit 1")
    FileSave selectOneByMd5(String md5);


}
