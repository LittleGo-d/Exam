package com.wh.mapper;

import com.wh.bean.User;
import com.wh.vo.UserQuery;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author whstart
 * @creat 2022--02-15:24
 */
public interface UserMapper {
    //登录
    User login(User user);

    List<User> getUserList(UserQuery userQuery);

    Integer countUser(UserQuery userQuery);

    @Insert("insert into user values (#{user.userId},#{user.userName},#{user.sex},#{user.profession},#{user.password},'学生',3)")
    @ResultMap("userMap")
    boolean register(@Param("user") User user);

    List<User> getTeacherList(UserQuery userQuery);

    Integer countTeacher(UserQuery userQuery);

    List<User> getStudentList(UserQuery userQuery);

    Integer countStudent(UserQuery userQuery);

    @Delete("delete from user where user_id = #{userId}")
    @ResultMap("userMap")
    void deleteById(String userId);

    void deleteByIds(@Param("userIds") int[] userIds);

    @Select("select * from user where user_id = #{userId}")
    @ResultMap("userMap")
    User selectById(@Param("userId") String userId);

    @Update("update user set user_name = #{user.userName},sex = #{user.sex},profession = #{user.profession} where user_id = #{user.userId}")
    @ResultMap("userMap")
    void updateUser(@Param("user") User user);
}
