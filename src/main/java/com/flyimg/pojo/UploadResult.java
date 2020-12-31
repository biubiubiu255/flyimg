package com.flyimg.pojo;

import lombok.Data;

@Data
public class UploadResult {

    private String fileName;
    private String md5;
    private String url;
    private String message;
    private Integer status;

}
