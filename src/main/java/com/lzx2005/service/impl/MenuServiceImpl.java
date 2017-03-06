package com.lzx2005.service.impl;

import com.lzx2005.dto.ServiceResult;
import com.lzx2005.entity.Dish;
import com.lzx2005.repository.DishRepository;
import com.lzx2005.service.MenuService;
import com.lzx2005.enums.ServiceStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Lizhengxian on 2017/3/6.
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    DishRepository dishRepository;

    @Override
    public ServiceResult<Dish> createDish(String name, double price, long logo, long type, long belong) {
        Dish dish = new Dish();

        dish.setName(name);
        dish.setPrice(price);
        dish.setLog(logo);
        dish.setType(type);
        dish.setBelong(belong);
        dish.setCreateTime(new Date());

        Dish save = dishRepository.save(dish);
        System.out.println(save);
        return new ServiceResult<Dish>()
                .setCode(ServiceStatus.SUCCESS.getCode())
                .setMsg(ServiceStatus.SUCCESS.getMsg());
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
    public ServiceResult<Page<Dish>> getDishAll(int page) {
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
        dishRepository.delete(id);
        return new ServiceResult<Dish>()
                .setCode(ServiceStatus.DELETE_SUCCESS.getCode())
                .setMsg(ServiceStatus.DELETE_SUCCESS.getMsg());
    }

    @Override
    public ServiceResult<Page<Dish>> getDishesByType(int page,long type) {

        PageRequest pageRequest = new PageRequest(page, 10);
        Page<Dish> dishes = dishRepository.findByType(pageRequest, type);

        if(dishes!=null){
            return new ServiceResult<Page<Dish>>()
                    .setCode(ServiceStatus.SUCCESS.getCode())
                    .setMsg(ServiceStatus.SUCCESS.getMsg())
                    .setData(dishes);
        }

        return new ServiceResult<Page<Dish>>()
                .setCode(ServiceStatus.DISH_IS_NOT_EXIST.getCode())
                .setMsg(ServiceStatus.DISH_IS_NOT_EXIST.getMsg());
    }
}
