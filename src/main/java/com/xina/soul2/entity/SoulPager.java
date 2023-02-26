package com.xina.soul2.entity;

public class SoulPager {
    private int index; // 当前页码
    private int pageSize; // 一页多少条数据
    private int totalPages; // 总共多少页

    private String fromTable; // 数据从哪个表查

    public SoulPager(){

    }

    public SoulPager(int index, int pageSize, int totalPages, String fromTable) {
        this.index = index;
        this.pageSize = pageSize;
        this.totalPages = totalPages;
        this.fromTable = fromTable;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public String getFromTable() {
        return fromTable;
    }

    public void setFromTable(String fromTable) {
        this.fromTable = fromTable;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
