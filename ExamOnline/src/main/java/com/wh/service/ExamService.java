package com.wh.service;

import com.wh.bean.Exam;
import com.wh.utils.SqlSessionFactoryUtils;
import com.wh.vo.ExamQuery;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * @author whstart
 * @creat 2022--11-17:23
 */
public interface ExamService {
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    List<Exam> getExamList(ExamQuery examQuery);

    Integer countExam(ExamQuery examQuery);

    List<Exam> AllExam();

    Exam selectById(int parseInt);

    void updateExam(Exam exam);

    void addExam(Exam exam);
}
