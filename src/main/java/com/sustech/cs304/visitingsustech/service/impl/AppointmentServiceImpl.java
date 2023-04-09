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
        QueryWrapper<UserInfoEntity> userIndoWrapper = new QueryWrapper<UserInfoEntity>()
                .eq("openid", appointmentEntity.getOpenid());
        List<Map<String, Object>> maps = userInfoMapper.selectMaps(userIndoWrapper);
        if (maps.size() == 0)
            throw new AppointmentException("Invalid UserID");
        if (appointmentEntity.getIdcard().length() != 18)
            throw new AppointmentException("Invalid accompanyingIdentityCard");
    }

    @Override
    public int addAppointment(AppointmentEntity appointmentEntity) {
        validation(appointmentEntity);
        return appointmentMapper.insert(appointmentEntity);
    }

    @Override
    public int deleteAppointment(Integer id) {
//        QueryWrapper<AppointmentEntity> appointmentQueryWrapper = new QueryWrapper<AppointmentEntity>()
//                .eq("id", id);
//        List<Map<String, Object>> res = appointmentMapper.selectMaps(appointmentQueryWrapper);
//        if (res.size() == 0)
//            throw new AppointmentException("Appointment Not Found");
        return appointmentMapper.deleteById(id);
    }

    @Override
    public int updateAppointment(AppointmentEntity appointmentEntity) {
        return appointmentMapper.updateById(appointmentEntity);
    }

    @Override
    public List<AppointmentEntity> getAppointment(String openid) {
//        QueryWrapper<UserInfoEntity> userWrapper = new QueryWrapper<UserInfoEntity>()
//                .eq("openid", openid).select("type");
//        List<Map<String, Object>> res = userInfoMapper.selectMaps(userWrapper);
//        QueryWrapper<AppointmentEntity> appointWrapper;
//        if (res.get(0).get("type") == "user") {
//            appointWrapper = new QueryWrapper<AppointmentEntity>().eq("openid", openid);
//        } else {
//            appointWrapper = new QueryWrapper<AppointmentEntity>().eq("status", "未审批");
//        }
        QueryWrapper<AppointmentEntity> appointWrapper = new QueryWrapper<AppointmentEntity>().eq("openid", openid);
        return appointmentMapper.selectList(appointWrapper);
    }
}
