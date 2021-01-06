package com.flyimg.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class WebPanel {

    private Integer userid;
    private Integer requestNum;
    private Integer flowYesterday;
    private Integer fileNum;
    private Integer memBanlance;
    private Long updatedTime;

}
