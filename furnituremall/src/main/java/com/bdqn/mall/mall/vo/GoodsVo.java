package com.bdqn.mall.mall.vo;


import com.bdqn.mall.mall.entity.Goods;

public class GoodsVo extends Goods {

    private Integer page;//当前页码
    private Integer limit;//每页显示数量


    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
