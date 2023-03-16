package com.demo.tcpreceiver.data.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
@Data
public class PoleData implements Serializable {
    public Integer    id;
    public int poleId;
    public float  x;
    public float  y;
    public float  z;
    public float windDirection;
    public float windSpeed;
    public float humility;
    public float airPressure;
    public float rainFall;
    public float solarRadiation;

    @TableField(fill = FieldFill.INSERT)
    @DateTimeFormat
    private LocalDateTime time;

}
