package com;

import com.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @code Description MyBatis快速入门
 * @code author 本当迷
 * @code date 2022/7/22-15:14
 */
public class MyBatisDemo {
    public static void main(String[] args) {
        // 1,加载mybatis的核心配置文件， 获取 sqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2.获取sqlSession对象，用它来执行sql
        final SqlSession session = sqlSessionFactory.openSession();

        // 3.执行sql
        final List<User> userList = session.selectList("test.selectAll");
        userList.forEach(System.out::println);

        // 4.释放资源
        session.close();


    }
}
