package com.flyimg.service;

import com.flyimg.pojo.Config;
import org.springframework.stereotype.Service;

@Service
public interface ConfigService {
    Config getSourceype();
    Integer setSourceype(Config config);
}
