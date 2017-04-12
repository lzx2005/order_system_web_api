package com.lzx2005.repository;

import com.lzx2005.entity.DishType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Lizhengxian on 2017/4/12.
 */
@Repository
public interface DishTypeRepository extends PagingAndSortingRepository<DishType,Integer>{

    Page<DishType> findByBelong(Pageable pageable, int belong);
}
