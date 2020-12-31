package com.flyimg.service.impl;

import com.flyimg.dao.KeysMapper;
import com.flyimg.pojo.Keys;
import com.flyimg.service.KeysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class KeysServiceImpl implements KeysService {

    @Resource
    private KeysMapper keysMapper;

    @Override
    public Keys selectKeys(Integer storageType) {
        // TODO Auto-generated method stub
        return keysMapper.selectKeys(storageType);
    }

    @Override
    public Integer updateKey(Keys key) {
        // TODO Auto-generated method stub
        return keysMapper.updateKey(key);
    }

    @Override
    public List<Keys> getKeys() {
        return null;
    }

}
