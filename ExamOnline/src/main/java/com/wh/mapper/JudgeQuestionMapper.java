package com.wh.mapper;

import com.wh.bean.JudgeQuestion;
import com.wh.vo.QuestionQuery;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author whstart
 * @creat 2022--09-16:31
 */
public interface JudgeQuestionMapper {

    List<JudgeQuestion> getQuestionList(QuestionQuery questionQuery);

    Integer countQuestion(QuestionQuery questionQuery);

    @Delete("delete from judge_question where question_id = #{questionId}")
    @ResultMap("judgeQuestionMap")
    void deleteById(String questionId);

    void deleteByIds(@Param("questionIds") int[] questionIds);

    @Select("select * from judge_question where question_id = #{questionId}")
    @ResultMap("judgeQuestionMap")
    JudgeQuestion selectById(@Param("questionId") int questionId);

    @Update("update judge_question set subject = #{question.subject},content = #{question.content},answer = #{question.answer},score = #{question.score},section = #{question.section},difficulty = #{question.difficulty} where question_id = #{question.questionId} ")
    @ResultMap("judgeQuestionMap")
    void updateQuestion(@Param("question") JudgeQuestion question);

    @Insert("insert into judge_question values (null,#{subject},#{content},#{answer},#{score},#{section},#{difficulty})")
    @ResultMap("judgeQuestion")
    void addQuestion(JudgeQuestion question);

    @Select("select min(question_id) from judge_question")
    Integer getMinId();

    @Select("select max(question_id) from judge_question")
    Integer getMaxId();
    @Select("select question_id from judge_question where subject = #{course}")
    int[] getQuesIdByCourse(@Param("course") String course);
}
