package com.sustech.cs304.visitingsustech.interceptor;

import com.sustech.cs304.visitingsustech.exception.TokenException;
import com.sustech.cs304.visitingsustech.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * Set up jwt interceptor.
 *
 * @author sui_h
 */
@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("Authorization");
        try {
            jwtUtil.verifyToken(token);
            return true;
        } catch (ExpiredJwtException e) {
            e.printStackTrace();
            throw new TokenException("token过期！");
        } catch (MalformedJwtException e) {
            e.printStackTrace();
            throw new TokenException("token格式错误！");
        } catch (SignatureException e) {
            e.printStackTrace();
            throw new TokenException("无效签名！");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            throw new TokenException("非法请求！");
        } catch (Exception e) {
            e.printStackTrace();
            throw new TokenException("token无效！");
        }
    }
}