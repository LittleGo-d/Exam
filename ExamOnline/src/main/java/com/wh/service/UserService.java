package com.wh.service;

import com.wh.bean.User;
import com.wh.utils.SqlSessionFactoryUtils;
import com.wh.vo.UserQuery;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * @author whstart
 * @creat 2022--02-15:26
 */
public interface UserService {
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    User login(User user);

    List<User> getUserList(UserQuery userQuery);

    Integer countUser(UserQuery userQuery);

    boolean register(User user);

    Integer countTeacher(UserQuery userQuery);

    List<User> getTeacherList(UserQuery userQuery);

    List<User> getStudentList(UserQuery userQuery);

    Integer countStudent(UserQuery userQuery);

    void deleteById(String userId);

    void deleteByIds(int[] userIds);

    User selectById(String userId);

    void updateUser(User user);
}
