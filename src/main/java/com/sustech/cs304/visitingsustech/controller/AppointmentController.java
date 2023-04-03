package com.sustech.cs304.visitingsustech.controller;

import com.sustech.cs304.visitingsustech.common.JsonResult;
import com.sustech.cs304.visitingsustech.entity.AppointmentEntity;
import com.sustech.cs304.visitingsustech.service.AppointmentService;
import com.sustech.cs304.visitingsustech.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.util.Objects;

@Controller
@RequestMapping("/appointment")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/add")
    public JsonResult<Void> addAppointment(@RequestParam("start") Timestamp entryTime,
                                           @RequestParam("end") Timestamp departureTime,
                                           @RequestParam("accName") String accompanyingName,
                                           @RequestParam("accIdCard") String accompanyingIdentityCard,
                                           HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        String openid = jwtUtil.getOpenidFromToken(token);
        AppointmentEntity appointmentEntity = new AppointmentEntity();
        appointmentEntity.setOpenid(openid);
        appointmentEntity.setEntryTime(entryTime);
        appointmentEntity.setDepartureTime(departureTime);
        appointmentEntity.setAccompanyingName(accompanyingName);
        appointmentEntity.setAccompanyingIdentityCard(accompanyingIdentityCard);
        String res = appointmentService.Validation(appointmentEntity);
        if (Objects.equals(res, "true")) {
            appointmentService.addAppointment(appointmentEntity);
            return JsonResult.success(res);
        } else {
            return JsonResult.error(res);
        }
    }

    @PostMapping("/delete")
    public JsonResult<Void> deleteAppointment(@RequestParam("id") Integer id,
                                              HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        String openid = jwtUtil.getOpenidFromToken(token);
        String res = appointmentService.Validation(id, openid);
        if (Objects.equals(res, "true")) {
            appointmentService.deleteAppointment(id, openid);
            return JsonResult.success(res);
        } else {
            return JsonResult.error(res);
        }
    }

    @PostMapping("/update")
    public JsonResult<Void> updateAppointment(@RequestParam("id") Integer id,
                                              @RequestParam("start") Timestamp entryTime,
                                              @RequestParam("end") Timestamp departureTime,
                                              @RequestParam("status") String status,
                                              @RequestParam("accName") String accompanyingName,
                                              @RequestParam("accIdCard") String accompanyingIdentityCard,
                                              HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        String openid = jwtUtil.getOpenidFromToken(token);
        AppointmentEntity appointmentEntity = new AppointmentEntity();
        appointmentEntity.setId(id);
        appointmentEntity.setOpenid(openid);
        appointmentEntity.setEntryTime(entryTime);
        appointmentEntity.setDepartureTime(departureTime);
        appointmentEntity.setAccompanyingName(accompanyingName);
        appointmentEntity.setAccompanyingIdentityCard(accompanyingIdentityCard);
        appointmentEntity.setStatus(status);
        String res = appointmentService.Validation(appointmentEntity);
        if (Objects.equals(res, "true")) {
            appointmentService.updateAppointment(appointmentEntity);
            return JsonResult.success(res);
        } else {
            return JsonResult.error(res);
        }
    }

    @PostMapping("/get")
    public JsonResult<AppointmentEntity> getAppointment(@RequestParam("id") Integer id,
                                                        HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        String openid = jwtUtil.getOpenidFromToken(token);
        return JsonResult.success(openid, appointmentService.getAppointment(id, openid));
    }
}
