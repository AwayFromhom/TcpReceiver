package com.demo.tcpreceiver.data.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Poles implements Serializable {
    public Integer id;
    public String name;
    public float lat;
    public float lon;
    public String address;
    public String comment;
}
