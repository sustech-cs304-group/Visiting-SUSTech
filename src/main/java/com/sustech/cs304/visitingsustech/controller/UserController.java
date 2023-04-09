package com.sustech.cs304.visitingsustech.controller;

import com.sustech.cs304.visitingsustech.common.JsonResult;
import com.sustech.cs304.visitingsustech.entity.UserInfoEntity;
import com.sustech.cs304.visitingsustech.exception.BaseException;
import com.sustech.cs304.visitingsustech.service.UserService;
import com.sustech.cs304.visitingsustech.util.JwtUtil;
import com.sustech.cs304.visitingsustech.vo.UserInfoVo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/user/person-info")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/update")
    public JsonResult<Void> updateUserInfo(HttpServletRequest request, @RequestBody UserInfoVo userInfoVo) {
        String token = request.getHeader("Authorization");
        String openid = jwtUtil.getOpenidFromToken(token);
        try {
            if (userService.updateUserInfo(openid, userInfoVo) > 0)
                return JsonResult.success("修改个人信息成功");
            else
                return JsonResult.error("修改个人信息失败");
        } catch (BaseException e) {
            return JsonResult.error(e.getStatus(), e.getMessage());
        }
    }

    @PostMapping("/update-avatar")
    public JsonResult<Void> updateAvatar(HttpServletRequest request, @RequestParam("avatar") MultipartFile avatar) {
        String token = request.getHeader("Authorization");
        String openid = jwtUtil.getOpenidFromToken(token);
        try {
            if (userService.updateAvatar(openid, avatar, request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/images/") > 0)
                return JsonResult.success("修改头像成功");
            else
                return JsonResult.error("修改头像失败");
        } catch (BaseException e) {
            return JsonResult.error(e.getStatus(), e.getMessage());
        }
    }

    @GetMapping("/query")
    public JsonResult<UserInfoEntity> getUserInfo(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        String openid = jwtUtil.getOpenidFromToken(token);
        UserInfoEntity userInfoEntity = userService.queryUserInfo(openid);
        if (userInfoEntity != null)
            return JsonResult.success(userInfoEntity);
        else
            return JsonResult.error(400, "查询个人信息失败");
    }
}
