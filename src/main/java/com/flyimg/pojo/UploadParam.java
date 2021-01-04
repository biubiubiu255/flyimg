package com.flyimg.pojo;

import lombok.Data;

import java.util.List;

@Data
public class UploadParam {

    private String newFilename;
    private String newDirectory;
    private Integer vaildSec;
    private List<String> headerList;
}
