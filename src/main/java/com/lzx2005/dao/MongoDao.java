package com.lzx2005.dao;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lizhengxian on 2017/4/7.
 */
@Component
public class MongoDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void createOrder(JSONObject jsonObject){
        mongoTemplate.insert(jsonObject,"order");
    }

    /**
     * 找到用户当前的订单
     * */
    public List<JSONObject> findActivityOrderByUserId(String userId){
        ArrayList<Integer> activityStatus = new ArrayList<>();
        activityStatus.add(1);
        activityStatus.add(2);
        activityStatus.add(3);
        activityStatus.add(4);
        Criteria in = Criteria.where("userId").is(userId).and("orderStatus").in(activityStatus);
        Query query = Query.query(in);
        return mongoTemplate.find(query, JSONObject.class, "order");
    }


    /**
     * 根据订单ID找到订单
     * */
    public JSONObject findOrderByOrderId(String orderId){
        Criteria criteria = Criteria.where("orderId").is(orderId);
        Query query = Query.query(criteria);
        return mongoTemplate.findOne(query, JSONObject.class, "order");
    }


}
