package com.flyimg.service.impl;

import com.flyimg.dao.UploadConfigMapper;
import com.flyimg.pojo.SysConfig;
import com.flyimg.service.SysConfigService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SysConfigServiceImpl implements SysConfigService {
    @Resource
    private UploadConfigMapper uploadConfigMapper;

    @Override
    public SysConfig get() {
        return uploadConfigMapper.select();
    }

    @Override
    public Integer update(SysConfig sysConfig) {
        return uploadConfigMapper.updateConfig(sysConfig);
    }
}

