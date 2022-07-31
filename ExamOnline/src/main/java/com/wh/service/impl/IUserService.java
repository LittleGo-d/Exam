package com.wh.service.impl;

import com.wh.bean.User;
import com.wh.mapper.UserMapper;
import com.wh.service.UserService;
import com.wh.vo.UserQuery;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * @author whstart
 * @creat 2022--02-15:27
 */
public class IUserService implements UserService {
    @Override
    public User login(User user) {
        SqlSession sqlSession = factory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User loginUser = mapper.login(user);
        return loginUser;
    }

    @Override
    public List<User> getUserList(UserQuery userQuery) {
        SqlSession sqlSession = factory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> list = mapper.getUserList(userQuery);
        sqlSession.close();
        return list;
    }

    @Override
    public Integer countUser(UserQuery userQuery) {
        SqlSession sqlSession = factory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        Integer count = mapper.countUser(userQuery);
        return count;
    }

    @Override
    public boolean register(User user) {
        SqlSession sqlSession = factory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        boolean isNull = mapper.register(user);
        sqlSession.commit();
        sqlSession.close();
        return isNull;
    }

    @Override
    public Integer countTeacher(UserQuery userQuery) {
        SqlSession sqlSession = factory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        Integer count = mapper.countTeacher(userQuery);
        return count;
    }

    @Override
    public List<User> getTeacherList(UserQuery userQuery) {
        SqlSession sqlSession = factory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> list = mapper.getTeacherList(userQuery);
        sqlSession.close();
        return list;
    }

    @Override
    public List<User> getStudentList(UserQuery userQuery) {
        SqlSession sqlSession = factory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> list = mapper.getStudentList(userQuery);
        sqlSession.close();
        return list;
    }

    @Override
    public Integer countStudent(UserQuery userQuery) {
        SqlSession sqlSession = factory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        Integer count = mapper.countStudent(userQuery);
        return count;
    }

    @Override
    public void deleteById(String userId) {
        SqlSession sqlSession = factory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.deleteById(userId);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void deleteByIds(int[] userIds) {
        SqlSession sqlSession = factory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.deleteByIds(userIds);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public User selectById(String userId) {
        SqlSession sqlSession = factory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.selectById(userId);
        sqlSession.close();
        return user;
    }

    @Override
    public void updateUser(User user) {
        SqlSession sqlSession = factory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.updateUser(user);
        sqlSession.commit();
        sqlSession.close();
    }
}
