package com.demo.tcpreceiver.data.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.tcpreceiver.data.entity.Wsensor;
import com.demo.tcpreceiver.data.mapper.WsensorMapper;
import com.demo.tcpreceiver.data.service.WsensorService;
import org.springframework.stereotype.Service;

@Service
public class WsensorServiceimpl extends ServiceImpl<WsensorMapper, Wsensor> implements WsensorService {
}
