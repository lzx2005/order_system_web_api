package com.lzx2005.service;

import com.lzx2005.dto.ServiceResult;
import com.lzx2005.entity.Dish;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by Lizhengxian on 2017/3/3.
 */
public interface MenuService {

    public ServiceResult createDish(String name,double price,long image,long type,long belong);


    public ServiceResult getDishById(long id);


    public ServiceResult getDishAll(int page);


    public ServiceResult removeDish(long id);


    public ServiceResult getDishesByType(int page,long type);
}
