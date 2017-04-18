package com.lzx2005;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lzx2005.dao.DishDao;
import com.lzx2005.entity.Dish;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

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

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }

    @Override
    public void run(String... strings) throws Exception {
        logger.info("测试日志系统是否可用");
        //初始化一些数据


        PageHelper.startPage(1, 10);
        List<Map<String,Object>> list = dishDao.findByBelongLeftJoinDishType(2);
        PageInfo pageInfo=new PageInfo(list);

        System.out.println(pageInfo);
    }

}
