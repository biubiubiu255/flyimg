package com.flyimg.service;


import com.flyimg.pojo.FileOss;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

public interface FileOssService {

    Integer add(FileOss fileOss);

    FileOss getByUri(String uri, Integer userid);

    FileOss getByUriGuess(String uri);

    Long writeOutputStream(String md5, String suffix, ByteArrayOutputStream byteArrayOutputStream);

    Long writeOutputStreamQ(String md5, String suffix, OutputStream outputStream);

    Integer updataFileOss(FileOss fileOSS);

}
