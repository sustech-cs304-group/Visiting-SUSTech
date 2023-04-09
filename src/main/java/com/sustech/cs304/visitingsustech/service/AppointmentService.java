package com.sustech.cs304.visitingsustech.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.sustech.cs304.visitingsustech.entity.AppointmentEntity;

import java.util.List;

public interface AppointmentService extends IService<AppointmentEntity> {

    public int addAppointment(AppointmentEntity appointmentEntity);

    public int deleteAppointment(String openid, Integer id);

    public int updateAppointment(String openid, AppointmentEntity appointmentEntity);

    public List<AppointmentEntity> getAppointment(String openid);
}