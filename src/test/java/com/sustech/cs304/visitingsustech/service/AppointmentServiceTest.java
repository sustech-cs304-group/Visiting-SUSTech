package com.sustech.cs304.visitingsustech.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sustech.cs304.visitingsustech.VisitingSusTechApplication;
import com.sustech.cs304.visitingsustech.controller.CommentController;
import com.sustech.cs304.visitingsustech.entity.AppointmentEntity;
import com.sustech.cs304.visitingsustech.entity.CommentEntity;
import com.sustech.cs304.visitingsustech.entity.ForumEntity;
import com.sustech.cs304.visitingsustech.entity.UserInfoEntity;
import com.sustech.cs304.visitingsustech.mapper.UserInfoMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Date;
import java.sql.Timestamp;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = VisitingSusTechApplication.class)
public class AppointmentServiceTest {

    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private ForumService forumService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserInfoMapper userInfoMapper;
    private AppointmentEntity appointmentEntity = new AppointmentEntity();

    @Test
    public void addTest(){
        userService.insertUser("999");
        QueryWrapper<UserInfoEntity> queryWrapper = new QueryWrapper<UserInfoEntity>().eq("openid", "999");
        UserInfoEntity userInfoEntity = userInfoMapper.selectOne(queryWrapper);
        userInfoEntity.setType("admin");
        userInfoMapper.updateById(userInfoEntity);
        appointmentEntity.setId(999);
        appointmentEntity.setOpenid("999");
        appointmentEntity.setCreateTime(Timestamp.valueOf("2002-10-10 00:00:00"));
        appointmentEntity.setAppointmentDate(Date.valueOf("2002-10-10"));
        appointmentEntity.setStatus(0);
        appointmentEntity.setIdentityCard("370303200109137410");
        appointmentEntity.setPhone("13025522222");
        appointmentEntity.setAccompanyingNum(0);
        appointmentEntity.setPurpose("test");
        appointmentEntity.setName("test");
        Assert.assertEquals(1, appointmentService.addAppointment(appointmentEntity));
    }

    @Test
    public void deleteTest(){
        Assert.assertEquals(1, appointmentService.deleteAppointment("999", 999));
    }

    @Test
    public void updateTest(){
        userService.insertUser("9999");
        QueryWrapper<UserInfoEntity> queryWrapper = new QueryWrapper<UserInfoEntity>().eq("openid", "999");
        UserInfoEntity userInfoEntity = userInfoMapper.selectOne(queryWrapper);
        userInfoEntity.setType("admin");
        userInfoMapper.updateById(userInfoEntity);
        appointmentEntity.setId(9999);
        appointmentEntity.setOpenid("9999");
        appointmentEntity.setCreateTime(Timestamp.valueOf("2002-10-10 00:00:00"));
        appointmentEntity.setAppointmentDate(Date.valueOf("2002-10-10"));
        appointmentEntity.setStatus(0);
        appointmentEntity.setIdentityCard("370303200109137410");
        appointmentEntity.setPhone("13025522222");
        appointmentEntity.setAccompanyingNum(0);
        appointmentEntity.setPurpose("test");
        appointmentEntity.setName("test");
        appointmentService.addAppointment(appointmentEntity);
        appointmentEntity.setStatus(1);
        Assert.assertEquals(1, appointmentService.updateAppointment("999", appointmentEntity));
    }

    @Test
    public void getTest(){
        System.out.println(appointmentService.getAppointment("999"));
    }
}
