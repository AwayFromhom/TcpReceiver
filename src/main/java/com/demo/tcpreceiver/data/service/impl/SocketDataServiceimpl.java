package com.demo.tcpreceiver.data.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.tcpreceiver.data.service.SocketDataService;
import com.demo.tcpreceiver.data.entity.SocketData;
import com.demo.tcpreceiver.data.mapper.SocketDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SocketDataServiceimpl extends ServiceImpl<SocketDataMapper, SocketData> implements SocketDataService {

    @Autowired
    SocketDataMapper socketDataMapper;

    /**
     * 查询没有被分析的数据
     * @return
     */
    @Override
    public ArrayList<SocketData> selectAllByIsAnalysis() {
        ArrayList<SocketData> list = socketDataMapper.selectAllByIsAnalysis();
        return list;
    }
}
