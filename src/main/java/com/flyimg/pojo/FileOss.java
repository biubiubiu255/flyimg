package com.flyimg.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@TableName("file_list")
public class FileOss {
    // 默认的时间字符串格式

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String md5;
    private String uri;
    private String dir;
    private String filename;
    private Integer userid;
    private String suffix;
    private Integer headerCode;
    private Long expire;
    private Long updatedTime;

}
	
		

