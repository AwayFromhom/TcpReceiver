package com.demo.tcpreceiver.TCPservice.socketClient;


import com.demo.tcpreceiver.TCPservice.constant.ReceiveDataFrameDemoConstant;
import com.demo.tcpreceiver.TCPservice.util.SocketUtil;
import com.demo.tcpreceiver.TCPservice.util.StreamUtil;
import com.demo.tcpreceiver.TCPservice.vo.SocketMsgDemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 客户端心跳检测、保持长连接状态
 */
public class ClientHeartBeatThread implements Runnable {

    private final static Logger log = LoggerFactory.getLogger(ClientHeartBeatThread.class);

    private Socket socket;
    private Object lockObject = new Object(); //锁对象，用于线程通讯，唤醒重试线程

    //3间隔多长时间发送一次心跳检测
    private int socketHeartIntervalTime;

    private volatile boolean isStop = false;

    public ClientHeartBeatThread(Socket socket, int socketHeartIntervalTime, Object lockObject) {
        this.socket = socket;
        this.socketHeartIntervalTime = socketHeartIntervalTime;
        this.lockObject = lockObject;
    }

    @Override
    public void run() {
        OutputStream outputStream = null;
        DataOutputStream dataOutputStream = null;
        try {
            outputStream = socket.getOutputStream();
            dataOutputStream = new DataOutputStream(outputStream);
            //客户端心跳检测
            while (!this.isStop && !socket.isClosed()) {
                SocketMsgDemo msgDataDemo = new SocketMsgDemo();
                msgDataDemo.setMessageHeader(ReceiveDataFrameDemoConstant.RECEIVE_FRAME_HEADER);

                msgDataDemo.setMessageLength(ReceiveDataFrameDemoConstant.RECEIVE_DOUBLE_BYTE);
                msgDataDemo.setStatusMonitoringDeviceID(ReceiveDataFrameDemoConstant.RECEIVE_SEVENTEEN_BYTE);
                msgDataDemo.setFrameType(ReceiveDataFrameDemoConstant.RECEIVE_ONE_BYTE);
                msgDataDemo.setMessagetype(ReceiveDataFrameDemoConstant.RECEIVE_BEAT_BYTE);
                msgDataDemo.setFrameSequenceNumber(ReceiveDataFrameDemoConstant.RECEIVE_ONE_BYTE);
                msgDataDemo.setMessageContent(null);
                msgDataDemo.setCheckSumbit(ReceiveDataFrameDemoConstant.RECEIVE_DOUBLE_BYTE);
                msgDataDemo.setMessageTail(ReceiveDataFrameDemoConstant.RECEIVE_FRAME_TAIL);

                if (msgDataDemo != null) {
                    SocketUtil.writeMsgDataDemo(dataOutputStream, msgDataDemo);
                }
                try {
                    Thread.sleep(socketHeartIntervalTime * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            log.error("客户端心跳消息发送异常");
            e.printStackTrace();
        } finally {
            this.isStop = true;
            log.info("客户端旧心跳线程已摧毁");
            StreamUtil.closeOutputStream(dataOutputStream);
            StreamUtil.closeOutputStream(outputStream);
            SocketUtil.closeSocket(socket);
            //最后唤醒线程、重建连接
            synchronized (lockObject) {
                lockObject.notify();
            }
        }
    }

    public boolean isStop() {
        return isStop;
    }

    public void setStop(boolean stop) {
        isStop = stop;
    }
}
