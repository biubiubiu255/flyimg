package com.flyimg.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.flyimg.pojo.HttpHeader;
import com.flyimg.pojo.Record;
import com.flyimg.pojo.WebPanel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RecordMapper extends BaseMapper<Record> {

    void insertAndIncr(List<Record> list);

    WebPanel selectWebPanel(Integer userid, Integer days);

}
