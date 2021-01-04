package com.flyimg.pojo;

import lombok.Data;

@Data
public class SysConfig {
    private Integer id;
    private String suffix;
    private Integer maxSizeTourists;
    private Integer maxSizeUser;
    private Integer maxCountTourists;
    private Integer maxCountUser;
    private Integer allowTouristsUp;
    private Integer api;
    private Integer maxMemoryTourists;
    private Integer maxMemoryUser;
    private String blacklist;

}