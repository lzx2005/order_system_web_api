package com.lzx2005.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lzx2005.dao.DishDao;
import com.lzx2005.dto.ServiceResult;
import com.lzx2005.entity.Dish;
import com.lzx2005.entity.DishType;
import com.lzx2005.enums.ServiceResultEnum;
import com.lzx2005.repository.DishRepository;
import com.lzx2005.repository.DishTypeRepository;
import com.lzx2005.service.MenuService;
import org.hibernate.jpa.criteria.expression.function.BasicFunctionExpression;
import org.hibernate.jpa.internal.metamodel.SingularAttributeImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Lizhengxian on 2017/3/6.
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private DishRepository dishRepository;

    @Autowired
    private DishTypeRepository dishTypeRepository;

    @Autowired
    private DishDao dishDao;

    @Override
    public ServiceResult createDish(String name, double price, long logo, long type, int belong,String belongRest) {
        Dish dish = new Dish();

        dish.setName(name);
        dish.setPrice(price);
        dish.setLogo(logo);
        dish.setType(type);
        dish.setBelong(belong);
        dish.setBelongRest(belongRest);
        dish.setCreateTime(new Date());

        Dish save = dishRepository.save(dish);

        if(save!=null){
            return ServiceResultEnum.SUCCESS.toServiceResult().setData(save);
        }
        return ServiceResultEnum.DB_ERROR.toServiceResult();
    }

    @Override
    public ServiceResult updateDish(long dishId, String name, double price, long logo, long type, int belong, String belongRest) {
        Dish dish = dishRepository.findOne(dishId);
        if(dish==null){
            return ServiceResultEnum.DISH_IS_NOT_EXIST.toServiceResult();
        }

        dish.setName(name);
        dish.setPrice(price);
        dish.setLogo(logo);
        dish.setType(type);
        dish.setBelong(belong);
        dish.setBelongRest(belongRest);
        dish.setCreateTime(new Date());

        Dish save = dishRepository.save(dish);

        if(save!=null){
            return ServiceResultEnum.SUCCESS.toServiceResult().setData(save);
        }
        return ServiceResultEnum.DB_ERROR.toServiceResult();
    }

    @Override
    public ServiceResult getDishById(long id) {
        Dish dish = dishRepository.findById(id);
        if(dish!=null){
            return ServiceResultEnum.SUCCESS.toServiceResult().setData(dish);
        }else{
            return ServiceResultEnum.DISH_IS_NOT_EXIST.toServiceResult();
        }
    }

    @Override
    public ServiceResult getDishAll(int page) {
        //分页查找
        Page all = dishRepository.findAll(new PageRequest(page, 10));
        if(all!=null){
            return ServiceResultEnum.SUCCESS.toServiceResult().setData(all);
        }
        return ServiceResultEnum.DB_ERROR.toServiceResult();
    }

    @Override
    public ServiceResult getDishAllByUserId(int page, int userId) {
        //Page<Dish> all = dishRepository.findAllByBelong(new PageRequest(page, 10), userId);
        page=page+1;
        PageHelper.startPage(page, 10);
        List<Map<String,Object>> list = dishDao.findByBelongLeftJoinDishType(userId);
        PageInfo pageInfo=new PageInfo(list);
        pageInfo.setPageNum(pageInfo.getPageNum()-1);
        return ServiceResultEnum.SUCCESS.toServiceResult().setData(pageInfo);
    }

    @Override
    public ServiceResult removeDish(long id) {
        dishRepository.delete(id);
        return ServiceResultEnum.SUCCESS.toServiceResult();
    }

    @Override
    public ServiceResult getDishesByType(int page,long type) {
        PageRequest pageRequest = new PageRequest(page, 10);
        Page dishes = dishRepository.findByType(pageRequest, type);
        if(dishes!=null){
            return ServiceResultEnum.SUCCESS.toServiceResult().setData(dishes);
        }
        return ServiceResultEnum.DISH_IS_NOT_EXIST.toServiceResult();
    }

    @Override
    public ServiceResult createDishType(String typeName, int belong) {

        DishType dishType = new DishType();
        dishType.setBelong(belong);
        dishType.setTypeName(typeName);
        DishType save = dishTypeRepository.save(dishType);
        if(save!=null){
            return ServiceResultEnum.SUCCESS.toServiceResult().setData(save);
        }
        return ServiceResultEnum.DB_ERROR.toServiceResult();
    }

    @Override
    public ServiceResult removeDishType(int typeId,int userId) {
        DishType one = dishTypeRepository.findOne(typeId);
        if(one.getBelong()==userId){
            dishTypeRepository.delete(one);
            return ServiceResultEnum.SUCCESS.toServiceResult();
        }else{
            return ServiceResultEnum.CANT_DELETE_NOT_BELONG_YOU_TYPE.toServiceResult();
        }
    }

    @Override
    public ServiceResult getAllDishTypeByUserId(int page,int userId) {
        Page<DishType> byBelong = dishTypeRepository.findByBelong(new PageRequest(page,10),userId);
        if(byBelong!=null){
            return ServiceResultEnum.SUCCESS.toServiceResult().setData(byBelong);
        }
        return ServiceResultEnum.DB_ERROR.toServiceResult();
    }

    @Override
    public ServiceResult getAllDishTypeByUserId(int userId) {
        List<DishType> byBelong = dishTypeRepository.findByBelong(userId);
        if(byBelong!=null){
            return ServiceResultEnum.SUCCESS.toServiceResult().setData(byBelong);
        }
        return ServiceResultEnum.DB_ERROR.toServiceResult();
    }
}
