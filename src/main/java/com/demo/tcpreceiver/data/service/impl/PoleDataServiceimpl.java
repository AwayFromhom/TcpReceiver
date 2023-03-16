package com.demo.tcpreceiver.data.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.tcpreceiver.data.mapper.PoleDataMapper;
import com.demo.tcpreceiver.data.service.PoleDataService;
import org.springframework.stereotype.Service;
import com.demo.tcpreceiver.data.entity.PoleData;
@Service
public class PoleDataServiceimpl extends ServiceImpl<PoleDataMapper, PoleData> implements PoleDataService {
}
