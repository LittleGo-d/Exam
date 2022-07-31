package com.wh.bean;

import lombok.Data;

/**
 * @author whstart
 * @creat 2022--16-9:59
 */
@Data
public class Score {
    private Integer scoreId;
    private Integer examId;
    private Integer studentId;
    private String subject;
    private Integer score;
    private String answerDate;
}
