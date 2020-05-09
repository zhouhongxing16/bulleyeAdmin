package com.chris.bulleyeadmin.common.entity;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class PageResult<T> {


    @ApiModelProperty("总数")
    private Long total;

    @ApiModelProperty("列表对象")
    private List<T> list;

    private boolean isLastPage;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public boolean isLastPage() {
        return isLastPage;
    }

    public void setLastPage(boolean lastPage) {
        isLastPage = lastPage;
    }
}
