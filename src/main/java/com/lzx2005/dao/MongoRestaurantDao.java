package com.lzx2005.dao;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.lzx2005.entity.Restaurant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeospatialIndex;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Lizhengxian on 2017/4/21.
 */
@Component
public class MongoRestaurantDao {
    private static final Logger logger = LoggerFactory.getLogger(MongoOrderDao.class);

    @Autowired
    private MongoTemplate mongoTemplate;

    private String collectionName="restaurant";

    public void insertRestaurant(Restaurant restaurant){
        mongoTemplate.insert(restaurant,this.collectionName);
        GeospatialIndex position = new GeospatialIndex("position");
        //position.typed(GeoSpatialIndexType.GEO_2DSPHERE);
        mongoTemplate.indexOps(this.collectionName).ensureIndex(position);
    }

    public List<Restaurant> getAllMyRestaurant(int userId){
        Criteria criteria = Criteria.where("belong").is(userId);
        Query query = new Query(criteria);
        return mongoTemplate.find(query, Restaurant.class, this.collectionName);
    }


    public PageInfo<Restaurant> getAllMyRestaurantByPage(int userId,int page){
        Criteria criteria = Criteria.where("belong").is(userId);
        Query query = new Query(criteria);
        query.with(new PageRequest(page,10));

        //查询
        long count = mongoTemplate.count(query, this.collectionName);
        List<Restaurant> jsonObjects = mongoTemplate.find(query, Restaurant.class, this.collectionName);

        //计算分页
        Page<Restaurant> pageResult = new Page<>(page,10);
        pageResult.setTotal(count);

        int pages = (int)count/10;
        pageResult.setPages(pages);
        PageInfo<Restaurant> pageInfo = pageResult.toPageInfo();
        pageInfo.setList(jsonObjects);
        return pageInfo;
    }

}
