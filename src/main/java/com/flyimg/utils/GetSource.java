package com.flyimg.utils;

import com.flyimg.exception.StorageSourceInitException;
import com.flyimg.pojo.ReturnImage;
import com.flyimg.service.impl.FTPImageupload;
import com.flyimg.service.impl.OSSImageupload;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @author Hellohao
 * @version 1.0
 * @date 2019/11/7 17:12
 */
public class GetSource {
    public static Map<ReturnImage, Integer>  storageSource(Integer type, Map<String, MultipartFile> fileMap, String userpath,
                                                           Map<String, String> filename, Integer setday){

        OSSImageupload ossImageupload = SpringContextHolder.getBean(OSSImageupload.class);
        FTPImageupload ftpImageupload = SpringContextHolder.getBean(FTPImageupload.class);
        Map<ReturnImage, Integer> m = null;
        try {
            if (type==2){
                m = ossImageupload.ImageuploadOSS(fileMap, userpath,filename,setday);
            }else if(type==5){
                m = LocUpdateImg.ImageuploadLOC(fileMap, userpath,filename,setday);
            }else if(type==7){
                m =  ftpImageupload.ImageuploadFTP(fileMap, userpath,filename,setday);
            }
            else{
                new StorageSourceInitException("GetSource类捕捉异常：未找到存储源");
            }
        } catch (Exception e) {
            new StorageSourceInitException("GetSource类捕捉异常：",e);
            e.printStackTrace();
        }
        return m;
    }


}
