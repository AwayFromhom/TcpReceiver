package com.demo.tcpreceiver;

import com.demo.tcpreceiver.data.entity.SocketData;
import com.demo.tcpreceiver.data.service.SocketDataService;
import com.demo.tcpreceiver.data.service.impl.SocketDataServiceimpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class TcpReceiverApplicationTests {

    @Autowired
    public SocketDataService socketDataService;
    @Test
    void contextLoads() {

        //将socket信息写入日志中
        SocketData socketData = new SocketData();
        socketData.setSocket(String.valueOf("this.socket"));
        socketData.setText("hexStr");
        socketData.setTime(LocalDateTime.now());

        socketDataService.save(socketData);
    }

}
