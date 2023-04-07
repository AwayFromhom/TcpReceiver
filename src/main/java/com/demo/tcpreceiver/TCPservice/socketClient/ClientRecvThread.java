package com.demo.tcpreceiver.TCPservice.socketClient;



import com.demo.tcpreceiver.TCPservice.constant.ReceiveDataFrameDemoConstant;
import com.demo.tcpreceiver.TCPservice.util.ByteTransformUtil;
import com.demo.tcpreceiver.TCPservice.util.SocketUtil;
import com.demo.tcpreceiver.TCPservice.util.StreamUtil;
import com.demo.tcpreceiver.data.entity.SocketData;
import com.demo.tcpreceiver.data.service.SocketDataService;
import com.demo.tcpreceiver.TCPservice.vo.SocketMsgDemo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
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

                SocketMsgDemo socketMsgDemo = SocketUtil.readMsgDataDemo(dataInputStream);
                log.info("客户端收到消息:{}",socketMsgDemo.toString());

//                SocketMsgVo msgDataVo = SocketUtil.readMsgData(dataInputStream);
//                //相对耗时，可以开线程来处理消息，否则影响后续消息接收处理速率
//                //方便测试：接收到的数据转换成16进制字符串表示法，好对比服务端发送的原始数据


                byte[] revByteArr = ByteTransformUtil.byteMerger(socketMsgDemo.getMessageHeader(), socketMsgDemo.getMessageLength());

                revByteArr = ByteTransformUtil.byteMerger(revByteArr, socketMsgDemo.getStatusMonitoringDeviceID());
                revByteArr = ByteTransformUtil.byteMerger(revByteArr, socketMsgDemo.getFrameType());
                revByteArr = ByteTransformUtil.byteMerger(revByteArr, socketMsgDemo.getMessagetype());
                revByteArr = ByteTransformUtil.byteMerger(revByteArr, socketMsgDemo.getFrameSequenceNumber());
                revByteArr = ByteTransformUtil.byteMerger(revByteArr, socketMsgDemo.getMessageContent());
                revByteArr = ByteTransformUtil.byteMerger(revByteArr, socketMsgDemo.getCheckSumbit());
                revByteArr = ByteTransformUtil.byteMerger(revByteArr, socketMsgDemo.getMessageTail());

                String hexStr = ByteTransformUtil.bytesToHex(revByteArr);

                log.info("客户端收到服务端原样16进制字符串:{}", hexStr.toUpperCase());

                //当收到数据非空时存入数据库
                if (socketMsgDemo.getMessageContent()!=null && socketMsgDemo.getMessageContent().length!=0){
                    //将socket信息写入日志中
                    SocketData socketData = new SocketData();
                    socketData.setSocket(String.valueOf(this.socket));
                    socketData.setText(hexStr.toUpperCase());
                    socketData.setTime(LocalDateTime.now());
                    socketData.setIsAnalysis(0);
                    socketDataService.save(socketData);
                    log.info("存入数据库:{}", socketMsgDemo.getMessageContent());
                    //响应数据报文
                    OutputStream outputStream =socket.getOutputStream();
                    DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
                    SocketMsgDemo msgDataDemo = new SocketMsgDemo();
                    msgDataDemo.setMessageHeader(ReceiveDataFrameDemoConstant.RECEIVE_FRAME_HEADER);
                    msgDataDemo.setMessageLength(ReceiveDataFrameDemoConstant.RECEIVE_ONEDATA_BYTE);
                    msgDataDemo.setStatusMonitoringDeviceID(ReceiveDataFrameDemoConstant.RECEIVE_SEVENTEEN_BYTE);
                    msgDataDemo.setFrameType(ReceiveDataFrameDemoConstant.RECEIVE_ONE_BYTE);
                    msgDataDemo.setMessagetype(ReceiveDataFrameDemoConstant.RECEIVE_BEAT_BYTE);
                    msgDataDemo.setFrameSequenceNumber(ReceiveDataFrameDemoConstant.RECEIVE_ONE_BYTE);
                    msgDataDemo.setMessageContent(ReceiveDataFrameDemoConstant.RECEIVE_SUCCESS_BYTE);
                    msgDataDemo.setCheckSumbit(ReceiveDataFrameDemoConstant.RECEIVE_DOUBLE_BYTE);
                    msgDataDemo.setMessageTail(ReceiveDataFrameDemoConstant.RECEIVE_FRAME_TAIL);

                    if (msgDataDemo != null) {
                        SocketUtil.writeMsgDataDemo(dataOutputStream, msgDataDemo);
                    }






                }



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

