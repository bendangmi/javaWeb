package com.mapper;

import com.pojo.Brand;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @code Description
 * @code author 本当迷
 * @code date 2022/7/22-17:57
 */
public interface BrandMapper {
    /**
     *  查询所有
     */
    List<Brand> selectAll();

    /**
     * 查看详情：根据ID进行查询
     */
//    @Select("select * from tb_user where id = #{id}")
    Brand selectById(Integer id);

    /**
     * 条件查询
     *      参数接受
     *              1.散装参数：如果方法中有多个参数，需要使用@Param("SQL参数占位符名称")
     *              2.对象参数: 对象的属性名称要和参数占位符一致
     *              3.map集合
     * @param status, companyName, brandName
     * @return List
     */
    List<Brand> selectByCondition(@Param("status") int status, @Param("companyName") String companyName, @Param("brandName") String brandName);
    List<Brand> selectByCondition(Brand brand);
    List<Brand> selectByCondition(Map map);

    /**
     * 单条件查询
     * @param brand
     * @return List
     */
    List<Brand> selectByConditionSingle(Brand brand);

    /**
     * 添加
     * @param brand
     */
    void add(Brand brand);

    /**
     *  修改
     * @param brand
     * @return
     */
    int update(Brand brand);

    /**
     *  根据id 删除
     * @param id
     */

    void deleteById(int id);

    /**
     * 批量删除
     * @param integers
     */
    void deleteByIds(Integer[] integers);
}
