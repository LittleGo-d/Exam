package com.wh.bean;

import lombok.Data;

/**
 * 判断题
 * @author whstart
 * @creat 2022--09-16:29
 */
@Data
public class FillQuestion {
    private Integer questionId;
    private String subject;
    private String content;
    private String answer;
    private Integer score;
    private Integer difficulty;
    private String section;
}
