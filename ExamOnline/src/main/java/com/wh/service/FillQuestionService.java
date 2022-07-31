package com.wh.service;

import com.wh.bean.FillQuestion;
import com.wh.utils.SqlSessionFactoryUtils;
import com.wh.vo.QuestionQuery;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * @author whstart
 * @creat 2022--09-16:34
 */
public interface FillQuestionService {
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    List<FillQuestion> getQuestionList(QuestionQuery questionQuery);

    Integer countQuestion(QuestionQuery questionQuery);

    void deleteById(String questionId);

    void deleteByIds(int[] questionIds);

    FillQuestion selectById(int parseInt);

    void updateQuestion(FillQuestion question);

    void addQuestion(FillQuestion question);

    Integer getMinId();

    Integer getMaxId();

    int[] getQuesIdByCourse(String course);
}
