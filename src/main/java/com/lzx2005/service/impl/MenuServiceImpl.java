package com.lzx2005.service.impl;

import com.lzx2005.dto.ServiceResult;
import com.lzx2005.entity.Dish;
import com.lzx2005.repository.DishRepository;
import com.lzx2005.service.MenuService;
import com.lzx2005.enums.ServiceStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by Lizhengxian on 2017/3/6.
 */
public class MenuServiceImpl implements MenuService {

    @Autowired
    DishRepository dishRepository;

    @Override
    public ServiceResult<Dish> createDish(String name, double price, long image, long type, long belong) {
        Dish dish = new Dish();

        dish.setName(name);
        dish.setPrice(price);
        dish.setImage(image);
        dish.setType(type);
        dish.setBelong(belong);

        Dish save = dishRepository.save(dish);
        if(save.getId()>0){
            return new ServiceResult<Dish>()
                    .setCode(ServiceStatus.SUCCESS.getCode())
                    .setMsg(ServiceStatus.SUCCESS.getMsg());
        }else{
            return new ServiceResult<Dish>()
                    .setCode(ServiceStatus.DB_ERROR.getCode())
                    .setMsg(ServiceStatus.DB_ERROR.getMsg());
        }
    }

    @Override
    public ServiceResult<Dish> getDishById(long id) {
        Dish dish = dishRepository.findById(id);
        if(dish!=null){
            return new ServiceResult<Dish>()
                    .setCode(ServiceStatus.SUCCESS.getCode())
                    .setMsg(ServiceStatus.SUCCESS.getMsg())
                    .setData(dish);
        }else{
            return new ServiceResult<Dish>()
                    .setCode(ServiceStatus.DISH_IS_NOT_EXIST.getCode())
                    .setMsg(ServiceStatus.DISH_IS_NOT_EXIST.getMsg());
        }
    }

    @Override
    public ServiceResult<Page<Dish>> getDishes(int page) {
        //分页查找
        Page<Dish> all = dishRepository.findAll(new PageRequest(page, 10));

        if(all!=null){
            return new ServiceResult<Page<Dish>>()
                    .setCode(ServiceStatus.SUCCESS.getCode())
                    .setMsg(ServiceStatus.SUCCESS.getMsg())
                    .setData(all);
        }
        return new ServiceResult<Page<Dish>>()
                .setCode(ServiceStatus.DB_ERROR.getCode())
                .setMsg(ServiceStatus.DB_ERROR.getMsg());
    }

    @Override
    public ServiceResult<Dish> removeDish(long id) {
        return null;
    }

    @Override
    public ServiceResult<List<Dish>> getDishesByType(int page) {
        return null;
    }
}
