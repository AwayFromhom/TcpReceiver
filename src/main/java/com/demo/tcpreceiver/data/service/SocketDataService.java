package com.demo.tcpreceiver.data.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.tcpreceiver.data.entity.SocketData;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;


public interface SocketDataService extends IService<SocketData> {
    public ArrayList<SocketData> selectAllByIsAnalysis();
}
