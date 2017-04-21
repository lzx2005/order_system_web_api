package com.lzx2005.entity;

import com.alibaba.fastjson.JSON;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Lizhengxian on 2017/3/3.
 */
@Entity
@Table(name = "dish")
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String name;

    @Column
    private double price;

    @Column
    private int belong;

    @Column
    private String belongRest;

    @Column
    private long logo;

    @Column
    private long type;

    @Column
    private Date createTime;

    public String getBelongRest() {
        return belongRest;
    }

    public void setBelongRest(String belongRest) {
        this.belongRest = belongRest;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getBelong() {
        return belong;
    }

    public void setBelong(int belong) {
        this.belong = belong;
    }


    public long getLogo() {
        return logo;
    }

    public void setLogo(long logo) {
        this.logo = logo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public long getType() {
        return type;
    }

    public void setType(long type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
