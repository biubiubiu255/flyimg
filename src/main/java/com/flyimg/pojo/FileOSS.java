package com.flyimg.pojo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class FileOSS {
    // 默认的时间字符串格式

    //id, imgname, imgurl, userid
    private Integer id;
    private String filename;
    private String uri;
    private Integer userid;
    private String  abnormal;
    private Integer imgtype;
    private String updatetime;
    private String username;
    private Integer storageType;
    private String starttime;
    private String stoptime;
    @Length(min = 1, max = 100, message = "图片描述不得超过100个字符")
    private String explains;
    private String md5;
    //Album
    @NotBlank(message = "画廊标题不能为空")
    @Length(min = 1, max = 50, message = "画廊标题不得超过50个字符")
    private String albumtitle;
    @Length(min = 0, max = 10, message = "画廊密码不能超过10个字符")
    private String password;
    private Integer selecttype;

    public FileOSS() {
        super();
    }

    public FileOSS(Integer id, String filename, String uri, Integer userid, Integer sizes, String abnormal, Integer source,
                   Integer imgtype, String updatetime, String username, Integer storageType, String starttime, String stoptime,
                   String explains, String albumtitle, String password, String md5, Integer selecttype) {
        this.id = id;
        this.filename = filename;
        this.uri = uri;
        this.userid = userid;
        this.abnormal = abnormal;
        this.imgtype = imgtype;
        this.updatetime = updatetime;
        this.username = username;
        this.storageType = storageType;
        this.starttime = starttime;
        this.stoptime = stoptime;
        this.explains = explains;
        this.albumtitle = albumtitle;
        this.password = password;
        this.md5 = md5;
        this.selecttype = selecttype;
    }



}
	
		

