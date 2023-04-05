package com.sustech.cs304.visitingsustech.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sustech.cs304.visitingsustech.entity.AppointmentEntity;
import com.sustech.cs304.visitingsustech.entity.UserInfoEntity;
import com.sustech.cs304.visitingsustech.exception.AppointmentException;
import com.sustech.cs304.visitingsustech.mapper.AppointmentMapper;
import com.sustech.cs304.visitingsustech.mapper.UserInfoMapper;
import com.sustech.cs304.visitingsustech.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AppointmentServiceImpl extends ServiceImpl<AppointmentMapper, AppointmentEntity> implements AppointmentService {
    @Autowired
    private AppointmentMapper appointmentMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public void validation(AppointmentEntity appointmentEntity) {
        if ((appointmentEntity.getEntryTime().getTime() - appointmentEntity.getDepartureTime().getTime()) <= 0)
            throw new AppointmentException("Invalid Time");
        QueryWrapper<UserInfoEntity> userIndoWrapper = new QueryWrapper<UserInfoEntity>()
                .eq("openid", appointmentEntity.getOpenid());
        List<Map<String, Object>> maps = userInfoMapper.selectMaps(userIndoWrapper);
        if (maps.size() == 0)
            throw new AppointmentException("Invalid UserID");
        if (appointmentEntity.getAccompanyingName().length() > 0 &&
                appointmentEntity.getAccompanyingIdentityCard().length() != 18)
            throw new AppointmentException("Invalid accompanyingIdentityCard");
    }

    @Override
    public int addAppointment(AppointmentEntity appointmentEntity) {
        validation(appointmentEntity);
        return appointmentMapper.insert(appointmentEntity);
    }

    @Override
    public int deleteAppointment(Integer id, String openid) {
        QueryWrapper<AppointmentEntity> appointmentQueryWrapper = new QueryWrapper<AppointmentEntity>()
                .eq("openid", openid).eq("id", id);
        List<Map<String, Object>> res = appointmentMapper.selectMaps(appointmentQueryWrapper);
        if (res.size() == 0)
            throw new AppointmentException("Appointment Not Found");
        return appointmentMapper.delete(appointmentQueryWrapper);
    }

    @Override
    public int updateAppointment(AppointmentEntity appointmentEntity) {
        return appointmentMapper.updateById(appointmentEntity);
    }

    @Override
    public AppointmentEntity getAppointment(Integer id, String openid) {
        return appointmentMapper.selectById(id);
    }
}
