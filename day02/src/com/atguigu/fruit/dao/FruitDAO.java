package com.atguigu.fruit.dao;

import com.atguigu.fruit.pojo.Fruit;

import java.sql.Connection;
import java.util.List;

public interface FruitDAO {
    //查询库存列表
    List<Fruit> getFruitList(Connection conn);

    //新增库存
    int addFruit(Connection conn, Fruit fruit);

    //修改库存
    int updateFruit(Connection conn, Fruit fruit);

    //根据名称查询特定库存
    Fruit getFruitByFname(Connection conn, String fname);

    //删除特定库存记录
    int delFruit(Connection conn, String fname);
}
