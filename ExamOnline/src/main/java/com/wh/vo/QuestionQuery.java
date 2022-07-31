package com.wh.vo;

import lombok.Data;

/**
 * @author whstart
 * @creat 2022--01-19:01
 */
@Data
public class QuestionQuery extends Page {
    private String subject;
    private String content;
}
