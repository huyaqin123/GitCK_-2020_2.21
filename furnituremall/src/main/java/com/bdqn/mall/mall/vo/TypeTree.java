package com.bdqn.mall.mall.vo;

import java.util.List;

/**
 * 工具类
 */
public class TypeTree {
    private int id;
    private String typeName;
    private Integer pid;
    private List<TypeTree> childeren;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }


    public List<TypeTree> getChilderen() {
        return childeren;
    }

    public void setChilderen(List<TypeTree> childeren) {
        this.childeren = childeren;
    }
}
