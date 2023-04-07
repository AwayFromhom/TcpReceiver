package com.demo.tcpreceiver.data.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.tcpreceiver.data.entity.WireTemperatureData;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WireTemperatureDataMapper extends BaseMapper<WireTemperatureData> {
}
