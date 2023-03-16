package com.demo.tcpreceiver.data.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.tcpreceiver.data.entity.Wsensor;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WsensorMapper extends BaseMapper<Wsensor> {
    public void add();
}
