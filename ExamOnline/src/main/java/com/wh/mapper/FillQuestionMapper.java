package com.wh.mapper;

import com.wh.bean.FillQuestion;
import com.wh.vo.QuestionQuery;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author whstart
 * @creat 2022--09-16:31
 */
public interface FillQuestionMapper {

    List<FillQuestion> getQuestionList(QuestionQuery questionQuery);

    Integer countQuestion(QuestionQuery questionQuery);

    @Delete("delete from fill_question where question_id = #{questionId}")
    @ResultMap("fillQuestionMap")
    void deleteById(String questionId);

    void deleteByIds(@Param("questionIds") int[] questionIds);

    @Select("select * from fill_question where question_id = #{questionId}")
    @ResultMap("fillQuestionMap")
    FillQuestion selectById(@Param("questionId") int questionId);

    @Update("update fill_question set subject = #{question.subject},content = #{question.content},answer = #{question.answer},score = #{question.score},section = #{question.section},difficulty = #{question.difficulty} where question_id = #{question.questionId} ")
    @ResultMap("fillQuestionMap")
    void updateQuestion(@Param("question") FillQuestion question);

    @Insert("insert into fill_question values (null,#{subject},#{content},#{answer},#{score},#{difficulty},#{section})")
    @ResultMap("fillQuestion")
    void addQuestion(FillQuestion question);

    @Select("select min(question_id) from fill_question")
    Integer getMinId();

    @Select("select max(question_id) from fill_question")
    Integer getMaxId();

    @Select("select question_id from fill_question where subject = #{course}")
    int[] getQuesIdByCourse(@Param("course") String course);
}
