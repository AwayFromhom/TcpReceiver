package com.demo.tcpreceiver.data.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.tcpreceiver.data.mapper.PolesMapper;
import com.demo.tcpreceiver.data.entity.Poles;

import com.demo.tcpreceiver.data.service.PolesService;
import org.springframework.stereotype.Service;

@Service
public class PolesServiceimpl extends ServiceImpl<PolesMapper, Poles> implements PolesService {
}
