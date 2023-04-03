package com.sustech.cs304.visitingsustech.vo;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class AppointmentVo {
    private Integer id;
    private Timestamp entryTime;
    private Timestamp departureTime;
    private String status;
    private String accompanyingName;
    private String accompanyingIdentityCard;
}
