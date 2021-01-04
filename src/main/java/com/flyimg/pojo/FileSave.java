package com.flyimg.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class FileSave {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String  uri;
    private String  md5;
    private Integer  size;
    private Integer  pointId;

}
	
		

