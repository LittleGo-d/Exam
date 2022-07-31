package com.wh.bean;

import lombok.Data;

/**
 * @author whstart
 * @creat 2022--11-16:59
 */
@Data
public class Exam {
    private Integer examId;
    private String description;
    private String course;
    private Integer paperId;
    private String  examDate;
    private Integer totalTime;
    private String grade;
    private String term;
    private String major;
    private Integer totalScore;
}
