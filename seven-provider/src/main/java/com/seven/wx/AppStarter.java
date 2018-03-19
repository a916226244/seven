package com.seven.wx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by xiaozhiping on 17/2/16.
 */
@SpringBootApplication
@ImportResource(locations = {"classpath:mybatis-config.xml" })
@ComponentScan
public class AppStarter{
    public static void main(String[] args) {
        SpringApplication.run(AppStarter.class, args);
    }

}
