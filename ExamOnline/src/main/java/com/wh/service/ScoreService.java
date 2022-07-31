package com.wh.service;

import com.wh.bean.Score;
import com.wh.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 * @author whstart
 * @creat 2022--16-14:57
 */
public interface ScoreService {
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    void addScore(Score score);
}
