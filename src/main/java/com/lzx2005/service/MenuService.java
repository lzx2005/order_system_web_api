package com.lzx2005.service;

import com.lzx2005.dto.ServiceResult;
import com.lzx2005.entity.Dish;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by Lizhengxian on 2017/3/3.
 */
public interface MenuService {

    ServiceResult createDish(String name,double price,long image,long type,int belong,String belongRest);

    ServiceResult getDishById(long id);

    ServiceResult getDishAll(int page);

    ServiceResult getDishAllByUserId(int page,int userId);

    ServiceResult removeDish(long id);

    ServiceResult getDishesByType(int page,long type);

    ServiceResult createDishType(String typeName,int belong);

    ServiceResult removeDishType(int typeId,int userId);

    ServiceResult getAllDishTypeByUserId(int page,int userId);

    ServiceResult getAllDishTypeByUserId(int userId);
}
