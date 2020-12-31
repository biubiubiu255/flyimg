package com.flyimg.pojo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class User {

    private Integer id;
    @Length(min = 6, max = 20, message = "密码需要为 6 - 20 个字符")
    private String password;
   // @NotBlank(message = "邮箱不能为空")
    //@Email(message = "邮箱格式不正确")
    @Length(min = 4, max = 20, message = "邮箱需要为 4 - 20 个字符")
    private String  email;
    private String  phone;
    private Integer level = 0;
    private Integer status;
    private String  key;
    private Long memory;
    private Long memoryUsed;
    private Long createdTime;


    public User() {
        super();
    }

    public User(Integer id, String password, String email, String phone, String key, Integer status, Long memory) {
        this.id = id;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.key = key;
        this.status = status;
        this.memory = memory;
    }

}
