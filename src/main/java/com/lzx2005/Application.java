package com.lzx2005;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by Lizhengxian on 2017/2/22.
 */

@SpringBootApplication(scanBasePackages = "com.lzx2005")
public class Application implements CommandLineRunner{
    private static final Logger logger = LoggerFactory.getLogger(Application.class);


    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }

    @Override
    public void run(String... strings) throws Exception {
        logger.info("测试日志系统是否可用");
        //初始化一些数据
    }
}
