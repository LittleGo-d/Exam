package com.wh.service.impl;

import com.wh.bean.Exam;
import com.wh.mapper.ExamMapper;
import com.wh.service.ExamService;
import com.wh.vo.ExamQuery;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * @author whstart
 * @creat 2022--11-17:24
 */
public class IExamService implements ExamService {

    @Override
    public List<Exam> getExamList(ExamQuery examQuery) {
        SqlSession sqlSession = factory.openSession();
        ExamMapper mapper = sqlSession.getMapper(ExamMapper.class);
        List<Exam> list = mapper.getExamList(examQuery);
        sqlSession.close();
        return list;
    }



    @Override
    public Integer countExam(ExamQuery examQuery) {
        SqlSession sqlSession = factory.openSession();
        ExamMapper mapper = sqlSession.getMapper(ExamMapper.class);
        Integer count = mapper.countExam(examQuery);
        sqlSession.close();
        return count;
    }

    @Override
    public List<Exam> AllExam() {
        SqlSession sqlSession = factory.openSession();
        ExamMapper mapper = sqlSession.getMapper(ExamMapper.class);
        List<Exam> list = mapper.allExam();
        sqlSession.close();
        return list;
    }

    @Override
    public Exam selectById(int examId) {
        SqlSession sqlSession = factory.openSession();
        ExamMapper mapper = sqlSession.getMapper(ExamMapper.class);
        Exam exam = mapper.selectById(examId);
        sqlSession.close();
        return exam;
    }

    @Override
    public void updateExam(Exam exam) {
        SqlSession sqlSession = factory.openSession();
        ExamMapper mapper = sqlSession.getMapper(ExamMapper.class);
        mapper.updateExam(exam);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void addExam(Exam exam) {
        SqlSession sqlSession = factory.openSession();
        ExamMapper mapper = sqlSession.getMapper(ExamMapper.class);
        mapper.addExam(exam);
        sqlSession.commit();
        sqlSession.close();
    }
}
