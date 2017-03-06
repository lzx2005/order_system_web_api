package com.lzx2005.service;

import com.lzx2005.dto.ServiceResult;
import com.lzx2005.entity.Dish;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by Lizhengxian on 2017/3/3.
 */
public interface MenuService {

    public ServiceResult<Dish> createDish(String name,double price,long image,long type,long belong);


    public ServiceResult<Dish> getDishById(long id);


    public ServiceResult<Page<Dish>> getDishes(int page);


    public ServiceResult<Dish> removeDish(long id);


    public ServiceResult<List<Dish>> getDishesByType(int page);
}
