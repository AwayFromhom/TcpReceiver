package com.demo.tcpreceiver.data.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class SocketData implements Serializable {
    public Integer id;
    public String text;
    public String socket;

    @TableField(fill = FieldFill.INSERT)
    @DateTimeFormat
    public LocalDateTime time;

    public Integer isAnalysis;

}
