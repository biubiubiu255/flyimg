package com.flyimg.service;

import com.flyimg.pojo.UploadParam;
import com.flyimg.pojo.UploadResult;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Service
public interface UploadService {

    /**
     * 上传单个文件
     */
    UploadResult uploadOne(MultipartFile file, UploadParam uploadParam, Integer userid);

    /**
     * 上传多个文件
     */
    List<UploadResult> uploadList(List<MultipartFile> files, UploadParam uploadParam, Integer userid);
}
