package com.sustech.cs304.visitingsustech.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sustech.cs304.visitingsustech.entity.AppointmentEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * Appointment mapper for database operation.
 *
 * @author pound
 */
@Mapper
public interface AppointmentMapper extends BaseMapper<AppointmentEntity> {

}
