package com.flyimg.service;

import com.flyimg.pojo.EmailConfig;
import org.springframework.stereotype.Service;

@Service
public interface EmailConfigService {
    EmailConfig getemail();
    Integer updateemail(EmailConfig emailConfig);
}
