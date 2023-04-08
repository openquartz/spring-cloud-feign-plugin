package com.openquartz.feign.plugin.example.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * server one feign
 * @author svnee
 */
@FeignClient(name = "server-one",url = "localhost:8080")
public interface ServerOneFeign {

    /**
     * test hello method
     */
    @GetMapping("/event/sync")
    void sayHello();

}
