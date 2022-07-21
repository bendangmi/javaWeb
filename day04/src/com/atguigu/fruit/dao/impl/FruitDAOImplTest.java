package com.atguigu.fruit.dao.impl;

import com.atguigu.fruit.dao.util.JDBCUtils;
import com.atguigu.fruit.pojo.Fruit;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @code Description
 * @code author 本当迷
 * @code date 2022/7/18-20:31
 */
class FruitDAOImplTest {

    @Test
    void getFruitList() throws Exception {
        final Connection conn= JDBCUtils.getConnection();
        final FruitDAOImpl dao = new FruitDAOImpl();
        final List<Fruit> list = dao.getFruitList(conn);
        list.forEach(System.out::println);
    }

    @Test
    void addFruit() throws Exception {
        final Connection conn= JDBCUtils.getConnection();
        final Fruit fruit = new Fruit(0, "本当迷1", 10, 10, "2");
        final FruitDAOImpl dao = new FruitDAOImpl();
        dao.addFruit(conn, fruit);
    }

    @Test
    void updateFruit() throws Exception {
        final Connection conn= JDBCUtils.getConnection();
        final Fruit fruit = new Fruit(1, "本当迷1", 10, 10, "2");
        final FruitDAOImpl dao = new FruitDAOImpl();
        dao.updateFruit(conn, fruit);

    }

    @Test
    void getFruitByFname() {
    }

    @Test
    void delFruit() throws Exception {
        final Connection conn= JDBCUtils.getConnection();
        final FruitDAOImpl dao = new FruitDAOImpl();
        final int delFruit = dao.delFruit(conn, 1);
    }

    @Test
    void getFruitByFid() throws Exception {
        final Connection conn= JDBCUtils.getConnection();
        final FruitDAOImpl dao = new FruitDAOImpl();
        final Fruit fruit = dao.getFruitByFid(conn, 7);
        System.out.println(fruit);
    }

}