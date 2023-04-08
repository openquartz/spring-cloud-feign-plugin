package com.openquartz.feign.plugin.example.controller;

import com.openquartz.feign.plugin.example.feign.ServerOneFeign;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TestController
 *
 * @author svnee
 **/
@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    ServerOneFeign serverOneFeign;

    @GetMapping("/get")
    public void hello() {

        long startTime = System.currentTimeMillis();
        try {
            serverOneFeign.sayHello();
        } finally {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+(System.currentTimeMillis() - startTime));
        }
    }

}
