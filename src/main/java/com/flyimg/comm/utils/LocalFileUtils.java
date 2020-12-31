package com.flyimg.comm.utils;

import com.flyimg.pojo.ReturnImage;
import com.flyimg.pojo.SysConfig;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LocalFileUtils {

    public static void deleteFile(String path){
        File file = new File(path);
        file.delete();
    }

    public static void copyByFileChannelWithBuffer(File srcFile,File desFile) throws IOException
    {
        // 方式一，速度最快，但是最大只能2G
        FileChannel srcChannel = new FileInputStream(srcFile).getChannel();
        FileChannel desChannel = new FileOutputStream(desFile).getChannel();
        srcChannel.transferTo(0,srcChannel.size(),desChannel);
        srcChannel.close();
        desChannel.close();

        // 方式二，速度比第一种慢一点，但无限制
        /*
        FileChannel srcChannel = new FileInputStream(srcFile).getChannel();
        FileChannel desChannel = new FileOutputStream(desFile).getChannel();
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
        while(srcChannel.read(buffer) != -1)
        {
            buffer.flip();
            desChannel.write(buffer);
            buffer.clear();
        }
        srcChannel.close();
        desChannel.close();
        */
    }


    public static void copyByFileChannelWithBuffer(InputStream input, File desFile) throws IOException
    {
        FileInputStream fileInputStream = (FileInputStream)input;
        // 方式一，速度最快，但是最大只能2G
        FileChannel srcChannel = fileInputStream.getChannel();
        FileChannel desChannel = new FileOutputStream(desFile).getChannel();
        srcChannel.transferTo(0,srcChannel.size(),desChannel);
        srcChannel.close();
        desChannel.close();
    }

}
