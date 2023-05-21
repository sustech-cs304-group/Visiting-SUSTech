package com.sustech.cs304.visitingsustech.vo;

import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Vo for appointment info introduction.
 *
 * @author pound
 */
@Data
public class AppointmentVo {
    private Integer id;
    private String name;
    private Date appointmentDate;
    private String identityCard;
    private String phone;
    private Integer accompanyingNum;
    private String purpose;
}
