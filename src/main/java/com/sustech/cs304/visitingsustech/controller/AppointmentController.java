package com.sustech.cs304.visitingsustech.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.sustech.cs304.visitingsustech.common.JsonResult;
import com.sustech.cs304.visitingsustech.entity.AppointmentEntity;
import com.sustech.cs304.visitingsustech.exception.BaseException;
import com.sustech.cs304.visitingsustech.mapper.AppointmentMapper;
import com.sustech.cs304.visitingsustech.service.AppointmentService;
import com.sustech.cs304.visitingsustech.util.JwtUtil;
import com.sustech.cs304.visitingsustech.vo.AppointmentVo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private AppointmentMapper appointmentMapper;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/add")
    public JsonResult<String> addAppointment(@RequestBody AppointmentVo appointmentVo,
                                             HttpServletRequest request) {
        System.out.println(appointmentVo);
        String token = request.getHeader("Authorization");
        String openid = jwtUtil.getOpenidFromToken(token);
        AppointmentEntity appointmentEntity = new AppointmentEntity();
        BeanUtils.copyProperties(appointmentVo, appointmentEntity);
//        QueryWrapper<AppointmentEntity> appointmentQueryWrapper = new QueryWrapper<AppointmentEntity>()
//                .isNotNull("openid");
//        List<Map<String, Object>> res = appointmentMapper.selectMaps(appointmentQueryWrapper);
//        if (res.size() == 0){
//            appointmentEntity.setId(1);
//        }
//        else {
//            appointmentEntity.setId(Integer.parseInt(String.valueOf(res.get(res.size() - 1).get("id"))) + 1);
//        }
        appointmentEntity.setOpenid(openid);
        appointmentEntity.setStatus("未审批");
        try {
            if (appointmentService.addAppointment(appointmentEntity) > 0) {
                return JsonResult.success("success");
            }
            else {
                return JsonResult.error("添加失败");
            }
        } catch (BaseException e) {
            return JsonResult.error(e.getStatus(), e.getMessage());
        }
    }

    @PostMapping("/delete")
    public JsonResult<Void> deleteAppointment(@RequestBody Integer id,
                                              HttpServletRequest request) {
        try {
            if (appointmentService.deleteAppointment(id) > 0)
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
//        String token = request.getHeader("Authorization");
//        String openid = jwtUtil.getOpenidFromToken(token);
        AppointmentEntity appointmentEntity = new AppointmentEntity();
        BeanUtils.copyProperties(appointmentVo, appointmentEntity);
        QueryWrapper<AppointmentEntity> appointmentQueryWrapper = new QueryWrapper<AppointmentEntity>()
                .eq("id", appointmentEntity.getId());
        List<Map<String, Object>> res = appointmentMapper.selectMaps(appointmentQueryWrapper);
        appointmentEntity.setOpenid((String) res.get(0).get("openid"));
//        appointmentEntity.setOpenid(openid);
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

    @GetMapping("/query")
    public JsonResult<List<AppointmentEntity>> getAppointment(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        String openid = jwtUtil.getOpenidFromToken(token);
        System.out.println(appointmentService.getAppointment(openid));
        return JsonResult.success(appointmentService.getAppointment(openid));
    }

    @PostMapping("/justify")
    public JsonResult<Void> justifyAppointment(HttpServletRequest request) {
        String isApproval = request.getParameter("isApproval");
        int id = Integer.parseInt(request.getParameter("id"));
        if (Objects.equals(isApproval, "true")){
            UpdateWrapper<AppointmentEntity> updateWrapper = new UpdateWrapper<AppointmentEntity>();
            updateWrapper.set("status", "审批通过").eq("id", id);
            appointmentMapper.update(null, updateWrapper);
        }
        else {
            try {
                if (appointmentService.deleteAppointment(id) > 0)
                    return JsonResult.success();
                else
                    return JsonResult.error("删除失败");
            } catch (BaseException e) {
                return JsonResult.error(e.getStatus(), e.getMessage());
            }
        }
        return JsonResult.success();
    }
}
