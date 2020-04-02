package com.bdqn.mall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 怪盗一只羊
 * @since 2020-04-01
 */
public class Type implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 分类编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 分类名称
     */
    private String typename;

    /**
     * 父级分类
     */
    private Integer parent;

    /**
     * 当前分类
     */
    private Integer type;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Type{" +
        "id=" + id +
        ", typename=" + typename +
        ", parent=" + parent +
        ", type=" + type +
        "}";
    }
}
