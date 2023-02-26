package com.xina.soul2.entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserReturnFront {
    private Integer id;
    private String uphone;
    private String upassword;
    private String uemail;
    private String uheadimg;
    private String unickname;
    private String token;
}
