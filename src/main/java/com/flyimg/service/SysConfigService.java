package com.flyimg.service;

import com.flyimg.pojo.SysConfig;
import org.springframework.stereotype.Service;

@Service
public interface SysConfigService {

    /**
     * 获取全局配置业务
     */
    SysConfig get();

    /**
     * 修改全局配置业务
     */
    Integer update(SysConfig updateConfig);
}
