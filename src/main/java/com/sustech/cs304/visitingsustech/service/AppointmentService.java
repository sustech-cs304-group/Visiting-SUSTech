package com.sustech.cs304.visitingsustech.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.sustech.cs304.visitingsustech.entity.AppointmentEntity;

public interface AppointmentService extends IService<AppointmentEntity> {
    public String Validation(AppointmentEntity appointmentEntity);

    public String Validation(Integer id, String openid);

    public void addAppointment(AppointmentEntity appointmentEntity);

    public void deleteAppointment(Integer id, String openid);

    public void updateAppointment(AppointmentEntity appointmentEntity);

    public AppointmentEntity getAppointment(Integer id, String openid);
}