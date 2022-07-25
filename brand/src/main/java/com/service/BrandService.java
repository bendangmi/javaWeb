package com.service;

import com.pojo.Brand;
import com.pojo.PageBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @code Description
 * @code author 本当迷
 * @code date 2022/7/24-18:55
 */
public interface BrandService {
    /**
     *  查询所有
     * @return
     */
    List<Brand> selectAll();

    /**
     * 添加
     * @param brand
     */
    void add(Brand brand);

    /**
     *  批量删除
     * @param ids
     */
    void deleteByIds(@Param("ids") int[] ids);

    /**
     * 分页查询
     * @param currentPage 每页页码
     * @param pageSize 每页条数
     * @return
     */
    PageBean<Brand> selectByPage(int currentPage, int pageSize);

    /**
     * 分页条件查询
     * @param currentPage
     * @param pageSize
     * @param brand
     * @return
     */
    PageBean<Brand> selectByPageAndCondition(int currentPage, int pageSize, Brand brand);

    /**
     *
     * @param brand
     */
    void update(Brand brand);

}
