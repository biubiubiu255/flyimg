package com.flyimg.pojo;

import lombok.Data;

@Data
public class SysConfig {
    private String suffix;
    private Integer maxSizeTourists;
    private Integer maxSizeUser;
    private Integer maxCountTourists;
    private Integer maxCountUser;
    private Integer allowTouristsUp;
    private Integer api;
    private Integer maxMemoryTourists;
    private Integer userMemory;
    private String blacklist;


    public SysConfig() {
    }

    public SysConfig(String suffix, Integer maxSizeTourists, Integer maxSizeUser, Integer maxCountTourists, Integer maxCountUser, Integer urltype, Integer allowTouristsUp, Integer api, Integer maxMemoryTourists, Integer userMemory, String blacklist) {
        this.suffix = suffix;
        this.maxSizeTourists = maxSizeTourists;
        this.maxSizeUser = maxSizeUser;
        this.maxCountTourists = maxCountTourists;
        this.maxCountUser = maxCountUser;
        this.allowTouristsUp = allowTouristsUp;
        this.api = api;
        this.maxMemoryTourists = maxMemoryTourists;
        this.userMemory = userMemory;
        this.blacklist = blacklist;
    }
}