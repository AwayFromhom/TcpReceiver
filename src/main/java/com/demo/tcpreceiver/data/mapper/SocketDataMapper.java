package com.demo.tcpreceiver.data.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.tcpreceiver.data.entity.SocketData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface SocketDataMapper extends BaseMapper<SocketData> {

   @Select("SELECT  * FROM socket_data WHERE is_analysis = 0")
   public ArrayList<SocketData> selectAllByIsAnalysis();

}
