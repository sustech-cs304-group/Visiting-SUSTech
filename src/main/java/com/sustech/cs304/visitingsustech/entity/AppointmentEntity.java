package com.sustech.cs304.visitingsustech.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

@Data
@TableName("appointment")
public class AppointmentEntity {
    @TableId
    private Integer id;
    private Integer userId;
    private Timestamp entryTime;
    private Timestamp departureTime;
    private String status;
    private String accompanyingName;
    private String accompanyingIdentityCard;
}
