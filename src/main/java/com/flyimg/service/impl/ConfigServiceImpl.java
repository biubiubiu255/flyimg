package com.flyimg.service.impl;

import com.flyimg.dao.ConfigMapper;
import com.flyimg.pojo.Config;
import com.flyimg.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ConfigServiceImpl implements ConfigService {
    @Resource
    private ConfigMapper configMapper;
    @Override
    public Config getSourceype() {
        return configMapper.getSourceype();
    }

    @Override
    public Integer setSourceype(Config config) {
        return configMapper.setSourceype(config);
    }

    @Override
    @Cacheable(value = "user", key = "#name")
    public String getAA(String name){
        if ("怎么".equals(name)){
            return "哈哈哈哈";
        }else {
            return "三生三世";
        }
    }

    @Override
    @CachePut(value = "user", key = "#name")
    public String putAA(String name, String content){
        String res = content;
        return name;
    }
}
