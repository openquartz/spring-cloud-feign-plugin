package com.openquartz.feign.plugin.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * FeignPluginExampleStarter
 *
 * @author svnee
 **/
@EnableFeignClients
@SpringBootApplication
public class FeignPluginExampleStarter {

    public static void main(String[] args) {
        SpringApplication.run(FeignPluginExampleStarter.class);
    }

}
