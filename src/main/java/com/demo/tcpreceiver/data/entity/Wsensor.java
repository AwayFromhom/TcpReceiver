package com.demo.tcpreceiver.data.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Wsensor implements Serializable {
    public Integer   id;
    public int poleId;
    public String  comment;
}
