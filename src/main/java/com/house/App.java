package com.house;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * @author zhuangyq
 * @create 2018-04-23 上午 9:56
 **/
@SpringBootApplication
public class App{
//public class App extends SpringBootServletInitializer {
//
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        return builder.sources(App.class);
//    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
