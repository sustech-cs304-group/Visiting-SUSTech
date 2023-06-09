package com.sustech.cs304.visitingsustech.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sustech.cs304.visitingsustech.entity.AppointmentEntity;
import com.sustech.cs304.visitingsustech.entity.UserInfoEntity;
import com.sustech.cs304.visitingsustech.exception.AppointmentException;
import com.sustech.cs304.visitingsustech.mapper.AppointmentMapper;
import com.sustech.cs304.visitingsustech.mapper.UserInfoMapper;
import com.sustech.cs304.visitingsustech.service.AppointmentService;
import com.sustech.cs304.visitingsustech.util.IdCardValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * Implementation of appointment service.
 *
 * @author pound
 */
@Service
public class AppointmentServiceImpl extends ServiceImpl<AppointmentMapper, AppointmentEntity> implements AppointmentService {
    @Autowired
    private AppointmentMapper appointmentMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;


    @Override
    public int addAppointment(AppointmentEntity appointmentEntity) {
        UserInfoEntity userInfoEntity = userInfoMapper.selectById(appointmentEntity.getOpenid());
        if (userInfoEntity == null)
            throw new AppointmentException("Invalid userID", 400);
        if (!IdCardValidator.isValid(appointmentEntity.getIdentityCard()))
            throw new AppointmentException("Invalid identityCard", 400);
        if (!appointmentEntity.getPhone().matches("^1[3-9]\\d{9}$"))
            throw new AppointmentException("Invalid phone number", 400);
        appointmentEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));
        return appointmentMapper.insert(appointmentEntity);
    }

    @Override
    public int deleteAppointment(String openid, Integer id) {
        AppointmentEntity appointmentEntity = appointmentMapper.selectById(id);
        UserInfoEntity userInfoEntity = userInfoMapper.selectById(openid);
        if (appointmentEntity == null || userInfoEntity == null)
            throw new AppointmentException("Invalid appointmentID or userID", 400);
        if (!(userInfoEntity.getType().equals("admin") || appointmentEntity.getOpenid().equals(openid)))
            throw new AppointmentException("You are not allowed to delete this appointment", 403);
        return appointmentMapper.deleteById(id);
    }

    @Override
    public int updateAppointment(String openid, AppointmentEntity appointmentEntity) {
        UserInfoEntity userInfoEntity = userInfoMapper.selectById(openid);
        if (userInfoEntity == null)
            throw new AppointmentException("Invalid userID", 400);
        if (appointmentEntity.getIdentityCard() != null && !IdCardValidator.isValid(appointmentEntity.getIdentityCard()))
            throw new AppointmentException("Invalid identityCard", 400);
        if (appointmentEntity.getPhone() != null && !appointmentEntity.getPhone().matches("^1[3-9]\\d{9}$"))
            throw new AppointmentException("Invalid phone number", 400);
        if (!(userInfoEntity.getType().equals("admin") || appointmentEntity.getOpenid().equals(openid)))
            throw new AppointmentException("You are not allowed to update this appointment", 403);
        return appointmentMapper.updateById(appointmentEntity);
    }

    @Override
    public List<AppointmentEntity> getAppointment(String openid) {
        UserInfoEntity userInfoEntity = userInfoMapper.selectById(openid);
        if (userInfoEntity == null)
            throw new AppointmentException("Invalid userID", 400);
        QueryWrapper<AppointmentEntity> appointWrapper;
        if (userInfoEntity.getType().equals("user"))
            appointWrapper = new QueryWrapper<AppointmentEntity>().eq("openid", openid).orderByDesc("appointment_date");
        else
            appointWrapper = new QueryWrapper<AppointmentEntity>().eq("status", 0).orderByDesc("create_time");
        return appointmentMapper.selectList(appointWrapper);
    }
}
