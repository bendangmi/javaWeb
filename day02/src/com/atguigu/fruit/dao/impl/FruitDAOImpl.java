package com.atguigu.fruit.dao.impl;

import com.atguigu.fruit.dao.FruitDAO;
import com.atguigu.fruit.dao.base.BaseDAO;
import com.atguigu.fruit.pojo.Fruit;

import java.sql.Connection;
import java.util.List;

public class FruitDAOImpl extends BaseDAO<Fruit> implements FruitDAO {


    @Override
    public List<Fruit> getFruitList(Connection conn) {
        String sql = "select * from t_fruit";
        final List<Fruit> list = getForList(conn, sql);
        return list;
    }

    @Override
    public int addFruit(Connection conn, Fruit fruit) {
        String sql = "insert into t_fruit values(0,?,?,?,?)";
        final int update = getUpdate(conn, sql, fruit.getFname(), fruit.getPrice(), fruit.getFcount(), fruit.getRemark());
        return update;
    }

    @Override
    public int updateFruit(Connection conn, Fruit fruit) {
        String sql = "update t_fruit set fcount = ? where fid = ? " ;
        final int update = getUpdate(conn, sql, fruit.getFcount(), fruit.getFid());
        return update;
    }

    @Override
    public Fruit getFruitByFname(Connection conn, String fname) {
        String sql = "select * from t_fruit where fname like ? ";
        return null;
    }

    @Override
    public int delFruit(Connection conn, String fname) {
        String sql = "delete from t_fruit where fname like ? " ;
        final int update = getUpdate(conn, sql, fname);
        return update;
    }
}