package org.svnee.feign.plugin.starter.autoconfig;

import feign.Feign;
import org.springframework.aop.Advisor;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.ribbon.LoadBalancerFeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;
import org.svnee.feign.plugin.starter.advisor.DynamicFeignTimeoutInterceptor;
import org.svnee.feign.plugin.starter.advisor.FeignPluginAdvisor;
import org.svnee.feign.plugin.starter.autoconfig.property.FeignPluginEnableProperties;
import org.svnee.feign.plugin.starter.autoconfig.property.FeignTimeoutProperties;

/**
 * FeignPluginAutoConfiguration
 *
 * @author svnee
 **/
@Configuration
@EnableConfigurationProperties({FeignTimeoutProperties.class, FeignPluginEnableProperties.class})
@ConditionalOnClass({LoadBalancerFeignClient.class, Feign.class})
@ConditionalOnProperty(prefix = FeignPluginEnableProperties.PREFIX, value = "enable", matchIfMissing = true)
public class FeignPluginAutoConfiguration {

    @Bean
    @Role(value = BeanDefinition.ROLE_INFRASTRUCTURE)
    public Advisor fileExportExecutorAnnotationAdvisor(FeignTimeoutProperties feignTimeoutProperties,
        FeignPluginEnableProperties feignPluginEnableProperties
    ) {
        DynamicFeignTimeoutInterceptor interceptor = new DynamicFeignTimeoutInterceptor(feignTimeoutProperties);
        FeignPluginAdvisor advisor = new FeignPluginAdvisor(interceptor);
        advisor.setOrder(feignPluginEnableProperties.getAdvisorOrder());
        return advisor;
    }
}
