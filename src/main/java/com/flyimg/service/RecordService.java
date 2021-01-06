package com.flyimg.service;

import com.flyimg.pojo.User;
import com.flyimg.pojo.WebPanel;

public interface RecordService {

    /**
     * 添加访问记录（队列执行）
     */
    Boolean addByQueue(Integer userid, String uri, String visitorIp);

    /**
     * 将队列里所有的记录入库
     */
    Integer saveByQueue();

    /**
     * 获取前端需要的面板统计数据
     */
    WebPanel getWebStatistics(Integer userid, Integer days);

}
