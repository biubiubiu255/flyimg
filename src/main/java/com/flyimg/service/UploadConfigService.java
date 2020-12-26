package com.flyimg.service;

import com.flyimg.pojo.UploadConfig;
import org.springframework.stereotype.Service;

@Service
public interface UploadConfigService {
    UploadConfig getUpdateConfig();
    Integer setUpdateConfig(UploadConfig updateConfig);
}
