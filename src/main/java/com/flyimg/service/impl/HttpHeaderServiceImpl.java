package com.flyimg.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.flyimg.dao.FileSaveMapper;
import com.flyimg.dao.HttpHeaderMapper;
import com.flyimg.pojo.FileSave;
import com.flyimg.pojo.HttpHeader;
import com.flyimg.service.FileSaveService;
import com.flyimg.service.HttpHeaderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class HttpHeaderServiceImpl extends ServiceImpl<HttpHeaderMapper, HttpHeader> implements HttpHeaderService {


}
