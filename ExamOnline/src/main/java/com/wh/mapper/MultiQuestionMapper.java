package com.wh.mapper;

import com.wh.bean.MultiQuestion;
import com.wh.vo.QuestionQuery;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author whstart
 * @creat 2022--01-18:52
 */
public interface MultiQuestionMapper {
    //获取问题列表
    List<MultiQuestion> getQuestionList(QuestionQuery query);
    //获取问题总数
    Integer countQuestion(QuestionQuery query);

    @Insert("insert into multi_question values (null,#{subject},#{content},#{optionA},#{optionB},#{optionC},#{optionD},#{answer},#{score},#{section},#{difficulty})")
    @ResultMap("multiQuestionMap")
    void addQuestion(MultiQuestion question);

    @Delete("delete from multi_question where question_id = #{questionId}")
    @ResultMap("multiQuestionMap")
    void deleteById(String questionId);

    void deleteByIds(@Param("questionIds") int[] questionIds);

    @Select("select * from multi_question where question_id = #{questionId}")
    @ResultMap("multiQuestionMap")
    MultiQuestion selectById(@Param("questionId") int questionId);

    @Update("update multi_question set subject = #{question.subject},content = #{question.content},optionA = #{question.optionA},optionB = #{question.optionB},optionC = #{question.optionC},optionD = #{question.optionD},answer = #{question.answer},score = #{question.score},section = #{question.section},difficulty = #{question.difficulty} where question_id = #{question.questionId}")
    @ResultMap("multiQuestionMap")
    void updateQuestion(@Param("question") MultiQuestion question);

    @Select("select min(question_id) from multi_question")
    Integer getMinId();

    @Select("select max(question_id) from multi_question")
    Integer getMaxId();

    @Select("select question_id from multi_question where subject = #{course}")
    int[] getQuesIdByCourse(@Param("course") String course);
}
