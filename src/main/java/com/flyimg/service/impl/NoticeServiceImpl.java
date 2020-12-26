package com.flyimg.service.impl;

import com.flyimg.dao.NoticeMapper;
import com.flyimg.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    @Override
    public String getNotice() {
        return noticeMapper.getNotice();
    }
}
