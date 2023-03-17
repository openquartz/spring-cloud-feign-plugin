package org.svnee.feign.plugin.starter.autoconfig.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * FeignPluginEnableProperties
 *
 * @author svnee
 **/
@ConfigurationProperties(prefix = FeignPluginEnableProperties.PREFIX)
public class FeignPluginEnableProperties {

    public static final String PREFIX = "feign.plugin";

    /**
     * enable feign plugin
     */
    private boolean enable = true;

    /**
     * advisor order
     */
    private Integer advisorOrder = Integer.MAX_VALUE;

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public Integer getAdvisorOrder() {
        return advisorOrder;
    }

    public void setAdvisorOrder(Integer advisorOrder) {
        this.advisorOrder = advisorOrder;
    }
}
