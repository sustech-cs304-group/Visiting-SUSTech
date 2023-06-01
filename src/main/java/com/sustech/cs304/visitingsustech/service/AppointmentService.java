package com.sustech.cs304.visitingsustech.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.sustech.cs304.visitingsustech.entity.AppointmentEntity;

import java.util.List;

/**
 * Appointment services(add, delete, update, query).
 *
 * @author pound
 */
public interface AppointmentService extends IService<AppointmentEntity> {

     int addAppointment(AppointmentEntity appointmentEntity);

     int deleteAppointment(String openid, Integer id);

     int updateAppointment(String openid, AppointmentEntity appointmentEntity);

     List<AppointmentEntity> getAppointment(String openid);

}