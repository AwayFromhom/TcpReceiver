package com.demo.tcpreceiver.TCPservice.vo;

import lombok.Data;

/**
 * 定义帧结构
 */
@Data
public class SocketMsgDemo {
    /**
     * 报文头：2 Byte
     * 标识状态监测数据报，以 16 进制值 5AA5（10 进制值 23205）表示。
     */
    private byte[] messageHeader;
    /**
     * 报文长度：2 Byte
     * 帧结构中“报文内容”（变长）数据的长度，单位：字节（Byte），报文长度应小于等于 1417
     */
    private byte[] messageLength ;
    /**
     * 状态监测装置 ID：17 Byte
     * 状态监测装置唯一标识，遵循国家电网公司“SG186 工程”
     * 生产管理系统设备 17 位编码规范。当设备出厂时，状态监测装置 ID 为 7 位厂
     * 家编码+10 位厂家生产序列号的 17 位原始 ID；当设备上线运行后需要通过接
     * 收上级设备的命令，将状态监测装置 ID 由原始 ID 更改为通信 ID。由多个或
     * 者多种监测类型组成的复合装置，需要配置多个不重复的 ID。
     */
    private byte[] statusMonitoringDeviceID;
    /**
     * 帧类型：1 Byte
     * 按功能对数据帧进行区分、标识。具体定义参考附表 C8-1。
     */
    private byte[] frameType;
    /**
     * 报文类型： 1 Byte
     * 按不同监测类型对数据帧进行区分、标识。具体定义参考附表 C8-2。
     */
    private byte[] messagetype;
    /**
     * 帧序列号：1 Byte
     * 监测装置或者上级设备主动发送的报文的顺序流水号，以无符号整数表示，在确认或者响应报文中应返回该帧序列号。
     */
    private byte[] frameSequenceNumber;
    /**
     * 报文内容：变长
     * 数据的字节长度不固定，具体定义参考 C.3、C.4、C.5、C.6 章。
     */
    private byte[] messageContent;
    /*
     *校验位：2 Byte
     *数据通信领域中最常用的一种差错校验码，其特征是信息字段和校验
     *字段的长度可以任意选定。本协议中，校验位通过 C.7 中所列 CRC16 校验算
     *法换算得出，校验的内容包括报文中除报文头、校验位、报文尾外所有报文数
     *据（包括报文长度＋状态监测装置 ID＋帧类型＋报文类型＋帧序列号＋报文
     *内容）。
     */
    private byte[] checkSumbit;
    /**
     * 报文尾：1 Byte
     * 标识电压监测数据报结束，以 16 进制整型值 96（10 进制值 150）表示。
     */
    private byte[] messageTail;
}
