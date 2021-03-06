package com.lzx2005.dao;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.lzx2005.entity.Restaurant;
import com.mongodb.WriteResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.geo.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.Sphere;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeospatialIndex;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.NearQuery;
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
        position.typed(GeoSpatialIndexType.GEO_2D);
        mongoTemplate.indexOps(this.collectionName).ensureIndex(position);
    }

    public int deleteRestaurant(String restaurantId){

        Criteria criteria = Criteria.where("restaurantId").is(restaurantId);
        Query query = new Query(criteria);
        WriteResult result = mongoTemplate.remove(query, this.collectionName);
        int n = result.getN();
        if(n>0){
            logger.info("删除餐厅"+restaurantId+"成功，删除了"+n+"条记录");
        }else{
            logger.info("删除餐厅"+restaurantId+"失败");
        }
        return n;
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

    public GeoResults<Restaurant> getNearRestaurant(double lng ,double lat,double length){
        NearQuery nearQuery = NearQuery.near(lng, lat, Metrics.KILOMETERS).maxDistance(length);
        GeoResults<Restaurant> geoResults = mongoTemplate.geoNear(nearQuery, Restaurant.class, this.collectionName);
        return geoResults;
    }

    public Restaurant findByRestaurantId(String restaurantId){
        Criteria criteria = Criteria.where("restaurantId").is(restaurantId);
        Query query = new Query(criteria);
        return mongoTemplate.findOne(query, Restaurant.class, this.collectionName);
    }

    public Restaurant findByRestaurantIdAndUserId(String restaurantId,int userId){
        Criteria criteria = Criteria.where("restaurantId").is(restaurantId).and("belong").is(userId);
        Query query = new Query(criteria);
        return mongoTemplate.findOne(query, Restaurant.class, this.collectionName);
    }

}
