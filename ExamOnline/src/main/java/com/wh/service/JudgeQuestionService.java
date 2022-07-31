package com.wh.service;

import com.wh.bean.JudgeQuestion;
import com.wh.bean.MultiQuestion;
import com.wh.utils.SqlSessionFactoryUtils;
import com.wh.vo.QuestionQuery;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * @author whstart
 * @creat 2022--09-16:34
 */
public interface JudgeQuestionService {
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    List<JudgeQuestion> getQuestionList(QuestionQuery questionQuery);

    Integer countQuestion(QuestionQuery questionQuery);

    void deleteById(String questionId);

    void deleteByIds(int[] questionIds);

    JudgeQuestion selectById(int parseInt);

    void updateQuestion(JudgeQuestion question);

    void addQuestion(JudgeQuestion question);

    Integer getMinId();

    Integer getMaxId();

    int[] getQuesIdByCourse(String course);
}
