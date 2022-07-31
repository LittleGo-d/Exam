package com.wh.service;

import com.wh.bean.Paper;
import com.wh.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * @author whstart
 * @creat 2022--12-14:31
 */
public interface PaperService {
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    List<Paper> getPaper(String paperId);
    //获取当前试卷最大编号
    Integer getMaxPaperId();

    void addPaper(Paper paper);
}
