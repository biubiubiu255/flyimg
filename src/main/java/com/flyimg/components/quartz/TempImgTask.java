package com.flyimg.components.quartz;

import com.flyimg.pojo.Keys;
import com.flyimg.service.impl.FileOssOssServiceImpl;
import com.flyimg.service.impl.KeysServiceImpl;
import com.flyimg.comm.utils.DeleImg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
单任务执行，并且通过控制器的接口实现时间间隔的动态修改
任务类
* */
//@Configuration

@Component
public class TempImgTask  {

    @Autowired
    private FileOssOssServiceImpl imgService;
    @Autowired
    private KeysServiceImpl keysService;

    private static TempImgTask tempImgTask;


    @PostConstruct
    public void init() {
        tempImgTask = this;
        tempImgTask.imgService = this.imgService;
        tempImgTask.keysService = this.keysService;
    }


    public void start() throws InterruptedException {
        List<String> list = new ArrayList<>();
        File file = new File(File.separator + "HellohaoData"+File.separator+"img.ini");
        //判断文件是否存在
        if (file.exists()) {
            StringBuilder result = new StringBuilder();
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "GBK"));//构造一个BufferedReader类来读取文件
                String s = null;
                while ((s = br.readLine()) != null) {//使用readLine方法，一次读一行
                    result.append(System.lineSeparator() + s);
                    //Print.Normal( s);
                    String str = s;
                    String[] strArr = str.split("\\|");
                    System.out.println(strArr.length); //这里输出3
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date sd1=new Date();//大时间 当前时间
                        Date sd2=df.parse(strArr[1]);//小时间
                        if(sd1.after(sd2)){//等于false时说明图片时间大于或者等于当前时间
                            Integer ret = tempImgTask.imgService.deleimgname(strArr[0]);//删除库里图片数据
                            Keys key =null;
                        }else{
                            //没有过期的临时图片
                            list.add(s);
                        }
                }
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            //return result.toString();
            textcr(list);
       } else {
                //Print.warning("file not exists, 不进行暂存扫描处理");
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }
    }

    public static void main(String[] args) throws Exception {
        clearInfoForFile(File.separator + "HellohaoData"+File.separator+"img.ini");
    }


    /**
     * 清空文件内容
     * @param fileName
     */
    public static void clearInfoForFile(String fileName) {
        //Print.Normal("清空原ini文件");
        File file =new File(fileName);
        try {
            if(!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter =new FileWriter(file);
            fileWriter.write("");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    //遍历没有过期的list文本，依次插入
    public static void textcr(List<String> list){
        clearInfoForFile(File.separator + "HellohaoData"+File.separator+"img.ini");
        //Print.Normal("开始重新插入没有过期的文本");
        for (String s : list) {
            DeleImg.charu(s);
        }
    }

}