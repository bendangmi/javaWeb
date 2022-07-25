package com.service;

import com.mapper.UserMapper;
import com.pojo.User;
import com.util.sqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 * @code Description
 * @code author 本当迷
 * @code date 2022/7/24-6:27
 */
public class UserService {
    final SqlSessionFactory sqlSessionFactory = sqlSessionFactoryUtils.getSqlSessionFactory();

    /**
     * 登录方法
     * @param username
     * @param password
     * @return
     */

    public User login(String username, String password) {
        final SqlSessionFactory sqlSessionFactory = sqlSessionFactoryUtils.getSqlSessionFactory();
        // 2.2获取SqlSession对象
        final SqlSession sqlSession = sqlSessionFactory.openSession();
        // 2.3获取Mapper
        final UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        // 2.4 调用方法
        final User user = userMapper.select(username, password);
        sqlSession.close();
        return user;

    }

    public User selectByUsername(String name) {
        // 2.2获取SqlSession对象
        final SqlSession sqlSession = sqlSessionFactory.openSession();
        // 2.3获取Mapper
        final UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        // 2.4 调用方法
        final User user = userMapper.selectByUsername(name);
        sqlSession.close();
        return user;

    }

    public void add(User user) {
        // 2.2获取SqlSession对象
        final SqlSession sqlSession = sqlSessionFactory.openSession();
        // 2.3获取Mapper
        final UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.add(user);
        sqlSession.commit();
        sqlSession.close();
    }

    public boolean register(User user){
        // 2.2获取SqlSession对象
        final SqlSession sqlSession = sqlSessionFactory.openSession();
        // 2.3获取Mapper
        final UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        // 4.判断用户名是否存在
        final User user1 = userMapper.selectByUsername(user.getUsername());

        if(user1 == null) {
            userMapper.add(user);
            sqlSession.commit();
        }
        sqlSession.close();
        return user1 == null;
    }
}
