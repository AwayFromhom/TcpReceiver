package com.demo.tcpreceiver.TCPservice.util;


import com.demo.tcpreceiver.TCPservice.constant.ReceiveDataFrameDemoConstant;
import com.demo.tcpreceiver.TCPservice.vo.SocketMsgDemo;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class SocketUtil {

    public static Socket createClientSocket(String host, int port) throws IOException {
        Socket socket = new Socket(host,port);
        return socket;
    }

    public static void closeSocket(Socket socket) {
        if (socket != null && !socket.isClosed()) {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    /**
     * 将封装好的SocketMsgDemo返回到DataOutputStream,当做心跳包返回。
     * @param dataOutputStream
     * @param msgDataDemo
     * @throws IOException
     */
    public static void writeMsgDataDemo(DataOutputStream dataOutputStream, SocketMsgDemo msgDataDemo) throws IOException {

        dataOutputStream.write(msgDataDemo.getMessageHeader());

        dataOutputStream.write(msgDataDemo.getMessageLength());

        dataOutputStream.write(msgDataDemo.getStatusMonitoringDeviceID());

        dataOutputStream.write(msgDataDemo.getFrameType());

        dataOutputStream.write(msgDataDemo.getMessagetype());

        dataOutputStream.write(msgDataDemo.getFrameSequenceNumber());

        if (msgDataDemo.getMessageContent() != null) {
            dataOutputStream.write(msgDataDemo.getMessageContent());
        }
        dataOutputStream.write(msgDataDemo.getCheckSumbit());

        dataOutputStream.write(msgDataDemo.getMessageTail());

        dataOutputStream.flush();
    }


    /**
     * 接收消息存入SocketMsgDemo
     * @param dataInputStream
     * @return
     * @throws IOException
     */
    public static SocketMsgDemo readMsgDataDemo(DataInputStream dataInputStream) throws IOException {
        byte[] messageHeader = new byte[ReceiveDataFrameDemoConstant.RECEIVE_Message_header_LENGTH];
        dataInputStream.read(messageHeader);
        byte[] messageLength = new byte[ReceiveDataFrameDemoConstant.RECEIVE_messageLength_LENGTH];
        dataInputStream.read(messageLength);
        int len = (messageLength[0] & 0xFF)
                | ((messageLength[1] & 0xFF)<<8);

        byte[] statusMonitoringDeviceID = new byte[ReceiveDataFrameDemoConstant.RECEIVE_statusMonitoringDeviceID_LENGTH];
        dataInputStream.read(statusMonitoringDeviceID);

        byte[] frameType = new byte[ReceiveDataFrameDemoConstant.RECEIVE_PUBLIC_LENGTH];
        dataInputStream.read(frameType);

        byte[] messagetype = new byte[ReceiveDataFrameDemoConstant.RECEIVE_PUBLIC_LENGTH];
        dataInputStream.read(messagetype);

        byte[] frameSequenceNumber = new byte[ReceiveDataFrameDemoConstant.RECEIVE_PUBLIC_LENGTH];
        dataInputStream.read(frameSequenceNumber);

        byte[] messageContent = new byte[len];
        dataInputStream.read(messageContent);

        byte[] checkSumbit = new byte[ReceiveDataFrameDemoConstant.RECEIVE_checkSumbit_LENGTH];
        dataInputStream.read(checkSumbit);
        byte[] messageTail = new byte[ReceiveDataFrameDemoConstant.RECEIVE_PUBLIC_LENGTH];

        dataInputStream.read(messageTail);
        System.out.println("获取的数据长度为：" + len);
        SocketMsgDemo msgDataDemo = new SocketMsgDemo();
        msgDataDemo.setMessageHeader(messageHeader);
        msgDataDemo.setMessageLength(messageLength);
        msgDataDemo.setStatusMonitoringDeviceID(statusMonitoringDeviceID);
        msgDataDemo.setFrameType(frameType);
        msgDataDemo.setMessagetype(messagetype);
        msgDataDemo.setFrameSequenceNumber(frameSequenceNumber);
        msgDataDemo.setMessageContent(messageContent);
        msgDataDemo.setCheckSumbit(checkSumbit);
        msgDataDemo.setMessageTail(messageTail);
        return msgDataDemo;
    }


}
