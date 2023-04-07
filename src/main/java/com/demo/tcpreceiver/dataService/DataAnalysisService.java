package com.demo.tcpreceiver.dataService;


import com.demo.tcpreceiver.TCPservice.util.ByteTransformUtil;
import com.demo.tcpreceiver.data.entity.SocketData;
import com.demo.tcpreceiver.data.entity.WireTemperatureData;
import com.demo.tcpreceiver.data.service.SocketDataService;
import com.demo.tcpreceiver.data.service.WireTemperatureDataService;
import com.demo.tcpreceiver.dataService.util.ByteAndStringUitls;
import com.demo.tcpreceiver.dataService.util.TimeUitls;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Component
public class DataAnalysisService implements InitializingBean {
    @Value("${dataAnalysis.time}")
    private int socketHeartIntervalTime;
    @Autowired
    WireTemperatureDataService wireTemperatureDataService;

    @Autowired
    SocketDataService socketDataService;
    /**
     * 在该类的依赖注入完毕之后，会自动调用afterPropertiesSet方法,否则外部tomcat部署会无法正常启动socket
     * jar包的启动时直接由项目的主函数开始启动，此时会先初始化IOC容器，然后才会进行内置Servlet环境（一般为Tomcat）的启动。
     * war包通常使用Tomcat进行部署启动，在tomcat启动war应用时，会先进行Servlet环境的初始化，之后才会进行到IOC容器的初始化，也就是说，在servlet初始化过程中是不能使用IOC依赖注入的
    */
    @Override
    public void afterPropertiesSet() throws Exception {
        start();
        System.out.println("data analysis main thread线程已经启动！！！！！！！！！！！！！！！");
    }

    private void start() throws InterruptedException {
        Thread dataAnalysisServiceThread = new Thread(() -> {
            while (true){
                //检查日志是否新增
                List<SocketData> records = socketDataService.selectAllByIsAnalysis();
                System.out.println("正在检查日志是否新增。。。。。。。。。。。。。。");

                if (records != null && records.size() != 0) {
                    //对新增的日志记录进行分析，并存入数据库
                    for (SocketData record : records) {

                        String input = record.getText();
                        String[] data = new String[input.length() / 2];

                        for (int i = 0; i < input.length(); i += 2) {
                            data[i / 2] = input.substring(i, i + 2);
                        }

                        List<String> dataList = new ArrayList<>();
                        int index = 0;

                        int[] lengths = new int[]{2, 2, 17, 1, 1, 1, 17, 1, 1, 4, 4, 2, 1}; // 定义数据长度

                        for (int length : lengths) { // 按照长度逐个分隔数据
                            String dataStr = String.join("", Arrays.copyOfRange(data, index, index + length));
                            dataList.add(dataStr);
                            index += length;
                        }
                        WireTemperatureData wtData = new WireTemperatureData();
                        wtData.setComponentId(String.valueOf(ByteTransformUtil.toInt(ByteAndStringUitls.getBytes(dataList.get(6)))));
                        wtData.setUnitSum(Integer.parseInt(dataList.get(7), 16));
                        wtData.setUnitNo(Integer.parseInt(dataList.get(8), 16));
                        wtData.setTimeStamp(TimeUitls.getDatetimeStr(ByteAndStringUitls.getBytes(dataList.get(9))));
                        try {
                            wtData.setLineTemperature(ByteAndStringUitls.getFloatByString(ByteAndStringUitls.getBytes(dataList.get(10))));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        wireTemperatureDataService.save(wtData);
                        //将isanaylis重新设置成1
                        record.setIsAnalysis(1);
                        socketDataService.updateById(record);
                    }
                }
                else {
                    System.out.println("已经是最新。。。。。。。。。。。。。。。。");
                }
                try {
                    Thread.sleep(socketHeartIntervalTime*1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        dataAnalysisServiceThread.setName("data analysis main thread");
        dataAnalysisServiceThread.start();

    }
}
