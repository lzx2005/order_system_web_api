package com.lzx2005.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Lizhengxian on 2017/4/12.
 */

@Entity
@Table(name = "dish_type")
public class DishType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int typeId;
    @Column
    private String typeName;
    @Column
    private int belong;
    @Column
    private Date createTime;

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getBelong() {
        return belong;
    }

    public void setBelong(int belong) {
        this.belong = belong;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
