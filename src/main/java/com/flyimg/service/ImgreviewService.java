package com.flyimg.service;

import com.flyimg.pojo.Imgreview;

public interface ImgreviewService {
    int deleteByPrimaryKey(Integer id);

    int insert(Imgreview record);

    int insertSelective(Imgreview record);

    Imgreview selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Imgreview record);

    int updateByPrimaryKey(Imgreview record);

}
