package com.flyimg.dao;

import com.flyimg.pojo.Config;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ConfigMapper {
    Config getSourceype();
    Integer setSourceype(Config config);
}
