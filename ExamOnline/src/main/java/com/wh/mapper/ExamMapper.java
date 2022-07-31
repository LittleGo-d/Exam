package com.wh.mapper;

import com.wh.bean.Exam;
import com.wh.vo.ExamQuery;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author whstart
 * @creat 2022--11-17:32
 */
public interface ExamMapper {
    List<Exam> getExamList(ExamQuery examQuery);

    Integer countExam(ExamQuery examQuery);

    @Select("select * from exam_manage")
    @ResultMap("examMap")
    List<Exam> allExam();

    @Select("select * from exam_manage where examId = #{examId}")
    @ResultMap("examMap")
    Exam selectById(@Param("examId") int examId);

    @Update("update exam_manage set description = #{description},course = #{course},examDate = #{examDate},totalTime = #{totalTime},major = #{major} where examId = #{examId}")
    @ResultMap("examMap")
    void updateExam(Exam exam);

    @Insert("insert into exam_manage values(null,#{description},#{course},#{paperId},#{examDate},#{totalTime},#{grade},#{term},#{major},#{totalScore})")
    @ResultMap("examMap")
    void addExam(Exam exam);
}
