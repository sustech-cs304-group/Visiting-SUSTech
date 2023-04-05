package com.sustech.cs304.visitingsustech.controller;

import com.sustech.cs304.visitingsustech.common.JsonResult;
import com.sustech.cs304.visitingsustech.entity.AppointmentEntity;
import com.sustech.cs304.visitingsustech.exception.BaseException;
import com.sustech.cs304.visitingsustech.service.AppointmentService;
import com.sustech.cs304.visitingsustech.util.JwtUtil;
import com.sustech.cs304.visitingsustech.vo.AppointmentVo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        try {
            if (appointmentService.addAppointment(appointmentEntity) > 0)
                return JsonResult.success();
            else
                return JsonResult.error("添加失败");
        } catch (BaseException e) {
            return JsonResult.error(e.getStatus(), e.getMessage());
        }
    }

    @PostMapping("/delete")
    public JsonResult<Void> deleteAppointment(@RequestParam("id") Integer id,
                                              HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        String openid = jwtUtil.getOpenidFromToken(token);
        try {
            if (appointmentService.deleteAppointment(id, openid) > 0)
                return JsonResult.success();
            else
                return JsonResult.error("删除失败");
        } catch (BaseException e) {
            return JsonResult.error(e.getStatus(), e.getMessage());
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
        try {
            appointmentService.validation(appointmentEntity);
            if (appointmentService.updateAppointment(appointmentEntity) > 0)
                return JsonResult.success();
            else
                return JsonResult.error("更新失败");
        } catch (BaseException e) {
            return JsonResult.error(e.getStatus(), e.getMessage());
        }
    }

    @PostMapping("/get")
    public JsonResult<AppointmentEntity> getAppointment(@RequestParam("id") Integer id,
                                                        HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        String openid = jwtUtil.getOpenidFromToken(token);
        return JsonResult.success(appointmentService.getAppointment(id, openid));
    }
}
