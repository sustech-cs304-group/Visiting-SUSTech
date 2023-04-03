package com.sustech.cs304.visitingsustech.controller;

import com.sustech.cs304.visitingsustech.common.JsonResult;
import com.sustech.cs304.visitingsustech.entity.AppointmentEntity;
import com.sustech.cs304.visitingsustech.service.AppointmentService;
import com.sustech.cs304.visitingsustech.util.JwtUtil;
import com.sustech.cs304.visitingsustech.vo.AppointmentVo;
import com.sustech.cs304.visitingsustech.vo.UserInfoVo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public JsonResult<Void> addAppointment(@RequestBody AppointmentVo appointmentVo,
                                           HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        String openid = jwtUtil.getOpenidFromToken(token);
        AppointmentEntity appointmentEntity = new AppointmentEntity();
        BeanUtils.copyProperties(appointmentVo, appointmentEntity);
        appointmentEntity.setOpenid(openid);
        appointmentEntity.setStatus("未验证");
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
    public JsonResult<Void> updateAppointment(@RequestBody AppointmentVo appointmentVo,
                                              HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        String openid = jwtUtil.getOpenidFromToken(token);
        AppointmentEntity appointmentEntity = new AppointmentEntity();
        BeanUtils.copyProperties(appointmentVo, appointmentEntity);
        appointmentEntity.setOpenid(openid);
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
