package com.atguigu.fruit.dao.impl;

import com.atguigu.fruit.dao.FruitDAO;
import com.atguigu.fruit.dao.base.BaseDAO;
import com.atguigu.fruit.pojo.Fruit;

import java.sql.Connection;
import java.util.List;

public class FruitDAOImpl extends BaseDAO<Fruit> implements FruitDAO {


    @Override
    public List<Fruit> getFruitList(Connection conn, String keyword, Integer page) {
        String sql = "select * from t_fruit where fname like ? or remark like ? limit ?, 5 ";
        final List<Fruit> list = getForList(conn, sql,"%"+keyword+"%", "%"+keyword+"%", (page - 1) * 5);
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
        String sql = "update t_fruit set fname = ?, price = ?, fcount = ?, remark = ? where fid = ? " ;
        final int update = getUpdate(conn, sql, fruit.getFname(), fruit.getPrice(), fruit.getFcount(), fruit.getRemark(), fruit.getFid());
        return update;
    }

    @Override
    public Fruit getFruitByFname(Connection conn, String fname) {
        String sql = "select * from t_fruit where fname like ? ";
        return null;
    }

    @Override
    public int delFruit(Connection conn, int id) {
        String sql = "delete from t_fruit where fid like ? " ;
        final int update = getUpdate(conn, sql, id);
        return update;
    }

    @Override
    public Fruit getFruitByFid(Connection conn, Integer fid) {
        String sql = "select * from t_fruit where fid = ?";
        final Fruit fruit = getInstance(conn, sql, fid);
        return fruit;
    }

    @Override
    public Long getFruitCount(Connection conn, String keyword) {
        String sql = "select count(*) from t_fruit where fname like ? or remark like ?" ;
        final Long count = getValue(conn, sql, "%"+keyword+"%", "%"+keyword+"%");
        return count;
    }
}