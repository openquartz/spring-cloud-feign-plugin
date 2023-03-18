package org.svnee.feign.plugin.example.controller;

import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.svnee.feign.plugin.example.feign.ServerOneFeign;

/**
 * TestController
 * @author svnee
 **/
@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    ServerOneFeign serverOneFeign;

    @GetMapping("/get")
    public void hello(){
        serverOneFeign.sayHello();
    }

}
