package com.lzx2005.repository;

import com.lzx2005.entity.Dish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;


/**
 * Created by Lizhengxian on 2017/3/3.
 */
@Repository
public interface DishRepository extends PagingAndSortingRepository<Dish,Long>,JpaSpecificationExecutor<Dish> {

    Dish findById(long id);

    Page<Dish> findByType(Pageable pageable,long type);

    Page<Dish> findAllByBelong(Pageable pageable,int belong);

    //@Query(value = "select d.id,d.name,d.price,d.belong,d.create_time,d.logo,dt.type_name from dish as d left join dish_type as dt on dt.type_id=d.type where d.belong = ?1",nativeQuery = true)
    //Page<HashMap<String,Object>> findAllByBelongUseQuery(Pageable pageable, int belong);


    //@Query(value = "select dish.id,dish.name,dish.price,dish.belong,dish.create_time,dish.logo,dish_type.type_name from dish left join dish_type on dish_type.type_id=dish.type where dish.belong = ?0",nativeQuery = true)
    //Page<HashMap<String,Object>> findAllByBelongUseQuery(int belong);
}
