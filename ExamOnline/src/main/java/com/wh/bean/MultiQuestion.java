package com.wh.bean;

import lombok.Data;

/**
 * 选择题
 * @author whstart
 * @creat 2022--01-18:47
 */
@Data
public class MultiQuestion {
    private Integer questionId;
    private String subject;
    private String content;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private Integer score;
    private String section;
    private String answer;
    private Integer difficulty;
}
