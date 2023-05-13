package com.sustech.cs304.visitingsustech.vo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Data
public class ForumVo {
    private String content;
    private String location;
    private List<Map<String,String>> imgOrRadio;
}
