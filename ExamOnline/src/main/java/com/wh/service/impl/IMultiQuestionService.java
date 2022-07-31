package com.wh.service.impl;

import com.wh.bean.MultiQuestion;
import com.wh.mapper.MultiQuestionMapper;
import com.wh.service.MultiQuestionService;
import com.wh.vo.QuestionQuery;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * @author whstart
 * @creat 2022--01-20:32
 */
public class IMultiQuestionService implements MultiQuestionService {
    @Override
    public List<MultiQuestion> getQuestionList(QuestionQuery query) {
        SqlSession session = factory.openSession();
        MultiQuestionMapper mapper = session.getMapper(MultiQuestionMapper.class);
        return mapper.getQuestionList(query);
    }

    @Override
    public Integer countQuestion(QuestionQuery query) {
        SqlSession session = factory.openSession();
        MultiQuestionMapper mapper = session.getMapper(MultiQuestionMapper.class);
        Integer count = mapper.countQuestion(query);
        return count;
    }

    @Override
    public void addQuestion(MultiQuestion question) {
        SqlSession session = factory.openSession();
        MultiQuestionMapper mapper = session.getMapper(MultiQuestionMapper.class);
        mapper.addQuestion(question);
        session.commit();
        session.close();
    }

    @Override
    public void deleteById(String questionId) {
        SqlSession session = factory.openSession();
        MultiQuestionMapper mapper = session.getMapper(MultiQuestionMapper.class);
        mapper.deleteById(questionId);
        session.commit();
        session.close();
    }

    @Override
    public void deleteByIds(int[] questionIds) {
        SqlSession session = factory.openSession();
        MultiQuestionMapper mapper = session.getMapper(MultiQuestionMapper.class);
        mapper.deleteByIds(questionIds);
        session.commit();
        session.close();
    }

    @Override
    public MultiQuestion selectById(int questionId) {
        SqlSession session = factory.openSession();
        MultiQuestionMapper mapper = session.getMapper(MultiQuestionMapper.class);
        MultiQuestion question = mapper.selectById(questionId);
        session.close();
        return question;
    }

    @Override
    public void updateQuestion(MultiQuestion question) {
        SqlSession session = factory.openSession();
        MultiQuestionMapper mapper = session.getMapper(MultiQuestionMapper.class);
        mapper.updateQuestion(question);
        session.commit();
        session.close();
    }

    @Override
    public Integer getMinId() {
        SqlSession sqlSession = factory.openSession();
        MultiQuestionMapper mapper = sqlSession.getMapper(MultiQuestionMapper.class);
        Integer id = mapper.getMinId();
        return id;
    }

    @Override
    public Integer getMaxId() {
        SqlSession sqlSession = factory.openSession();
        MultiQuestionMapper mapper = sqlSession.getMapper(MultiQuestionMapper.class);
        Integer id = mapper.getMaxId();
        return id;
    }

    @Override
    public int[] getQuesIdByCourse(String course) {
        SqlSession sqlSession = factory.openSession();
        MultiQuestionMapper mapper = sqlSession.getMapper(MultiQuestionMapper.class);
        int[] ids = mapper.getQuesIdByCourse(course);
        sqlSession.close();
        return ids;
    }

}
