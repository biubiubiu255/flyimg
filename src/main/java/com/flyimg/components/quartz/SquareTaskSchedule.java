package com.flyimg.components.quartz;

import com.flyimg.service.RecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 任务调度
 * @author yuanneng
 * Created by jonty on 2019/6/18.
 */

@Slf4j
@Component
@EnableScheduling
public class SquareTaskSchedule
{

    @Resource
    private RecordService recordService;


    @Scheduled(cron = "0 10 0 * * ?")
    public void saveShowRecord(){
        Integer num = recordService.saveByQueue();
        log.info("访问记录入库完成: " + num + "条");
    }

}
