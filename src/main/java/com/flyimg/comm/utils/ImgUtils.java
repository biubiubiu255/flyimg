package com.flyimg.comm.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/*
操作网络url图片工具类
* */
public class ImgUtils {

    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }
    /**
     * 获取网络文件大小
     */
    public static long getFileLength(String downloadUrl) throws IOException {
        if(downloadUrl == null || "".equals(downloadUrl) ||downloadUrl.length()<=7){
            return 0L ;
        }
        URL url = new URL(downloadUrl);
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("HEAD");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows 7; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.73 Safari/537.36 YNoteCef/5.8.0.1 (Windows)");
            return (long) conn.getContentLength();
        } catch (IOException e) {
            return 0L;
        } finally {
            conn.disconnect();
        }
    }

    /**
     * 从网络Url中下载文件
     * @param urlStr
     * @param fileName
     * @param savePath
     * @throws IOException
     */


    public static boolean  downLoadFromUrl(String urlStr,String fileName,String savePath) throws IOException{
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        //设置超时间为3秒
        conn.setConnectTimeout(5*1000);
        //防止屏蔽程序抓取而返回403错误
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
        //得到输入流
        InputStream inputStream = conn.getInputStream();
        //获取自己数组
        byte[] getData = readInputStream(inputStream);
        File saveDir = new File(savePath);
        if(!saveDir.exists()){
            saveDir.mkdir();
        }
        File file = new File(saveDir+File.separator+fileName);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(getData);
        if(fos!=null){
            fos.close();
        }
        if(inputStream!=null){
            inputStream.close();
        }
        return true;
    }
    /**
     * 从输入流中获取字节数组
     * @param inputStream
     * @return
     * @throws
     */
    public static  byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }

    /**
     * 根据java.io.*的流获取文件大小
     * @param file
     */
    public static Integer getFileSize2(File file){
        Integer imgsize = 0;
        FileInputStream fis = null;
        try {
            if(file.exists() && file.isFile()){
                String fileName = file.getName();
                fis = new FileInputStream(file);
                imgsize=fis.available()/1024;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(null!=fis){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return imgsize;
    }



    /**
     常用文件的文件头如下：(以前六位为准)
     JPEG (jpg)，文件头：FFD8FF
     PNG (png)，文件头：89504E47
     GIF (gif)，文件头：47494638
     TIFF (tif)，文件头：49492A00
     Windows Bitmap (bmp)，文件头：424D
     CAD (dwg)，文件头：41433130
     Adobe Photoshop (psd)，文件头：38425053
     Rich Text Format (rtf)，文件头：7B5C727466
     XML (xml)，文件头：3C3F786D6C
     HTML (html)，文件头：68746D6C3E
     Email [thorough only] (eml)，文件头：44656C69766572792D646174653A
     Outlook Express (dbx)，文件头：CFAD12FEC5FD746F
     Outlook (pst)，文件头：2142444E
     MS Word/Excel (xls.or.doc)，文件头：D0CF11E0
     MS Access (mdb)，文件头：5374616E64617264204A
     WordPerfect (wpd)，文件头：FF575043
     Postscript (eps.or.ps)，文件头：252150532D41646F6265
     Adobe Acrobat (pdf)，文件头：255044462D312E
     Quicken (qdf)，文件头：AC9EBD8F
     Windows Password (pwl)，文件头：E3828596
     ZIP Archive (zip)，文件头：504B0304
     RAR Archive (rar)，文件头：52617221
     Wave (wav)，文件头：57415645
     AVI (avi)，文件头：41564920
     Real Audio (ram)，文件头：2E7261FD
     Real Media (rm)，文件头：2E524D46
     MPEG (mpg)，文件头：000001BA
     MPEG (mpg)，文件头：000001B3
     Quicktime (mov)，文件头：6D6F6F76
     Windows Media (asf)，文件头：3026B2758E66CF11
     MIDI (mid)，文件头：4D546864
     */
    public static String checkPictureType(String hexHeader) {

        switch (hexHeader) {
            case "FFD8FF": return "jpg";
            case "89504E": return "png";
            case "474946": return "jif";
            case "47494638": return "gif";
            case "424D": return "bmp";

            default: return "0000";
        }
    }
}
