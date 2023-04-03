package com.sustech.cs304.visitingsustech.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sustech.cs304.visitingsustech.entity.AppointmentEntity;
import com.sustech.cs304.visitingsustech.entity.UserInfoEntity;
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
    public String Validation(AppointmentEntity appointmentEntity) {
        if ((appointmentEntity.getEntryTime().getTime() - appointmentEntity.getDepartureTime().getTime()) <= 0) {
            return "false+Invalid Time";
        }

        QueryWrapper<UserInfoEntity> userIndoWrapper = new QueryWrapper<UserInfoEntity>()
                .eq("openid", appointmentEntity.getOpenid());
        List<Map<String, Object>> maps = userInfoMapper.selectMaps(userIndoWrapper);
        if (maps.size() == 0) {
            return "false+Invalid UserID";
        }

        if (appointmentEntity.getAccompanyingName().length() > 0 &&
                appointmentEntity.getAccompanyingIdentityCard().length() != 18) {
            return "false+Invalid accompanyingIdentityCard";
        }

        return "true";
    }

    @Override
    public void addAppointment(AppointmentEntity appointmentEntity) {
        QueryWrapper<AppointmentEntity> appointmentWrapper = new QueryWrapper<AppointmentEntity>().isNotNull("id");
        List<Map<String, Object>> maps = appointmentMapper.selectMaps(appointmentWrapper);
        appointmentEntity.setId(maps.size());
        appointmentEntity.setStatus("未验证");
        appointmentMapper.insert(appointmentEntity);
    }

    public String Validation(Integer id, String openid) {
        QueryWrapper<AppointmentEntity> appointmentQueryWrapper = new QueryWrapper<AppointmentEntity>()
                .eq("openid", openid).eq("id", id);
        List<Map<String, Object>> maps = appointmentMapper.selectMaps(appointmentQueryWrapper);
        if (maps.size() == 0) {
            return "false+Invalid ID and UserID";
        }

        return "true";
    }

    @Override
    public void deleteAppointment(Integer id, String openid) {
        QueryWrapper<AppointmentEntity> appointmentQueryWrapper = new QueryWrapper<AppointmentEntity>()
                .eq("openid", openid).eq("id", id);
        appointmentMapper.delete(appointmentQueryWrapper);
    }

    @Override
    public void updateAppointment(AppointmentEntity appointmentEntity) {
        appointmentMapper.update(appointmentEntity, new QueryWrapper<AppointmentEntity>().eq("id", appointmentEntity.getId()));
    }

    @Override
    public AppointmentEntity getAppointment(Integer id, String openid) {
        return appointmentMapper.selectById(id);
    }
}
