package com.sustech.cs304.visitingsustech.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sustech.cs304.visitingsustech.entity.AppointmentEntity;
import com.sustech.cs304.visitingsustech.entity.CommentEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * Comment mapper for database operation.
 *
 * @author pound
 */
@Mapper
public interface CommentMapper extends BaseMapper<CommentEntity> {

}
