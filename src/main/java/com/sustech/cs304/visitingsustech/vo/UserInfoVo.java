package com.sustech.cs304.visitingsustech.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserInfoVo {
    @NotBlank
    private String nickname;
    @NotBlank
    private String name;
    @NotBlank
    private String phone;
    @NotBlank
    private String identityCard;
    @NotBlank
    private Integer gender;
}
