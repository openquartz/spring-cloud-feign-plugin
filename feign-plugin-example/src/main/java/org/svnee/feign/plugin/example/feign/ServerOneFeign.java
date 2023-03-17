package org.svnee.feign.plugin.example.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * server one feign
 * @author svnee
 */
@FeignClient(name = "server-one")
public interface ServerOneFeign {

    /**
     * test hello method
     */
    @GetMapping("/hello")
    void sayHello();

}
