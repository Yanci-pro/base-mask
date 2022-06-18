package com.yanci;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan("com.yanci.bean")
@EnableJpaRepositories(basePackages = "com.yanci.dao")
@SpringBootApplication
public class BaseMaskJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaseMaskJpaApplication.class, args);
    }

}
