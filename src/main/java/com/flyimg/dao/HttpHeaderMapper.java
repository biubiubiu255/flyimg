package com.flyimg.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.flyimg.pojo.FileSave;
import com.flyimg.pojo.HttpHeader;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface HttpHeaderMapper extends BaseMapper<HttpHeader> {

}
