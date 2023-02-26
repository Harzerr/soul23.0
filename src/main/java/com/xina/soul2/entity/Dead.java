package com.xina.soul2.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@TableName("dead")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Dead {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String dname;
    private String daddress;
    private String dsex;
    private Date dbirth;
    private Date ddeath;
    private Integer df_fnumber;
    private String dlastcomment;
    private Integer dwreathnum;
    private String ddescribe;
    private String dphoto;
}
