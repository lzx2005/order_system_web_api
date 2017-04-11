package com.lzx2005.repository;

import com.lzx2005.entity.Dish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


/**
 * Created by Lizhengxian on 2017/3/3.
 */
@Repository
public interface DishRepository extends PagingAndSortingRepository<Dish,Long> {

    Dish findById(long id);

    Page<Dish> findByType(Pageable pageable,long type);

    Page<Dish> findAllByBelong(Pageable pageable,int belong);
}
