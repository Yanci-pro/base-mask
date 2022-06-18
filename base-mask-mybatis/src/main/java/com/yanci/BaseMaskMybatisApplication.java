package com.yanci;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.yanci.dao")
@SpringBootApplication
public class BaseMaskMybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaseMaskMybatisApplication.class, args);
    }

}
