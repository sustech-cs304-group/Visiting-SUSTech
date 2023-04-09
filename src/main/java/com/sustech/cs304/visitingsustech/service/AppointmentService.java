package com.sustech.cs304.visitingsustech.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.sustech.cs304.visitingsustech.entity.AppointmentEntity;

import java.util.List;

public interface AppointmentService extends IService<AppointmentEntity> {
    public void validation(AppointmentEntity appointmentEntity);

    public int addAppointment(AppointmentEntity appointmentEntity);

    public int deleteAppointment(Integer id);

    public int updateAppointment(AppointmentEntity appointmentEntity);

    public List<AppointmentEntity> getAppointment(String openid);
}