package com.flyimg.controller.page;

import com.flyimg.pojo.*;
import com.flyimg.service.*;
import com.flyimg.service.impl.UploadServiceImpl;
import com.flyimg.comm.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

@Controller
public class UpdateImgController {

    @Autowired
    private UserService userService;
    @Autowired
    private KeysService keysService;
    @Autowired
    private ConfigService configService;
    @Autowired
    private SysConfigService sysConfigService;
    @Autowired
    private FileOssService fileOssService;
    @Autowired
    private UploadServiceImpl uploadServiceImpl;

    private String[] iparr;


    @GetMapping(value = "/images/{id}")
    @ResponseBody
    public FileOss selectByFy(@PathVariable("id") Integer id) {
        return fileOssService.selectByPrimaryKey(id);
    }


    @RequestMapping("/{key1}/TOIMG{key2}N.{key3}")
    public void selectByFy(HttpServletRequest request, HttpServletResponse response,
                             @PathVariable("key1") String key1, @PathVariable("key2") String key2,
                             @PathVariable("key3") String key3, Model model){
        String head = "jpg";
        if(key3.equals("jpg")||key3.equals("jpeg")){
            head = "jpeg";
        }else if(key3.equals("png")){
            head = "png";
        }else if(key3.equals("bmp")){
            head = "bmp";
        }else if(key3.equals("gif")){
            head = "gif";
        }else{
            head = key3;
        }
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/"+head);
        InputStream is= null;
        BufferedImage bi=null;
        try {
            is = new FileInputStream(new File(File.separator+"HellohaoData"+File.separator+key1+"/TOIMG"+key2+"N."+key3));
            bi= ImageIO.read(is);
            is.close();
            //将图片输出给浏览器
            BufferedImage image = (bi) ;
            OutputStream os = response.getOutputStream();
            ImageIO.write(image, head, os);
        } catch (Exception e) {
            Print.warning("寻找本地文件出错："+e.getMessage());
            e.printStackTrace();
            try {
                response.sendRedirect("/404");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        //return "forward:/links/"+key1+"/TOIMG"+key2+"N."+key3;
    }


    @RequestMapping("/{key1:\\d+}/{key2}/{key3}/TOIMG{key4}N.{key5}")
    public void selectByFy2(HttpServletRequest request, HttpServletResponse response,
                              @PathVariable("key1") String key1,@PathVariable("key2") String key2,
                              @PathVariable("key3") String key3,@PathVariable("key4") String key4,
                              @PathVariable("key5") String key5,Model model) {
        String head = "jpg";

        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()){
            String name = headerNames.nextElement();
            String header = request.getHeader(name);
            System.out.println(name + ": " + header);
        }
        System.out.println("==================");
        System.out.println("getRemoteHost: " + request.getRemoteHost());
        System.out.println("getRemoteAddr: " + request.getRemoteAddr());
        System.out.println("getRequestURL: " + request.getRequestURL());
        System.out.println("getRequestURL: " + request.getRequestURL());
        System.out.println("getServletPath: " + request.getServletPath());
        System.out.println("getServerName: " + request.getServerName());
        System.out.println("getServletContext: " + request.getServletContext());



        if(key5.equals("jpg")||key5.equals("jpeg")){
            head = "jpeg";
        }else if(key5.equals("png")){
            head = "png";
        }else if(key5.equals("bmp")){
            head = "bmp";
        }else if(key5.equals("gif")){
            head = "gif";
        }else{
            head = key5;
        }
        response.setHeader("Pragma", "max-age=315360000");
        response.setHeader("Cache-Control", "max-age=315360000");
        response.setHeader("Expires", "Sat, 26 Dec 2030 02:57:49 GMT");
        //response.setDateHeader("Expires", 0);


/*        if (request.getHeader("If-None-Match")!= null){
            response.setStatus(304);
            return;
        }*/

        response.setContentType("image/"+head);



        InputStream is= null;
        BufferedImage bi=null;
        try {
            is = new FileInputStream(new File(File.separator+"HellohaoData"+File.separator+key1+"/"+key2+"/"+key3+"/TOIMG"+key4+"N."+key5));
            bi= ImageIO.read(is);
            // 返回浏览器资源标志
            //response.setHeader("ETag", CryptoUtils.crc32(is));
            is.close();
            //将图片输出给浏览器
            BufferedImage image = (bi) ;
            OutputStream os = response.getOutputStream();
            ImageIO.write(image, head, os);

        } catch (Exception e) {
            Print.warning("寻找本地文件出错："+e.getMessage());
            e.printStackTrace();
            try {
                response.sendRedirect("/404");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }



/*        InputStream is= null;
        BufferedImage bi=null;
            is = new FileInputStream(new File(File.separator+"HellohaoData"+File.separator+key1+"/"+key2+"/"+key3+"/TOIMG"+key4+"N."+key5));
            bi= ImageIO.read(is);
            is.close();

        //将验证码存入Session
        //将图片输出给浏览器
        BufferedImage image = (bi) ;
        response.setContentType("image/JPEG");
        OutputStream os = response.getOutputStream();
        ImageIO.write(image, "JPEG", os);*/


    }

    private Integer yzupdate(){
        Calendar cal = Calendar.getInstance();
        int y=cal.get(Calendar.YEAR);
        int m=cal.get(Calendar.MONTH);
        int d=cal.get(Calendar.DATE);
        //int h=cal.get(Calendar.HOUR_OF_DAY);
        //int mm=cal.get(Calendar.MINUTE);
        return y+m+d+999;
    }

    @RequestMapping("/err")
    public String err() {
        return "err";
    }


}
