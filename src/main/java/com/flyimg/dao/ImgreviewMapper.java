package com.flyimg.dao;

import com.flyimg.pojo.Imgreview;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ImgreviewMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Imgreview record);

    int insertSelective(Imgreview record);

    Imgreview selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Imgreview record);

    int updateByPrimaryKey(Imgreview record);
}