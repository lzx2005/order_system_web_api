package com.lzx2005.repository;

import com.lzx2005.entity.Dish;
import org.springframework.data.repository.CrudRepository;


/**
 * Created by Lizhengxian on 2017/3/3.
 */
public interface DishRepository extends CrudRepository<Dish,String> {
}
