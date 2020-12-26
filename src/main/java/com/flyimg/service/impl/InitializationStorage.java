package com.flyimg.service.impl;

import com.flyimg.dao.KeysMapper;
import com.flyimg.pojo.Keys;
import com.flyimg.utils.Print;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Hellohao
 * @version 1.0
 * @date 2019/8/28 15:33
 */

@Component
@Order(2)   //通过order值的大小来决定启动的顺序
public class InitializationStorage implements CommandLineRunner {
    @Resource
    private KeysMapper keysMapper;

    @Override
    public void run(String... args) throws Exception {
        //intiStorage();
        sout();
    }
    public void intiStorage(){
        List<Keys> keylist = keysMapper.getKeys();
        for (Keys key : keylist) {
            if(key.getStorageType()!=0 && key.getStorageType()!=null){
                int ret =0;
                if (key.getStorageType()==2){
                    ret =OSSImageupload.Initialize(key);
                }else if(key.getStorageType()==6){
                    //ret = COSImageupload.Initialize(key);
                }else if(key.getStorageType()==7){
                    ret = FTPImageupload.Initialize(key);
                }
            }
        }
        Print.Normal("初始化完成");
    }

    public void sout(){
        Print.Normal("______________________________________________");
        Print.Normal("              Hellohao-Pro                    ");
        Print.Normal("            startup Successful                ");
        Print.Normal("     is OK!  Open http://your ip:port         ");
        Print.Normal("______________________________________________");
    }
}
