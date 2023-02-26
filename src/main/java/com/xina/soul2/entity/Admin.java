package com.xina.soul2.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("admin")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Admin {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String aphone;
    private String apassword;
}
