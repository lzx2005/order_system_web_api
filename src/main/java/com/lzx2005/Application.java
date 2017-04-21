package com.lzx2005;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lzx2005.dao.DishDao;
import com.lzx2005.dto.ServiceResult;
import com.lzx2005.entity.Dish;
import com.lzx2005.entity.Restaurant;
import com.lzx2005.entity.User;
import com.lzx2005.service.RestaurantService;
import com.lzx2005.tools.PointTools;
import com.lzx2005.tools.SUID;
import com.mongodb.client.model.geojson.Point;
import com.mongodb.client.model.geojson.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Lizhengxian on 2017/2/22.
 */

@SpringBootApplication(scanBasePackages = "com.lzx2005")
public class Application extends SpringBootServletInitializer implements CommandLineRunner{
    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    @Autowired
    private DishDao dishDao;

    @Autowired
    private RestaurantService restaurantService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }

    @Override
    public void run(String... strings) throws Exception {
        logger.info("测试日志系统是否可用");
        //初始化一些数据
        logger.info("测试Jenkins系统是否可用");

        double lng=106.538768;
        double lat=29.608937;
        double[] doubles = PointTools.bd09_To_Gcj02(lat, lng);
        List<Double> list = new ArrayList<>();
        list.add(doubles[0]);
        list.add(doubles[1]);
        Restaurant restaurant = new Restaurant();
        restaurant.setPosition(list);
        restaurant.setRestaurantName("XX餐厅");
        restaurant.setRestaurantId(SUID.getUUID());
        restaurant.setBelong(2);
        restaurant.setCreateTime(new Date());
        //ServiceResult restaurant1 = restaurantService.createRestaurant(restaurant);
        //System.out.println(restaurant1.toString());
    }

}
