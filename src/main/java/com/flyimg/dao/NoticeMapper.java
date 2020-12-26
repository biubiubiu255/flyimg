package com.flyimg.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NoticeMapper {
    String getNotice();
}
