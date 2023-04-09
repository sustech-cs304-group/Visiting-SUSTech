package com.sustech.cs304.visitingsustech.vo;

import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

@Data
public class AppointmentVo {
    private String name;
    private Date appointmentDate;
    private String idcard;
    private String phone;
    private Integer accompanyingNum;
    private String purpose;
}
