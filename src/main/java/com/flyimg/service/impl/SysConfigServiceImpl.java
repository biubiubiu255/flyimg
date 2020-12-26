package com.flyimg.service.impl;

import com.flyimg.dao.SysConfigMapper;
import com.flyimg.pojo.SysConfig;
import com.flyimg.service.SysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Hellohao
 * @version 1.0
 * @date 2019/8/15 13:48
 */
@Service
public class SysConfigServiceImpl implements SysConfigService {
    @Autowired
    private SysConfigMapper sysConfigMapper;
    @Override
    public SysConfig getstate() {
        return sysConfigMapper.getstate();
    }

    @Override
    public Integer setstate(SysConfig sysConfig) {
        return sysConfigMapper.setstate(sysConfig);
    }
}
