package com.flyimg.service.impl;

import com.flyimg.dao.DomainMapper;
import com.flyimg.service.DomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Hellohao
 * @version 1.0
 * @date 2019/8/21 9:51
 */
@Service
public class DomainServiceImpl implements DomainService {
    @Autowired
    private  DomainMapper domainMapper;

    @Override
    public Integer getDomain(String domain) {
        return domainMapper.getDomain(domain);
    }
}
