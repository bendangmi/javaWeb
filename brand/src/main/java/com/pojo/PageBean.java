package com.pojo;

import java.util.List;

/**
 * @code Description 分页查询的JavaBean
 * @code author 本当迷
 * @code date 2022/7/24-22:15
 */
public class PageBean<T> {
    // 总页数
    private int totalCount;

    // 当前页数据
    private List<T> rows;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public PageBean() {
    }

    public PageBean(int totalCount, List<T> rows) {
        this.totalCount = totalCount;
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "totalCount=" + totalCount +
                ", rows=" + rows +
                '}';
    }
}
