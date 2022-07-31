package com.wh.service.impl;

import com.wh.bean.Paper;
import com.wh.mapper.PaperMapper;
import com.wh.service.PaperService;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * @author whstart
 * @creat 2022--12-14:31
 */
public class IPaperService implements PaperService {

    @Override
    public List<Paper> getPaper(String paperId) {
        SqlSession sqlSession = factory.openSession();
        PaperMapper mapper = sqlSession.getMapper(PaperMapper.class);
        List<Paper> list = mapper.getPaper(paperId);
        return list;
    }

    @Override
    public Integer getMaxPaperId() {
        SqlSession sqlSession = factory.openSession();
        PaperMapper mapper = sqlSession.getMapper(PaperMapper.class);
        Integer maxPaperId = mapper.getMaxPaperId();
        return maxPaperId;
    }

    @Override
    public void addPaper(Paper paper) {
        SqlSession sqlSession = factory.openSession();
        PaperMapper mapper = sqlSession.getMapper(PaperMapper.class);
        mapper.addPaper(paper);
        sqlSession.commit();
        sqlSession.close();
    }

}
