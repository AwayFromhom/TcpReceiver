package com.demo.tcpreceiver.TCPservice.constant;

/**
 * 接收帧常量
 * 数据包格式： 帧头（4Byte）| 长度（4Byte）| 数据（xxByte）| 帧尾（4Byte）
 */
public class ReceiveDataFrameDemoConstant {

    public static int RECEIVE_Message_header_LENGTH = 2;  //帧头字节长度
    public static int RECEIVE_messageLength_LENGTH = 2;  //报文长度：2 Byte
    public static int RECEIVE_statusMonitoringDeviceID_LENGTH = 17;  //状态监测装置 ID 长度
    public static int RECEIVE_checkSumbit_LENGTH = 2;  //校验位：2 Byte

    public static int RECEIVE_PUBLIC_LENGTH = 1;  //1 Byte

//    public static int RECEIVE_FRAME_LENGTH_LENGTH = 4;  //帧长度长度
    public static byte[] RECEIVE_FRAME_HEADER = {(byte)0x5A, (byte)0xA5};  //固定帧头

    public static byte[] RECEIVE_DOUBLE_BYTE = {(byte)0x00, (byte) 0x00};

    public static byte[] RECEIVE_ONEDATA_BYTE = {(byte)0x01, (byte) 0x00};
    public static byte[] RECEIVE_ONE_BYTE = {(byte)0x00};
    public static byte[] RECEIVE_SUCCESS_BYTE = {(byte)0xFF};
    public static byte[] RECEIVE_BEAT_BYTE = {(byte)0xC1};
    public static byte[] RECEIVE_SEVENTEEN_BYTE = {(byte)0x00, (byte) 0x00,(byte)0x00, (byte) 0x00, (byte)0x00, (byte) 0x00,(byte)0x00, (byte) 0x00,(byte)0x00, (byte) 0x00,(byte)0x00, (byte) 0x00,(byte)0x00, (byte) 0x00,(byte)0x00, (byte) 0x00,(byte) 0x00};
    public static byte[] RECEIVE_FRAME_TAIL = {(byte)0x96}; //固定帧尾

}
