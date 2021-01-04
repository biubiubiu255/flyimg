package com.flyimg.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("http_header")
@Data
public class HttpHeader {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer userid;
    private String  contentType;
    private String  contentEncoding;
    private String  contentLanguage;
    private String  contentDisposition;
    private String  cacheControl;
    private String  expires;
    private String  others;
}
	
		

