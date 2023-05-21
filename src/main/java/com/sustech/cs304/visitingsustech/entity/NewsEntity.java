package com.sustech.cs304.visitingsustech.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

/**
 * Stores news info.
 *
 * @author pound
 */
@Data
@TableName("news")
public class NewsEntity {
    private Integer id;
    private String title;
    private String content;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String pictureUrl;
}
