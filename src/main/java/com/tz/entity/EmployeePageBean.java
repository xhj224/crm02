package com.tz.entity;

import java.util.List;

public class EmployeePageBean {
    private List<Employee> employees; // 保存员工信息的列表
    private int currentPage; // 当前页
    private int previousPage; // 前一页
    private int nextPage; // 后一页
    private Long totalPage; // 总页数
    private int pageSize; // 每页显示多少条记录
    private Long rowCount; // 一共有多少条记录

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Long getRowCount() {
        return rowCount;
    }

    public void setRowCount(Long rowCount) {
        this.rowCount = rowCount;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPreviousPage() {
        return previousPage;
    }

    public void setPreviousPage(int currentPage) {
        if (currentPage <= 1) {
            previousPage = 1;
        } else {
            previousPage = currentPage - 1;
        }
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int currentPage) {
        if (currentPage == getTotalPage()) {
            nextPage = currentPage;
        } else {
            nextPage = currentPage + 1;
        }
    }

    public Long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Long totalPage) {
        this.totalPage = totalPage / pageSize;
        if (totalPage % pageSize != 0) {
            this.totalPage++;
        }
    }

    @Override
    public String toString() {
        return "EmployeePageBean [employees=" + employees + ", currentPage=" + currentPage + ", previousPage="
                + previousPage + ", nextPage=" + nextPage + ", totalPage=" + totalPage + "]";
    }

}
