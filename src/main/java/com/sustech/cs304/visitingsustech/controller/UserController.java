package com.sustech.cs304.visitingsustech.controller;

import com.sustech.cs304.visitingsustech.common.JsonResult;
import com.sustech.cs304.visitingsustech.service.UserService;
import com.sustech.cs304.visitingsustech.util.JwtUtil;
import com.sustech.cs304.visitingsustech.vo.UserInfoVo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/person-info")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    public JsonResult<Void> updateUserInfo(HttpServletRequest request, @RequestBody UserInfoVo userInfoVo) {
        String token = request.getHeader("Authorization");
        String openid = jwtUtil.getOpenidFromToken(token);
        if (userService.updateUserInfo(openid, userInfoVo) > 0)
            return JsonResult.success("修改个人信息成功");
        else
            return JsonResult.error("修改个人信息失败");
    }
}
