package com.lzx2005.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Lizhengxian on 2017/4/7.
 */
@Entity
@Table(name = "order")
public class Order {

    @Id
    private long orderId;
    private int orderBelong;
    private short orderStatus;
    private Date createTime;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public int getOrderBelong() {
        return orderBelong;
    }

    public void setOrderBelong(int orderBelong) {
        this.orderBelong = orderBelong;
    }

    public short getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(short orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
