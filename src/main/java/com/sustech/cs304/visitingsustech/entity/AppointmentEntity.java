package com.sustech.cs304.visitingsustech.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@TableName("appointment")
public class AppointmentEntity {
    @TableId
    private Integer id;
    private String openid;
    private String name;
    private Date appointmentDate;
    private String idcard;
    private String status;
    private String phone;
    private Integer accompanyingNum;
    private String purpose;
}
