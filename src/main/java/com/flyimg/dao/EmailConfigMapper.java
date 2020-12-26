package com.flyimg.dao;

import com.flyimg.pojo.EmailConfig;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmailConfigMapper {
    EmailConfig getemail();
    Integer updateemail(EmailConfig emailConfig);
}
