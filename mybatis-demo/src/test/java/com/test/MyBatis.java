package com.test;

import com.mapper.BrandMapper;
import com.pojo.Brand;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @code Description
 * @code author 本当迷
 * @code date 2022/7/22-17:10
 */
public class MyBatis {
    @Test
    public void testSelectAll() {
        // 1,加载mybatis的核心配置文件， 获取 sqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2.获取SqlSession对象
        final SqlSession sqlSession = sqlSessionFactory.openSession();
        // 3.获取Mapper接口代理对象
        final BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        // 4.执行方法
        final List<Brand> selectAll = brandMapper.selectAll();
        selectAll.forEach(System.out::println);

        // 5.释放资源
        sqlSession.close();
    }

    @Test
    public void testSelectById() {
        Integer id = 1;

        // 1,加载mybatis的核心配置文件， 获取 sqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2.获取SqlSession对象
        final SqlSession sqlSession = sqlSessionFactory.openSession();
        // 3.获取Mapper接口代理对象
        final BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        // 4.执行方法
        final Brand selectById = brandMapper.selectById(id);
        System.out.println(selectById);

        // 5.释放资源
        sqlSession.close();
    }

    @Test
    public void testSelectByCondition() {

        // 1,加载mybatis的核心配置文件， 获取 sqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 接受参数
        int status = 1;
        String companyName = "华为";
        String brandName = "华为";


        // 处理参数
        companyName = "%" + companyName + "%";
        brandName = "%" + brandName + "%";

        final Brand brand = new Brand();
        brand.setStatus(status);
        brand.setCompanyName(companyName);
        brand.setBrandName(brandName);

        // 使用Map集合
        final Map hashMap = new HashMap();
        hashMap.put("status", status);
        hashMap.put("companyName", companyName);
        hashMap.put("brandName", brandName);


        // 2.获取SqlSession对象
        final SqlSession sqlSession = sqlSessionFactory.openSession();
        // 3.获取Mapper接口代理对象
        final BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        // 4.执行方法
        //final List<Brand> brandList = brandMapper.selectByCondition(status, companyName, brandName);

        final List<Brand> brandList = brandMapper.selectByCondition(hashMap);
        brandList.forEach(System.out::println);

        // 5.释放资源
        sqlSession.close();
    }

    @Test
    public void add() {

        // 1,加载mybatis的核心配置文件， 获取 sqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 接受参数
        int status = 1;
        String companyName = "小米手机";
        String brandName = "小米";
        String description = "为发烧而生！";
        int ordered = 100;


        final Brand brand = new Brand();
        brand.setStatus(status);
        brand.setCompanyName(companyName);
        brand.setBrandName(brandName);
        brand.setDescription(description);
        brand.setOrdered(ordered);

        // 2.获取SqlSession对象
        final SqlSession sqlSession = sqlSessionFactory.openSession();
        // 3.获取Mapper接口代理对象
        final BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        // 4.执行方法
        brandMapper.add(brand);

        // 提交事务
        sqlSession.commit();

        final List<Brand> brandList = brandMapper.selectAll();
        brandList.forEach(System.out::println);

        // 5.释放资源
        sqlSession.close();
    }

    @Test
    public void update(){
        // 1,加载mybatis的核心配置文件， 获取 sqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 接受参数
        int id = 5;
        int status = 1;
        String companyName = "小米手机";
        String brandName = "小米";
        String description = "为发烧而生烫烫烫烫烫！";
        int ordered = 100;



        final Brand brand = new Brand();
        brand.setStatus(status);
        brand.setCompanyName(companyName);
        brand.setBrandName(brandName);
        brand.setDescription(description);
        brand.setOrdered(ordered);
        brand.setId(id);


        // 2.获取SqlSession对象
        final SqlSession sqlSession = sqlSessionFactory.openSession();
        // 3.获取Mapper接口代理对象
        final BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        // 4.执行方法
        final int update = brandMapper.update(brand);
        System.out.println(update);

        // 提交事务
        sqlSession.commit();

        // 5.释放资源
        sqlSession.close();
    }
    @Test
    public void selectByConditionSingle(){
        // 1,加载mybatis的核心配置文件， 获取 sqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 接受参数
        int status = 1;
        String companyName = "华为";
        String brandName = "华为";


        // 处理参数
        companyName = "%" + companyName + "%";
        brandName = "%" + brandName + "%";

        final Brand brand = new Brand();
        brand.setStatus(status);
        brand.setCompanyName(companyName);
        brand.setBrandName(brandName);


        // 2.获取SqlSession对象
        final SqlSession sqlSession = sqlSessionFactory.openSession();
        // 3.获取Mapper接口代理对象
        final BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        // 4.执行方法
        final List<Brand> brandList = brandMapper.selectByConditionSingle(brand);
        brandList.forEach(System.out::println);



        // 5.释放资源
        sqlSession.close();
    }

    @Test
    public void deleteById(){
        // 1,加载mybatis的核心配置文件， 获取 sqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        final SqlSession sqlSession = sqlSessionFactory.openSession();
        final BrandMapper sessionMapper = sqlSession.getMapper(BrandMapper.class);
        sessionMapper.deleteById(6);
        System.out.println("删除成功！");

        // 提交事务
        sqlSession.commit();

        // 资源关闭
        sqlSession.close();
    }

    @Test
    public void deleteByIds(){
        // 1,加载mybatis的核心配置文件， 获取 sqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        final SqlSession sqlSession = sqlSessionFactory.openSession();
        final BrandMapper sessionMapper = sqlSession.getMapper(BrandMapper.class);
        Integer [] ids = {5, 7};
        sessionMapper.deleteByIds(ids);
        System.out.println("删除成功！");

        // 提交事务
        sqlSession.commit();

        // 资源关闭
        sqlSession.close();

    }




}
