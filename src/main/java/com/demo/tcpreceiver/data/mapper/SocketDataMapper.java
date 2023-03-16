package com.demo.tcpreceiver.data.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.tcpreceiver.data.entity.SocketData;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SocketDataMapper extends BaseMapper<SocketData> {
    public void addData();
}
