package com.demo.tcpreceiver.data.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.demo.tcpreceiver.data.entity.PoleData;
@Mapper
public interface PoleDataMapper extends BaseMapper<PoleData> {
    public void add();
}
