package com.demo.tcpreceiver.socketClient;



import com.demo.tcpreceiver.core.util.ByteTransformUtil;
import com.demo.tcpreceiver.core.util.SocketUtil;
import com.demo.tcpreceiver.core.util.StreamUtil;
import com.demo.tcpreceiver.data.entity.SocketData;
import com.demo.tcpreceiver.data.service.SocketDataService;
import com.demo.tcpreceiver.data.service.impl.SocketDataServiceimpl;
import com.demo.tcpreceiver.vo.SocketMsgVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.time.LocalDateTime;

/**
 * 客户端发送，服务端消息接收线程
 */

public class ClientRecvThread implements Runnable {

    private final static Logger log = LoggerFactory.getLogger(ClientRecvThread.class);

    private Socket socket;

    private volatile boolean isStop = false;

    public ClientRecvThread(Socket socket) {
        this.socket = socket;
    }

    @Autowired()
    public SocketDataService socketDataService;

    @Override
    public void run() {
        //线程终止条件： 设置标志位为 true or socket 已关闭
        InputStream inputStream = null;
        DataInputStream dataInputStream = null;
        try {
            inputStream = socket.getInputStream();
            dataInputStream = new DataInputStream(inputStream);
            while (!isStop && !socket.isClosed()) {
                SocketMsgVo msgDataVo = SocketUtil.readMsgData(dataInputStream);

                //log.info("客户端收到消息:{}",msgDataVo.toString());


                //相对耗时，可以开线程来处理消息，否则影响后续消息接收处理速率
                //方便测试：接收到的数据转换成16进制字符串表示法，好对比服务端发送的原始数据
                byte[] revByteArr = ByteTransformUtil.byteMerger(msgDataVo.getFrameHeader(), ByteTransformUtil.toHH(msgDataVo.getLen()));
                revByteArr = ByteTransformUtil.byteMerger(revByteArr, msgDataVo.getBody());
                revByteArr = ByteTransformUtil.byteMerger(revByteArr, msgDataVo.getFrameTail());

                String hexStr = ByteTransformUtil.bytesToHex(revByteArr);

                log.info("客户端收到服务端原样16进制字符串:{}", hexStr.toUpperCase());

                //将socket信息写入日志中
                SocketData socketData = new SocketData();
                socketData.setSocket(String.valueOf(this.socket));
                socketData.setText(hexStr.toUpperCase());
                socketData.setTime(LocalDateTime.now());
                socketDataService.save(socketData);

            }
        } catch (IOException e) {
            log.error("客户端接收消息发生异常");
            e.printStackTrace();
        } finally {
            this.isStop = true;
            log.info("客户端旧接收线程已摧毁");
            StreamUtil.closeInputStream(dataInputStream);
            StreamUtil.closeInputStream(inputStream);
            SocketUtil.closeSocket(socket);
        }

    }

    public boolean getStop() {
        return isStop;
    }

    public void setStop(boolean stop) {
        isStop = stop;
    }
}
