package com.demo.tcpreceiver.data.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class WsensorData implements Serializable {
    public Integer id;
    public int wsensorId;
    public float temperature;
    @TableField(fill = FieldFill.INSERT)
    @DateTimeFormat
    public LocalDateTime time;


}
