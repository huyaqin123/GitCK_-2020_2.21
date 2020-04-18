package com.bdqn.mall.mall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 怪盗一只羊
 * @since 2020-04-16
 */
public class GoodsProvider implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 供应商编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 供应商名称
     */
    @TableField("providerName")
    private String providerName;

    /**
     * 所属一级分类编号
     */
    @TableField("typeIdOne")
    private Integer typeIdOne;

    /**
     * 所属二级分类编号
     */
    @TableField("typeIdTwo")
    private Integer typeIdTwo;

    /**
     * 所属一级分类编号
     */
    @TableField("typeTree")
    private Integer typeTree;

    /**
     * 供应商所在城市
     */
    private String address;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public Integer getTypeIdOne() {
        return typeIdOne;
    }

    public void setTypeIdOne(Integer typeIdOne) {
        this.typeIdOne = typeIdOne;
    }

    public Integer getTypeIdTwo() {
        return typeIdTwo;
    }

    public void setTypeIdTwo(Integer typeIdTwo) {
        this.typeIdTwo = typeIdTwo;
    }

    public Integer getTypeTree() {
        return typeTree;
    }

    public void setTypeTree(Integer typeTree) {
        this.typeTree = typeTree;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "GoodsProvider{" +
        "id=" + id +
        ", providerName=" + providerName +
        ", typeIdOne=" + typeIdOne +
        ", typeIdTwo=" + typeIdTwo +
        ", typeTree=" + typeTree +
        ", address=" + address +
        "}";
    }
}
