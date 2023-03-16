package com.demo.tcpreceiver.data.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.tcpreceiver.data.entity.Poles;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public  interface PolesMapper extends BaseMapper<Poles> {
    public void add();
}
