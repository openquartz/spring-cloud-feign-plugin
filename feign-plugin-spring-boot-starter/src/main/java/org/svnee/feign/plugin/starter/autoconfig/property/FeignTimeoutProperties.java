package org.svnee.feign.plugin.starter.autoconfig.property;

import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * feign timeout property,support spring cloud config refresh!
 * set property
 * <p>
 *     feign.client.config.{appId}.connect-timeout = 1000
 *     feign.client.config.{appId}.read-timeout = 1000
 * </p>
 * @author svnee
 */
@RefreshScope
@ConfigurationProperties(prefix = FeignTimeoutProperties.PREFIX)
public class FeignTimeoutProperties {

    public static final String PREFIX = "feign.client";

    private Map<String, TimeoutProperty> config = new TreeMap<>();

    public Map<String, TimeoutProperty> getConfig() {
        return this.config;
    }

    public void setConfig(final Map<String, TimeoutProperty> config) {
        this.config = config;
    }

    public FeignTimeoutProperties(final Map<String, TimeoutProperty> config) {
        this.config = config;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FeignTimeoutProperties that = (FeignTimeoutProperties) o;
        return Objects.equals(config, that.config);
    }

    @Override
    public int hashCode() {
        return Objects.hash(config);
    }

    @Override
    public String toString() {
        return "FeignTimeoutProperties{" +
            "config=" + config +
            '}';
    }

    /**
     * base template timeout property
     *
     * @author svnee
     */
    public static class TimeoutProperty {

        /**
         * connect timeout
         */
        private Integer connectTimeout;

        /**
         * read timeout
         */
        private Integer readTimeout;

        public Integer getConnectTimeout() {
            return connectTimeout;
        }

        public void setConnectTimeout(Integer connectTimeout) {
            this.connectTimeout = connectTimeout;
        }

        public Integer getReadTimeout() {
            return readTimeout;
        }

        public void setReadTimeout(Integer readTimeout) {
            this.readTimeout = readTimeout;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            TimeoutProperty that = (TimeoutProperty) o;
            return Objects.equals(connectTimeout, that.connectTimeout) && Objects
                .equals(readTimeout, that.readTimeout);
        }

        @Override
        public int hashCode() {
            return Objects.hash(connectTimeout, readTimeout);
        }

        @Override
        public String toString() {
            return "TimeoutProperty{" +
                "connectTimeout=" + connectTimeout +
                ", readTimeout=" + readTimeout +
                '}';
        }
    }
}
