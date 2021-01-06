package com.flyimg.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@TableName("user")
public class User {

    @TableId(type = IdType.AUTO)
    private Integer id;
    @Length(min = 6, max = 20, message = "密码需要为 6 - 20 个字符")
    private String password;
   // @NotBlank(message = "邮箱不能为空")
    //@Email(message = "邮箱格式不正确")
    @Length(min = 4, max = 20, message = "邮箱需要为 4 - 20 个字符")
    private String  email;
    private String  phone;
    private Integer status;
    private String  secret;
    private Integer memory;
    private Integer memoryUsed;
    private Long createdTime;



}
