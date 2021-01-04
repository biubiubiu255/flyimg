package com.flyimg.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.flyimg.pojo.SysConfig;
import com.flyimg.pojo.User;
import org.apache.ibatis.annotations.Select;


public interface UploadConfigMapper  extends BaseMapper<SysConfig> {

    /**
     * 查询当前的oss全局规则
     */
    @Select("select * from sys_config where 1 limit 1")
    SysConfig select();

    /**
     * 修改oss全局规则
     */
    Integer updateConfig(SysConfig sysConfig);
}
