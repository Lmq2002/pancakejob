package com.jbgz.pancakejob;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.jbgz.pancakejob.mapper")
public class PancakejobApplication {

    public static void main(String[] args) {
        SpringApplication.run(PancakejobApplication.class, args);
    }

}
