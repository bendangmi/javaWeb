package com.atguigu.fruit.dao;

import com.atguigu.fruit.pojo.Fruit;

import java.sql.Connection;
import java.util.List;

public interface FruitDAO {
    //查询库存列表
    List<Fruit> getFruitList(Connection conn, String keyword , Integer page);

    //新增库存
    int addFruit(Connection conn, Fruit fruit);

    //修改库存
    int updateFruit(Connection conn, Fruit fruit);

    //根据名称查询特定库存
    Fruit getFruitByFname(Connection conn, String fname);

    //删除特定库存记录
    int delFruit(Connection conn, int id);

    // 根据fid获取特定水果库存信息
    Fruit getFruitByFid(Connection conn, Integer fid);

    // 查询库存总记录条数
    Long getFruitCount(Connection conn, String keyword);
}
