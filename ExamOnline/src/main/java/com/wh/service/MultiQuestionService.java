package com.wh.service;

import com.wh.bean.MultiQuestion;
import com.wh.utils.SqlSessionFactoryUtils;
import com.wh.vo.QuestionQuery;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * @author whstart
 * @creat 2022--01-19:31
 */
public interface MultiQuestionService {
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    List<MultiQuestion> getQuestionList(QuestionQuery query);

    Integer countQuestion(QuestionQuery query);

    void addQuestion(MultiQuestion question);

    void deleteById(String questionId);

    void deleteByIds(int[] questionIds);

    MultiQuestion selectById(int questionId);

    void updateQuestion(MultiQuestion question);

    //找出最小questionId
    Integer getMinId();

    Integer getMaxId();

    int[] getQuesIdByCourse(String course);
}
