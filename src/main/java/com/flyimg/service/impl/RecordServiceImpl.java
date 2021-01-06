package com.flyimg.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.flyimg.comm.enums.ResultCode;
import com.flyimg.comm.utils.AssertUtil;
import com.flyimg.comm.utils.CryptoUtils;
import com.flyimg.comm.utils.DateUtils;
import com.flyimg.comm.utils.LocalFileUtils;
import com.flyimg.dao.RecordMapper;
import com.flyimg.pojo.*;
import com.flyimg.pojo.vo.MyException;
import com.flyimg.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Hellohao
 * @version 1.0
 * @date 2020/1/9 15:46
 */

@Slf4j
@Service
public class RecordServiceImpl implements RecordService {

    @Resource
    private RecordMapper recordMapper;

    private static BlockingQueue<Record> Queue = new LinkedBlockingQueue<Record>();

    @Override
    public Boolean addByQueue(Integer userid, String uri, String visitorIp) {
        Record record = new Record();
        record.setUserid(userid);
        record.setUri(uri);
        record.setVisitorIp(visitorIp);
        record.setDays(Integer.valueOf(DateUtils.dateToString(new Date(), "yyyyMMdd")));
        record.setNum(1);
        Queue.add(record);
        return null;
    }

    @Override
    public Integer saveByQueue() {
        ArrayList<Record> records = new ArrayList<>();
        while (!Queue.isEmpty()){
            records.add(Queue.poll());
        }
        recordMapper.insertAndIncr(records);
        return records.size();
    }



    @Cacheable(value="comm", key="#root.methodName+#userid+#days" )
    @Override
    public WebPanel getWebStatistics(Integer userid, Integer days) {
        return recordMapper.selectWebPanel(userid, days);
    }


}



