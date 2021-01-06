package com.flyimg.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("record")
@Data
public class Record {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer userid;
    private Integer num;
    private Integer days;
    private String uri;
    private String visitorIp;

}
