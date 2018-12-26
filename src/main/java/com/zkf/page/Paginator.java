/**
 * LY.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.zkf.page;

import java.io.Serializable;

public class Paginator implements Serializable {

    /**  */
    private static final long serialVersionUID  = 1L;

    /** 默认每页数据条目20 */
    private static final Integer  DEFAULT_PAGE_SIZE = 20;

    /** 数据总量 */
    private Integer               totalCount;

    /** 总页数 */
    private Integer               totalPage;

    /** 每页条目数 */
    private Integer               pageSize          = DEFAULT_PAGE_SIZE;

    /** 查询起始条目编号，默认从0开始 */
    private Integer               startIndex;

    /** 当前页页码 */
    private Integer               currentPage;

    /** 上一页页码 */
    private Integer               nextPage;

    /** 下一页页码 */
    private Integer               priviousPage;

    /**
     * 默认构造函数
     */
    public Paginator() {
    }

    /**
     * 构造函数
     *
     * @param totalCount 数据总条目数
     * @param pageSize 每页条目数
     * @param currentPage 当前页码
     */
    protected Paginator(int totalCount, int pageSize, int currentPage) {
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.currentPage = currentPage;
//        getTotalPage();
//        getCurrentPage();
//        getStartIndex();
//        getNextPage();
//        getPriviousPage();
    }


    /**
     * 获取每页数据条目数
     *
     * @return 每页数据条目数
     */
//    public int getPageSize() {
//        if (pageSize <= 0) {
//            pageSize = DEFAULT_PAGE_SIZE;
//        }
//        return pageSize;
//    }


    /**
     * 获取当前页码
     *
     * @return 当前页面
     */
//    public int getCurrentPage() {
//        if (currentPage <= 0) {
//            return 1;
//        } else if (currentPage > getTotalPage()) {
//            return getTotalPage();
//        }
//        return currentPage;
//    }


    /**
     * 获取总页数
     *
     * @return
     */
//    public int getTotalPage() {
//        if (totalCount > 0) {
//            if (getPageSize() > 0) {
//                totalPage = totalCount / getPageSize();
//                if ((totalCount % getPageSize()) > 0) {
//                    totalPage++;
//                }
//            } else {
//                totalPage = 1;
//            }
//        } else {
//            totalPage = 0;
//        }
//        return totalPage;
//    }

    /**
     * 获取查询起始条目编号
     *
     * @return 查询起始条目编号
     */
//    public int getStartIndex() {
//        if (getCurrentPage() > 0) {
//            if (getPageSize() > 0) {
//                startIndex = (getCurrentPage() - 1) * getPageSize();
//            } else {
//                startIndex = 0;
//            }
//        } else {
//            startIndex = 0;
//        }
//        return startIndex;
//    }

    /**
     * 获取下一页页码
     *
     * @return 下一页页码
     */
//    public int getNextPage() {
//        if (getCurrentPage() >= getTotalPage()) {
//            nextPage = getTotalPage();
//        } else {
//            if (getTotalPage() == 0) {
//                nextPage = 1;
//            } else {
//                nextPage = getCurrentPage() + 1;
//            }
//
//        }
//        return nextPage;
//    }

    /**
     * 获取上一页页码
     *
     * @return 上一页页码
     */
//    public int getPriviousPage() {
//        if (getCurrentPage() <= 1) {
//            priviousPage = 1;
//        } else if (getCurrentPage() > getTotalPage()) {
//            priviousPage = getTotalPage() - 1;
//        } else {
//            priviousPage = getCurrentPage() - 1;
//        }
//        return priviousPage;
//    }

    @Override
    public String toString() {
        return "Paginator{" +
                "totalCount=" + totalCount +
                ", totalPage=" + totalPage +
                ", pageSize=" + pageSize +
                ", startIndex=" + startIndex +
                ", currentPage=" + currentPage +
                ", nextPage=" + nextPage +
                ", priviousPage=" + priviousPage +
                '}';
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public void setPriviousPage(int priviousPage) {
        this.priviousPage = priviousPage;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getNextPage() {
        return nextPage;
    }

    public void setNextPage(Integer nextPage) {
        this.nextPage = nextPage;
    }

    public Integer getPriviousPage() {
        return priviousPage;
    }

    public void setPriviousPage(Integer priviousPage) {
        this.priviousPage = priviousPage;
    }

    public static void main(String[] args) {
        System.out.println(new Paginator(100, 20, 3));
    }

}
