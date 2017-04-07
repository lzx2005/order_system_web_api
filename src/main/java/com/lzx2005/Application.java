package com.lzx2005;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by Lizhengxian on 2017/2/22.
 */

@SpringBootApplication(scanBasePackages = "com.lzx2005")
public class Application implements CommandLineRunner{
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }

    @Override
    public void run(String... strings) throws Exception {
        //初始化一些数据
    }
}
