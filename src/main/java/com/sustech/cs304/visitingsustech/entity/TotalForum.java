package com.sustech.cs304.visitingsustech.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Used for forum return.
 *
 * @author pound
 */
@Data
public class TotalForum {
    private Integer id;
    private String nickname;
    private String avatarUrl;
    private String content;
    private String location;
    private Timestamp createDate;
    private List<String> imgOrRadio;
    private List<String> likes;
    private List<CommentEntity> comments;
}
