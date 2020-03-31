package com.bdqn.mall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.bdqn.mall.dao")
public class FurnituremallApplication {

    public static void main(String[] args) {
        SpringApplication.run(FurnituremallApplication.class, args);
    }

}
