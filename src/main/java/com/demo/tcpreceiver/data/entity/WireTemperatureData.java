package com.demo.tcpreceiver.data.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Herofancye
 */
@Data
public class WireTemperatureData implements Serializable {
    public Integer id;
    public String componentId;
    public Integer unitSum;
    public Integer unitNo;

    @TableField(fill = FieldFill.INSERT)
    @DateTimeFormat
    public LocalDateTime timeStamp;
    public String lineTemperature;

}
