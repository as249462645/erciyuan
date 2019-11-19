package com.heyi.erciyuan.erciyuan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@MapperScan("com.heyi.erciyuan.erciyuan.mapper")
@EnableConfigurationProperties
@SpringBootApplication
public class ErciyuanApplication {

    public static void main(String[] args) {
        SpringApplication.run(ErciyuanApplication.class, args);
    }

}
