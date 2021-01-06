package com.flyimg.service.init;

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


    @Override
    public void run(String... args) throws Exception {
        sout();
    }


    public void sout(){
        Print.Normal("______________________________________________");
        Print.Normal("              Hellohao-Pro                    ");
        Print.Normal("            startup Successful                ");
        Print.Normal("     is OK!  Open http://your ip:port         ");
        Print.Normal("______________________________________________");
    }
}
