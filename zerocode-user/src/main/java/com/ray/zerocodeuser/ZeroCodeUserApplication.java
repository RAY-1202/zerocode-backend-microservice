package com.ray.zerocodeuser;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.ray.zerocodeuser.mapper")
@EnableDubbo
@ComponentScan("com.ray")
public class ZeroCodeUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZeroCodeUserApplication.class, args);
    }
}
