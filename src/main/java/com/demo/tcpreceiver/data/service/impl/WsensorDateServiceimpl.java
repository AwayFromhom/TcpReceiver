package com.demo.tcpreceiver.data.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.tcpreceiver.data.entity.WsensorData;
import com.demo.tcpreceiver.data.mapper.WsensorDataMapper;
import com.demo.tcpreceiver.data.service.WsensorDateService;
import org.springframework.stereotype.Service;

@Service
public class WsensorDateServiceimpl extends ServiceImpl<WsensorDataMapper, WsensorData> implements WsensorDateService {
}
