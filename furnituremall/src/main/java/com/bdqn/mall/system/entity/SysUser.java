package com.bdqn.mall.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 怪盗一只羊
 * @since 2020-04-13
 */
public class SysUser implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 用户编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名称
     */
    @TableField("userNamee")
    private String userNamee;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 加密的盐值
     */
    private String salt;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 用户性别
     */
    private String sex;

    /**
     * 用户头像路径
     */
    @TableField("imageName")
    private String imageName;

    /**
     * 用户等级
     */
    private Integer level;

    /**
     * 注册时间
     */
    @TableField("createTime")
    private LocalDate createTime;

    /**
     * 邮箱
     */
    private String email;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserNamee() {
        return userNamee;
    }

    public void setUserNamee(String userNamee) {
        this.userNamee = userNamee;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public LocalDate getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDate createTime) {
        this.createTime = createTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "SysUser{" +
        "id=" + id +
        ", userNamee=" + userNamee +
        ", password=" + password +
        ", salt=" + salt +
        ", phone=" + phone +
        ", sex=" + sex +
        ", imageName=" + imageName +
        ", level=" + level +
        ", createTime=" + createTime +
        ", email=" + email +
        "}";
    }
}
