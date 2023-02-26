package com.xina.soul2.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@TableName("user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String uphone;
    private String upassword;
    private String uemail;
    private String uheadimg;
    private String unickname;
}
