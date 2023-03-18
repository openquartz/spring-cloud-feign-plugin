package org.svnee.feign.plugin.starter.autoconfig.property;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.stream.Collectors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * feign timeout property,support spring cloud config refresh!
 * set property
 * <p>
 * feign.plugin.client.config.{config-alias}.host = {host}
 * feign.plugin.client.config.{config-alias}.path = *
 * feign.plugin.client.config.{config-alias}.connect-timeout = 1000
 * feign.plugin.client.config.{config-alias}.read-timeout = 1000
 * </p>
 *
 * @author svnee
 */
@RefreshScope
@ConfigurationProperties(prefix = FeignTimeoutProperties.PREFIX)
public class FeignTimeoutProperties {

    public static final String PREFIX = "feign.plugin.client";

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

    /**
     * get host config
     *
     * @param host host
     * @return timeout property
     */
    public List<TimeoutProperty> getHostConfig(String host) {
        return config.values().stream().filter(e -> e.getHost().equals(host)).collect(Collectors.toList());
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
         * host
         */
        private String host;

        /**
         * match default all
         */
        private String path = "*";

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

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
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
            return Objects.equals(host, that.host) && Objects.equals(path, that.path) && Objects
                .equals(connectTimeout, that.connectTimeout) && Objects.equals(readTimeout, that.readTimeout);
        }

        @Override
        public int hashCode() {
            return Objects.hash(host, path, connectTimeout, readTimeout);
        }
    }
}
