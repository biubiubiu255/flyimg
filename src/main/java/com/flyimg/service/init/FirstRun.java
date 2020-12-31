package com.flyimg.service.init;

import com.flyimg.comm.utils.Print;
import com.flyimg.comm.utils.RunSqlScript;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

/**
 * @author asher
 * @version 1.1
 * @date 2020/12/28 16:56
 */
@Slf4j
@Configuration
public class FirstRun implements InitializingBean {

    @Value("${spring.datasource.username}")
    private String jdbcusername;

    @Value("${spring.datasource.password}")
    private String jdbcpass;

    @Value("${spring.datasource.url}")
    private String jdbcurl;

    @Override
    public void afterPropertiesSet() throws Exception {

        log.info("数据库结构校验完成（当前流程已跳过，可自行恢复~~qaq）");

        if (true){
            return;
        }

        RunSqlScript.USERNAME = jdbcusername;
        RunSqlScript.PASSWORD = jdbcpass;
        RunSqlScript.DBURL = jdbcurl;
        Print.Normal("正在校验数据库参数...");
        RunSqlScript.RunInsert(dynamic);
        RunSqlScript.RunInsert(compressed);

        String uid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        RunSqlScript.RunInsert("update user set uid='"+uid+"' where id = 1");
        Integer ret1 = RunSqlScript.RunSelectCount(sql1);
        if(ret1==0){
            Print.Normal("In execution...");
            RunSqlScript.RunInsert(sql2);
            ;

        }else{
            if(ret1>0){
                Print.Normal("Stage 1");
            }else{
                Print.Normal("Mysql 报了一个错");
            }
        }
        Print.Normal("Stage success");
    }

    //检查表是否存在，后边加'imgdata' and column_name = 'explains'  检查字段是否存在
    private String judgeTable = "select count(*) from information_schema.columns where table_name = ";
    //创建blacklist  2019-11-29
    private String sql1 = "select count(*) from information_schema.columns where table_name = 'uploadconfig' and column_name = 'blacklist'";
    private String sql2 = "alter table uploadconfig add blacklist varchar(500);";
    //创建imgandalbum和album  添加imgdata表字段explain 2019-12-20
    private String sql3 ="CREATE TABLE `imgandalbum`  (`imgname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,`albumkey` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic";
    private String sql4 ="CREATE TABLE `album`  (`albumkey` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,`albumtitle` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,`createdate` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL, `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic";
    private String sql6 = "alter table imgdata add explains varchar(500)";
    //添加album表字段userid
    private String sql7 = "alter table album add userid int(10)";
    //添加imgdata表字段md5key
    private String sql8 = "alter table imgdata add md5key varchar(500)";
    private String sql9 = "ALTER TABLE config ADD theme int(4) DEFAULT '1' COMMENT '主题'  ";


    private String inddx_md5key = "ALTER TABLE imgdata ADD INDEX index_md5key_url ( md5key,imgurl)";

    private String dynamic = "alter table imgdata row_format=dynamic";
    private String compressed = "alter table imgdata row_format=compressed";


}
