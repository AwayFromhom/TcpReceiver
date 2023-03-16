package com.demo.tcpreceiver.data.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.tcpreceiver.data.entity.WsensorData;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WsensorDataMapper extends BaseMapper<WsensorData> {
    public void add();
}
