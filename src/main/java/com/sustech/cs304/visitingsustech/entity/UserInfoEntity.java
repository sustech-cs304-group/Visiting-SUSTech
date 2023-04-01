package com.sustech.cs304.visitingsustech.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user_info")
public class UserInfoEntity {
    @TableId
    private String openid;
    private String name;
    private String phone;
    private String identityCard;
    private String type;
    private Integer gender;
}
