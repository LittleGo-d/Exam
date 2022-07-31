package com.wh.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author whstart
 * @creat 2022--01-18:58
 */
@Data
public class Page implements Serializable {
    private int page;
    private int limit;
    public Integer getStart(){
        return (page - 1 ) * limit;
    }
}

