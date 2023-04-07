package com.demo.tcpreceiver.data.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.tcpreceiver.data.entity.WireTemperatureData;
import com.demo.tcpreceiver.data.mapper.WireTemperatureDataMapper;
import com.demo.tcpreceiver.data.service.WireTemperatureDataService;
import org.springframework.stereotype.Service;

@Service
public class WireTemperatureDataServiceimpl extends ServiceImpl<WireTemperatureDataMapper, WireTemperatureData> implements WireTemperatureDataService {
}
