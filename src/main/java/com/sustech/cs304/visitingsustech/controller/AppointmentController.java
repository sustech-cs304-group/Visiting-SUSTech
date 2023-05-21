package com.sustech.cs304.visitingsustech.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.sustech.cs304.visitingsustech.common.JsonResult;
import com.sustech.cs304.visitingsustech.entity.AppointmentEntity;
import com.sustech.cs304.visitingsustech.entity.UserInfoEntity;
import com.sustech.cs304.visitingsustech.exception.BaseException;
import com.sustech.cs304.visitingsustech.mapper.AppointmentMapper;
import com.sustech.cs304.visitingsustech.service.AppointmentService;
import com.sustech.cs304.visitingsustech.service.UserService;
import com.sustech.cs304.visitingsustech.util.JwtUtil;
import com.sustech.cs304.visitingsustech.vo.AppointmentVo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * For Application operations.
 *
 * @author pound
 */
@RestController
@RequestMapping("/appointment")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private AppointmentMapper appointmentMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;
    /**
     * Add an appointment.
     *
     * @param appointmentVo Info of appointment to add
     * @param request Http request
     * @return Message of success or fail
     */
    @PostMapping("/add")
    public JsonResult<String> addAppointment(@RequestBody AppointmentVo appointmentVo,
                                             HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        String openid = jwtUtil.getOpenidFromToken(token);
        AppointmentEntity appointmentEntity = new AppointmentEntity();
        BeanUtils.copyProperties(appointmentVo, appointmentEntity);
        appointmentEntity.setOpenid(openid);
        appointmentEntity.setStatus(0);
        try {
            if (appointmentService.addAppointment(appointmentEntity) > 0)
                return JsonResult.success("success");
            else
                return JsonResult.error("添加失败");
        } catch (BaseException e) {
            return JsonResult.error(e.getStatus(), e.getMessage());
        }
    }

    @PostMapping("/delete")
    public JsonResult<Void> deleteAppointment(@RequestParam("id") Integer id,
                                              HttpServletRequest request) {
        try {
            String token = request.getHeader("Authorization");
            String openid = jwtUtil.getOpenidFromToken(token);
            if (appointmentService.deleteAppointment(openid, id) > 0)
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
        try {
            if (appointmentService.updateAppointment(openid, appointmentEntity) > 0)
                return JsonResult.success();
            else
                return JsonResult.error("更新失败");
        } catch (BaseException e) {
            return JsonResult.error(e.getStatus(), e.getMessage());
        }
    }

    @GetMapping("/query")
    public JsonResult<List<AppointmentEntity>> getAppointment(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        String openid = jwtUtil.getOpenidFromToken(token);
        return JsonResult.success(appointmentService.getAppointment(openid));
    }

    @PostMapping("/justify")
    public JsonResult<Void> justifyAppointment(HttpServletRequest request, @RequestParam("id") Integer id, @RequestParam("approval") Boolean approval, @RequestParam("comment") String comment) {
        String token = request.getHeader("Authorization");
        String openid = jwtUtil.getOpenidFromToken(token);
        UserInfoEntity userInfoEntity = userService.queryUserInfo(openid);
        if (userInfoEntity == null || !userInfoEntity.getType().equals("admin"))
            return JsonResult.error(403, "权限不足");
        UpdateWrapper<AppointmentEntity> updateWrapper = new UpdateWrapper<AppointmentEntity>();
        if (approval) {
            updateWrapper.eq("id", id).set("status", 1).set("comment", comment);
            appointmentMapper.update(null, updateWrapper);
        } else {
            updateWrapper.eq("id", id).set("status", 2).set("comment", comment);
            appointmentMapper.update(null, updateWrapper);
        }
        return JsonResult.success();
    }
}
