package com.flyimg.service.impl;

import com.flyimg.dao.EmailConfigMapper;
import com.flyimg.pojo.EmailConfig;
import com.flyimg.service.EmailConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class EmailServiceImpl implements EmailConfigService {
    @Resource
    EmailConfigMapper emailConfigMapper;

    @Override
    public EmailConfig getemail() {
        return emailConfigMapper.getemail();
    }

    @Override
    public Integer updateemail(EmailConfig emailConfig) {
        return emailConfigMapper.updateemail(emailConfig);
    }
}
