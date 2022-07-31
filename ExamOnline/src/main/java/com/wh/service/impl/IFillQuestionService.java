package com.wh.service.impl;

import com.wh.bean.FillQuestion;
import com.wh.mapper.FillQuestionMapper;
import com.wh.service.FillQuestionService;
import com.wh.service.FillQuestionService;
import com.wh.vo.QuestionQuery;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * @author whstart
 * @creat 2022--09-16:35
 */
public class IFillQuestionService implements FillQuestionService {

    @Override
    public List<FillQuestion> getQuestionList(QuestionQuery questionQuery) {
        SqlSession sqlSession = factory.openSession();
        FillQuestionMapper mapper = sqlSession.getMapper(FillQuestionMapper.class);
        List<FillQuestion> list = mapper.getQuestionList(questionQuery);
        sqlSession.close();
        return list;
    }

    @Override
    public Integer countQuestion(QuestionQuery questionQuery) {
        SqlSession sqlSession = factory.openSession();
        FillQuestionMapper mapper = sqlSession.getMapper(FillQuestionMapper.class);
        Integer count = mapper.countQuestion(questionQuery);
        sqlSession.close();
        return count;
    }

    @Override
    public void deleteById(String questionId) {
        SqlSession sqlSession = factory.openSession();
        FillQuestionMapper mapper = sqlSession.getMapper(FillQuestionMapper.class);
        mapper.deleteById(questionId);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void deleteByIds(int[] questionIds) {
        SqlSession sqlSession = factory.openSession();
        FillQuestionMapper mapper = sqlSession.getMapper(FillQuestionMapper.class);
        mapper.deleteByIds(questionIds);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public FillQuestion selectById(int questionId) {
        SqlSession sqlSession = factory.openSession();
        FillQuestionMapper mapper = sqlSession.getMapper(FillQuestionMapper.class);
        FillQuestion question = mapper.selectById(questionId);
        sqlSession.close();
        return question;
    }

    @Override
    public void updateQuestion(FillQuestion question) {
        SqlSession sqlSession = factory.openSession();
        FillQuestionMapper mapper = sqlSession.getMapper(FillQuestionMapper.class);
        mapper.updateQuestion(question);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void addQuestion(FillQuestion question) {
        SqlSession sqlSession = factory.openSession();
        FillQuestionMapper mapper = sqlSession.getMapper(FillQuestionMapper.class);
        mapper.addQuestion(question);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public Integer getMinId() {
        SqlSession sqlSession = factory.openSession();
        FillQuestionMapper mapper = sqlSession.getMapper(FillQuestionMapper.class);
        Integer minId = mapper.getMinId();
        sqlSession.close();
        return minId;
    }

    @Override
    public Integer getMaxId() {
        SqlSession sqlSession = factory.openSession();
        FillQuestionMapper mapper = sqlSession.getMapper(FillQuestionMapper.class);
        Integer maxId = mapper.getMaxId();
        sqlSession.close();
        return maxId;
    }

    @Override
    public int[] getQuesIdByCourse(String course) {
        SqlSession sqlSession = factory.openSession();
        FillQuestionMapper mapper = sqlSession.getMapper(FillQuestionMapper.class);
        int[] ids = mapper.getQuesIdByCourse(course);
        return ids;
    }
}
