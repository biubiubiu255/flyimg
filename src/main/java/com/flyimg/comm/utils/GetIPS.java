package com.flyimg.comm.utils;
import com.flyimg.service.impl.FileServiceImpl;

/**
 * @author Hellohao
 * @version 1.0
 * @date 2019/11/6 17:35
 */
public class GetIPS implements Runnable {
    private String imgname ;

    public void setImgname(String imgname) {
        this.imgname = imgname;
    }
    @Override
    public void run() {
        FileServiceImpl imgService = SpringContextHolder.getBean(FileServiceImpl.class);
    }
    public static void runxc(String imgnames){
        GetIPS getIPS = new GetIPS();
        getIPS.setImgname(imgnames);
        Thread thread = new Thread(getIPS);
        thread.start();
    }


    /*** * 用于测试跨域 * @author huangweii * 2015年5月29日 */




}
