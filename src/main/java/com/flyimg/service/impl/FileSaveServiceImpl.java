package com.flyimg.service.impl;


import com.flyimg.dao.FileSaveMapper;
import com.flyimg.pojo.FileSave;
import com.flyimg.service.FileSaveService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class FileSaveServiceImpl implements FileSaveService {

    @Resource
    private FileSaveMapper fileSaveMapper;

    @Override
    public FileSave getByMd5(String md5) {
        return fileSaveMapper.selectOneByMd5(md5);
    }

    @Override
    public Integer add(FileSave fileSave) {
        return fileSaveMapper.insert(fileSave);
    }

}
