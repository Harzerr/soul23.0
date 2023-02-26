package com.xina.soul2.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("funeral")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Funeral {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String fname;
    private Integer fnumber;
    private String fpassword;
    private String faddress;
    private String fphone;
    private String fculture;
}
