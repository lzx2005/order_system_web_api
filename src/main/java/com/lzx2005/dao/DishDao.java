package com.lzx2005.dao;

import com.lzx2005.entity.Dish;

import java.util.List;
import java.util.Map;

/**
 * Created by Lizhengxian on 2017/4/18.
 */
public interface DishDao {
    Dish findById(long id);

    List<Map<String,Object>> findByBelongLeftJoinDishType(int belong);
}
