package com.flyimg.service.impl;

import com.flyimg.dao.ImgreviewMapper;
import com.flyimg.pojo.Imgreview;
import com.flyimg.service.ImgreviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImgreviewServiceImpl implements ImgreviewService {

    @Autowired
    private ImgreviewMapper imgreviewMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(Imgreview record) {
        return 0;
    }

    @Override
    public int insertSelective(Imgreview record) {
        return 0;
    }

    @Override
    public Imgreview selectByPrimaryKey(Integer id) {
        return imgreviewMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Imgreview record) {
        return imgreviewMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Imgreview record) {
        return 0;
    }
}
