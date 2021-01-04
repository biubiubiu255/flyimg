package com.flyimg.pojo;

import lombok.Data;

@Data
public class UploadResult {

    private String filename;
    private String md5;
    private String url;
    private Integer status;

}
