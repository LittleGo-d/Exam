package com.wh.mapper;

import com.wh.bean.Paper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author whstart
 * @creat 2022--12-14:30
 */
public interface PaperMapper {

    @Select("select * from paper_manage where paperId = #{paperId}")
    List<Paper> getPaper(@Param("paperId") String paperId);

    @Select("select max(paperId) from paper_manage")
    Integer getMaxPaperId();

    @Insert("insert paper_manage values(#{paperId},#{questionType},#{questionId})")
    void addPaper(Paper paper);
}
