package com.wh.mapper;

import com.wh.bean.Score;
import org.apache.ibatis.annotations.Insert;

/**
 * @author whstart
 * @creat 2022--16-14:58
 */
public interface ScoreMapper {

    @Insert("insert into score(studentId,subject,score,answerDate) values (#{studentId},#{subject},#{score},#{answerDate})")
    void addScore(Score score);
}
