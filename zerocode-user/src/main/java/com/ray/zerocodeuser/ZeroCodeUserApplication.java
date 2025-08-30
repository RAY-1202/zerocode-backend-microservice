package com.ray.zerocodeuser;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.ray.zerocode.mapper")
@ComponentScan("com.ray")
public class ZeroCodeUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZeroCodeUserApplication.class, args);
    }
}
