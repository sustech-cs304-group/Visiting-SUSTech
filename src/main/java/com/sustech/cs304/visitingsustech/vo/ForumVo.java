package com.sustech.cs304.visitingsustech.vo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Data
public class ForumVo {
    private Integer id;
    private String content;
    private String location;
    private Timestamp createDate;
    private List<MultipartFile> imgOrRadio;
}
