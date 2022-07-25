package com.service.impl;

import com.mapper.BrandMapper;
import com.pojo.Brand;
import com.pojo.PageBean;
import com.service.BrandService;
import com.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @code Description
 * @code author 本当迷
 * @code date 2022/7/24-19:04
 */
public class BrandServiceImpl implements BrandService {
    // 1.创建SqlSessionFactory对象
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    @Override
    public List<Brand> selectAll() {
        // 2.获取SqlSession对象
        final SqlSession sqlSession = factory.openSession();

        // 3.获取BrandMapper
        final BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        final List<Brand> brands = mapper.selectAll();

        // 4.关闭sqlSession
        sqlSession.close();

        // 5. 调用方法
        return brands;


    }

    @Override
    public void add(Brand brand) {
        // 2.获取SqlSession对象
        final SqlSession sqlSession = factory.openSession();

        // 3.获取BrandMapper
        final BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        mapper.add(brand);

        // 提交事务
        sqlSession.commit();

        // 4.关闭sqlSession
        sqlSession.close();

    }

    @Override
    public void deleteByIds(int[] ids) {
        // 2.获取SqlSession对象
        final SqlSession sqlSession = factory.openSession();

        // 3.获取BrandMapper
        final BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        mapper.deleteByIds(ids);

        // 提交事务
        sqlSession.commit();

        // 4.关闭sqlSession
        sqlSession.close();
    }

    @Override
    public PageBean<Brand> selectByPage(int currentPage, int pageSize) {
        // 2.获取SqlSession对象
        final SqlSession sqlSession = factory.openSession();

        // 3.获取BrandMapper
        final BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        // 4.计算索引
        int begin = (currentPage - 1) * pageSize;

        // 计算查询条目数

        // 5.查询当前每页数据
        final List<Brand> brands = mapper.selectByPage(begin, pageSize);

        // 6. 查询总记录数
        final int count = mapper.selectTotalCount();

        final PageBean<Brand> pageBean = new PageBean<>(count, brands);

        sqlSession.close();

        return pageBean;
    }

    @Override
    public PageBean<Brand> selectByPageAndCondition(int currentPage, int pageSize, Brand brand) {
        // 2.获取SqlSession对象
        final SqlSession sqlSession = factory.openSession();

        // 3.获取BrandMapper
        final BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        // 4.计算索引
        int begin = (currentPage - 1) * pageSize;

        // 计算查询条目数

        // 处理brand条件数
        final String brandName = brand.getBrandName();
        if(brandName != null && brandName.length() > 0){
            brand.setBrandName("%" + brandName + "%");
        }
        final String companyName = brand.getCompanyName();
        if(companyName != null && companyName.length() > 0){
            brand.setCompanyName("%" + companyName + "%");
        }

        // 5.查询当前每页数据
        final List<Brand> brands = mapper.selectByPageAndCondition(begin, pageSize, brand);

        // 6. 查询总记录数
        final int count = mapper.selectTotalCountByCondition(brand);

        final PageBean<Brand> pageBean = new PageBean<>(count, brands);

        sqlSession.close();

        return pageBean;
    }

    @Override
    public void update(Brand brand) {
        // 2.获取SqlSession对象
        final SqlSession sqlSession = factory.openSession();

        // 3.获取BrandMapper
        final BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        mapper.update(brand);

        sqlSession.commit();

        sqlSession.close();
    }



}
