package com.sustech.cs304.visitingsustech.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Stores appointment info.
 *
 * @author pound
 */
@Data
@TableName("appointment")
public class AppointmentEntity {
    @TableId
    private Integer id;
    private String openid;
    private String name;
    private Date appointmentDate;
    private String identityCard;
    private Integer status;
    private String phone;
    private Integer accompanyingNum;
    private String purpose;
    private String comment;
    private Timestamp createTime;
}
