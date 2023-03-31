package com.sustech.cs304.visitingsustech.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serial;

@Data
@TableName("user_info")
public class UserInfoEntity {
    @TableId
    private Integer id;
    private String name;
    private String phone;
    private String identityCard;
    private String wechatId;
    private String type;
}
