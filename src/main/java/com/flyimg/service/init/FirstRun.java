package com.flyimg.service.init;

import com.flyimg.comm.utils.Print;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

/**
 * @author asher
 * @version 1.1
 * @date 2020/12/28 16:56
 */
@Slf4j
@Configuration
public class FirstRun implements InitializingBean {

    @Value("${spring.datasource.username}")
    private String jdbcusername;

    @Value("${spring.datasource.password}")
    private String jdbcpass;

    @Value("${spring.datasource.url}")
    private String jdbcurl;

    @Override
    public void afterPropertiesSet() throws Exception {

        log.info("数据库结构校验完成（当前流程已跳过，可自行恢复~~qaq）");

        if (true){
            return;
        }

    }

}
