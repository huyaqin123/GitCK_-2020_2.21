package com.bdqn.mall.mall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 怪盗一只羊
 * @since 2020-04-05
 */
public class Goods implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 商品编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

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
     * 所属三级分类编号
     */
    @TableField("typeId")
    private Integer typeId;

    /**
     * 商品名称
     */
    @TableField("goodsName")
    private String goodsName;

    /**
     * 商品简介
     */
    private String introduction;

    /**
     * 商品图片路径
     */
    @TableField("goodsImg")
    private String goodsImg;

    /**
     * 商品库存量
     */
    private Integer inventory;

    /**
     * 商品供货量
     */
    private Integer supply;

    /**
     * 原价
     */
    @TableField("oPrice")
    private Double oPrice;

    /**
     * 现价
     */
    @TableField("rPrice")
    private Double rPrice;

    /**
     * 成交量
     */
    private Integer volume;

    /**
     * 浏览次数
     */
    private Integer viewed;

    /**
     * 入库时间
     */
    @TableField("storageTime")
    private Date storageTime;

    /**
     * 是否上架
     */
    private Integer isShelf;

    /**
     * 上架时间
     */
    @TableField("shelfTime")
    private Date shelfTime;

    /**
     * 供应商编号
     */
    @TableField("providerId")
    private Integer providerId;

    /**
     * 商品类型名称
     */
    @TableField(exist = false)
    private String goodsTypeName;

    /**
     * 供货商名称
     */
    @TableField(exist = false)
    private String providername;

    public String getGoodsTypeName() {
        return goodsTypeName;
    }

    public void setGoodsTypeName(String goodsTypeName) {
        this.goodsTypeName = goodsTypeName;
    }

    public String getProvidername() {
        return providername;
    }

    public void setProvidername(String providername) {
        this.providername = providername;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    public Integer getSupply() {
        return supply;
    }

    public void setSupply(Integer supply) {
        this.supply = supply;
    }

    public Double getoPrice() {
        return oPrice;
    }

    public void setoPrice(Double oPrice) {
        this.oPrice = oPrice;
    }

    public Double getrPrice() {
        return rPrice;
    }

    public void setrPrice(Double rPrice) {
        this.rPrice = rPrice;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public Integer getViewed() {
        return viewed;
    }

    public void setViewed(Integer viewed) {
        this.viewed = viewed;
    }

    public Date getStorageTime() {
        return storageTime;
    }

    public void setStorageTime(Date storageTime) {
        this.storageTime = storageTime;
    }

    public Integer getIsShelf() {
        return isShelf;
    }

    public void setIsShelf(Integer isShelf) {
        this.isShelf = isShelf;
    }

    public Date getShelfTime() {
        return shelfTime;
    }

    public void setShelfTime(Date shelfTime) {
        this.shelfTime = shelfTime;
    }

    public Integer getProviderId() {
        return providerId;
    }

    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
    }

    @Override
    public String toString() {
        return "Goods{" +
        "id=" + id +
        ", typeIdOne=" + typeIdOne +
        ", typeIdTwo=" + typeIdTwo +
        ", typeId=" + typeId +
        ", goodsName=" + goodsName +
        ", introduction=" + introduction +
        ", goodsImg=" + goodsImg +
        ", inventory=" + inventory +
        ", supply=" + supply +
        ", oPrice=" + oPrice +
        ", rPrice=" + rPrice +
        ", volume=" + volume +
        ", viewed=" + viewed +
        ", storageTime=" + storageTime +
        ", isShelf=" + isShelf +
        ", shelfTime=" + shelfTime +
        ", providerId=" + providerId +
        "}";
    }
}
