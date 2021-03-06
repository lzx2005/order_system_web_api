package com.lzx2005.dao;

import com.alibaba.fastjson.JSONObject;
import com.lzx2005.entity.Dish;
import com.lzx2005.entity.Order;
import com.lzx2005.enums.ServiceResultEnum;
import com.mongodb.WriteResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lizhengxian on 2017/4/7.
 */
@Component
public class MongoOrderDao {
    private static final Logger logger = LoggerFactory.getLogger(MongoOrderDao.class);

    @Autowired
    private MongoTemplate mongoTemplate;

    @Deprecated
    public void createOrder(JSONObject jsonObject){
        mongoTemplate.insert(jsonObject,"order");
    }

    public void createOrder(Order order){
        mongoTemplate.insert(order,"order");
    }
    /**
     * 找到用户当前的订单
     * */
    public Order findActivityOrderByUserId(int userId){
        Criteria in = Criteria.where("userId").is(userId).and("status").is(0);
        Query query = Query.query(in);
        return mongoTemplate.findOne(query, Order.class, "order");
    }

    public int payOrder(int userId){
        Criteria in = Criteria.where("userId").is(userId).and("status").is(0);
        Query query = Query.query(in);

        Update update = Update.update("status",1);

        WriteResult order = mongoTemplate.upsert(query, update, "order");
        return order.getN();
    }

    /**
     * 根据订单ID找到订单
     * */
    public Order findOrderByOrderId(String orderId){
        Criteria criteria = Criteria.where("orderId").is(orderId);
        Query query = Query.query(criteria);
        return mongoTemplate.findOne(query, Order.class, "order");
    }

    public JSONObject addDishToOrder(String orderId, Dish dish){
        Criteria criteria = Criteria.where("orderId").is(orderId);
        Query query = Query.query(criteria);
        Update update = new Update();
        update.push("dishes",dish).inc("sum",1);
        WriteResult order = mongoTemplate.upsert(query, update, "order");
        logger.info("插入dish到order：result={},lastConcern={}",order,order.getLastConcern());
        return null;
    }

    public JSONObject removeDishFromOrder(String orderId,Dish dish){
        Criteria criteria = Criteria.where("orderId").is(orderId).and("dishes._id").is(dish.getId());
        Query query = Query.query(criteria);
        List<JSONObject> order1 = mongoTemplate.find(query, JSONObject.class, "order");
        if(order1.size()>0){
            Update update = new Update();
            update.pop("dishes", Update.Position.FIRST);
            logger.info("删除语句：update={}",update);
            WriteResult order = mongoTemplate.upsert(query, update, "order");
            logger.info("删除dish从order：result={},lastConcern={}",order,order.getLastConcern());
        }else{
            return ServiceResultEnum.DISH_HAS_NO_MORE.toJSONObject();
        }
        return null;
    }

    public JSONObject submitOrder(String orderId){
        Criteria criteria = Criteria.where("orderId").is(orderId);
        Query query = Query.query(criteria);
        Update update = new Update();
        update.set("orderStatus",3);
        WriteResult order = mongoTemplate.upsert(query, update, "order");
        logger.info("提交点菜：result={},lastConcern={}",order,order.getLastConcern());
        return null;
    }

    public JSONObject cookFinish(String orderId){
        Criteria criteria = Criteria.where("orderId").is(orderId);
        Query query = Query.query(criteria);
        Update update = new Update();
        update.set("orderStatus",4);
        WriteResult order = mongoTemplate.upsert(query, update, "order");
        logger.info("上菜完成：result={},lastConcern={}",order,order.getLastConcern());
        return null;
    }
}
