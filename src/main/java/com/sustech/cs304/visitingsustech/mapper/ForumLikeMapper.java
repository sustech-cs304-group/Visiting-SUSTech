package com.sustech.cs304.visitingsustech.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sustech.cs304.visitingsustech.entity.AppointmentEntity;
import com.sustech.cs304.visitingsustech.entity.CommentEntity;
import com.sustech.cs304.visitingsustech.entity.ForumLikeEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ForumLikeMapper extends BaseMapper<ForumLikeEntity> {

}
