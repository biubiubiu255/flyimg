package com.flyimg.pojo;

import lombok.Data;

@Data
public class Keys {
    private Integer id;
    private String accessKey;
    private String accessSecret;
    private String endpoint;
    private String bucketname;
    private String requestAddress;
    private Integer storageType;

    public Keys() {
        super();
    }

    public Keys(Integer id, String accessKey, String accessSecret, String endpoint, String bucketname,
                String requestAddress, Integer storageType) {
        super();
        this.id = id;
        this.accessKey = accessKey;
        this.accessSecret = accessSecret;
        this.endpoint = endpoint;
        this.bucketname = bucketname;
        this.requestAddress = requestAddress;
        this.storageType = storageType;
    }



}
