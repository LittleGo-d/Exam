package com.wh.service.impl;

import com.wh.bean.JudgeQuestion;
import com.wh.bean.MultiQuestion;
import com.wh.mapper.JudgeQuestionMapper;
import com.wh.service.JudgeQuestionService;
import com.wh.vo.QuestionQuery;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * @author whstart
 * @creat 2022--09-16:35
 */
public class IJudgeQuestionService implements JudgeQuestionService {

    @Override
    public List<JudgeQuestion> getQuestionList(QuestionQuery questionQuery) {
        SqlSession sqlSession = factory.openSession();
        JudgeQuestionMapper mapper = sqlSession.getMapper(JudgeQuestionMapper.class);
        List<JudgeQuestion> list = mapper.getQuestionList(questionQuery);
        sqlSession.close();
        return list;
    }

    @Override
    public Integer countQuestion(QuestionQuery questionQuery) {
        SqlSession sqlSession = factory.openSession();
        JudgeQuestionMapper mapper = sqlSession.getMapper(JudgeQuestionMapper.class);
        Integer count = mapper.countQuestion(questionQuery);
        sqlSession.close();
        return count;
    }

    @Override
    public void deleteById(String questionId) {
        SqlSession sqlSession = factory.openSession();
        JudgeQuestionMapper mapper = sqlSession.getMapper(JudgeQuestionMapper.class);
        mapper.deleteById(questionId);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void deleteByIds(int[] questionIds) {
        SqlSession sqlSession = factory.openSession();
        JudgeQuestionMapper mapper = sqlSession.getMapper(JudgeQuestionMapper.class);
        mapper.deleteByIds(questionIds);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public JudgeQuestion selectById(int questionId) {
        SqlSession sqlSession = factory.openSession();
        JudgeQuestionMapper mapper = sqlSession.getMapper(JudgeQuestionMapper.class);
        JudgeQuestion question = mapper.selectById(questionId);
        sqlSession.close();
        return question;
    }

    @Override
    public void updateQuestion(JudgeQuestion question) {
        SqlSession sqlSession = factory.openSession();
        JudgeQuestionMapper mapper = sqlSession.getMapper(JudgeQuestionMapper.class);
        mapper.updateQuestion(question);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void addQuestion(JudgeQuestion question) {
        SqlSession sqlSession = factory.openSession();
        JudgeQuestionMapper mapper = sqlSession.getMapper(JudgeQuestionMapper.class);
        mapper.addQuestion(question);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public Integer getMinId() {
        SqlSession sqlSession = factory.openSession();
        JudgeQuestionMapper mapper = sqlSession.getMapper(JudgeQuestionMapper.class);
        Integer minId = mapper.getMinId();
        sqlSession.close();
        return minId;
    }

    @Override
    public Integer getMaxId() {
        SqlSession sqlSession = factory.openSession();
        JudgeQuestionMapper mapper = sqlSession.getMapper(JudgeQuestionMapper.class);
        Integer maxId = mapper.getMaxId();
        sqlSession.close();
        return maxId;
    }

    @Override
    public int[] getQuesIdByCourse(String course) {
        SqlSession sqlSession = factory.openSession();
        JudgeQuestionMapper mapper = sqlSession.getMapper(JudgeQuestionMapper.class);
        int[] ids = mapper.getQuesIdByCourse(course);
        sqlSession.close();
        return ids;
    }
}
