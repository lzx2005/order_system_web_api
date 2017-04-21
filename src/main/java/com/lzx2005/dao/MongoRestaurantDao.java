package com.lzx2005.dao;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
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

    public void insertRestaurant(JSONObject restaurant){
        mongoTemplate.insert(restaurant,this.collectionName);
    }

    public List<JSONObject> getAllMyRestaurant(int userId){
        Criteria criteria = Criteria.where("userId").is(userId);
        Query query = new Query(criteria);
        List<JSONObject> restaurant = mongoTemplate.find(query, JSONObject.class, this.collectionName);
        return restaurant;
    }


    public PageInfo<JSONObject> getAllMyRestaurantByPage(int userId,int page){
        Criteria criteria = Criteria.where("userId").is(userId);
        Query query = new Query(criteria);
        query.with(new PageRequest(page,10));

        //查询
        long count = mongoTemplate.count(query, this.collectionName);
        List<JSONObject> jsonObjects = mongoTemplate.find(query, JSONObject.class, this.collectionName);

        //计算分页
        Page<JSONObject> pageResult = new Page<>(page,10);
        pageResult.setTotal(count);

        int pages = (int)count/10;
        pageResult.setPages(pages);
        PageInfo<JSONObject> pageInfo = pageResult.toPageInfo();
        pageInfo.setList(jsonObjects);
        return pageInfo;
    }

}