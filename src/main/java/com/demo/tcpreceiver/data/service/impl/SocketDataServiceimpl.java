package com.demo.tcpreceiver.data.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.tcpreceiver.data.service.SocketDataService;
import com.demo.tcpreceiver.data.entity.SocketData;
import com.demo.tcpreceiver.data.mapper.SocketDataMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class SocketDataServiceimpl extends ServiceImpl<SocketDataMapper, SocketData> implements SocketDataService {
}
