package com.flyimg.service.impl;

import com.flyimg.dao.UploadConfigMapper;
import com.flyimg.pojo.UploadConfig;
import com.flyimg.service.UploadConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UploadConfigServiceImpl implements UploadConfigService {
    @Autowired
    private UploadConfigMapper uploadConfigMapper;

    @Override
    public UploadConfig getUpdateConfig() {
        return uploadConfigMapper.getUpdateConfig();
    }

    @Override
    public Integer setUpdateConfig(UploadConfig uploadConfig) {
        return uploadConfigMapper.setUpdateConfig(uploadConfig);
    }
}

