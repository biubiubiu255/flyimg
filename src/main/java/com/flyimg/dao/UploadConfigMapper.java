package com.flyimg.dao;

import com.flyimg.pojo.UploadConfig;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UploadConfigMapper {
    UploadConfig getUpdateConfig();
    Integer setUpdateConfig(UploadConfig uploadConfig);
}
