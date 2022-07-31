package com.wh.service.impl;

import com.wh.bean.Score;
import com.wh.mapper.ScoreMapper;
import com.wh.service.ScoreService;
import org.apache.ibatis.session.SqlSession;

/**
 * @author whstart
 * @creat 2022--16-14:57
 */
public class IScoreService implements ScoreService {

    @Override
    public void addScore(Score score) {
        SqlSession session = factory.openSession();
        ScoreMapper mapper = session.getMapper(ScoreMapper.class);
        mapper.addScore(score);
        session.commit();
        session.close();
    }
}
