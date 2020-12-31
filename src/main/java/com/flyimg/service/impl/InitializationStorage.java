package com.flyimg.service.impl;

import com.flyimg.dao.KeysMapper;
import com.flyimg.pojo.Keys;
import com.flyimg.comm.utils.Print;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author asher
 * @version 1.1
 * @date 2020/12/26 15:33
 */

@Component
@Order(2)   //通过order值的大小来决定启动的顺序，springboot特有接口CommandLineRunner，启动时执行
public class InitializationStorage implements CommandLineRunner {
    @Resource
    private KeysMapper keysMapper;

    @Override
    public void run(String... args) throws Exception {
        intiStorage();
        sout();
    }
    public void intiStorage(){
        // 获取所有需要初始化的服务以及对应的key，比如七牛云、私有ftp等等
        List<Keys> keylist = keysMapper.getKeys();
        for (Keys key : keylist) {
            int serverType = key.getStorageType();
            // 这里可以写switch语句，根据不同的类型，调用对应的service初始化
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
