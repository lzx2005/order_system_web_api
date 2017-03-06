package com.lzx2005.repository;

import com.lzx2005.entity.Dish;
import org.springframework.data.repository.PagingAndSortingRepository;


/**
 * Created by Lizhengxian on 2017/3/3.
 */
public interface DishRepository extends PagingAndSortingRepository<Dish,String> {

    public Dish findById(long id);
}
