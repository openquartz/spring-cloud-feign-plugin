package org.svnee.feign.plugin.starter.advisor;

import feign.Client;
import feign.Request;
import feign.Request.Options;
import java.net.URI;
import java.util.List;
import java.util.Objects;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.svnee.feign.plugin.starter.autoconfig.property.FeignTimeoutProperties;
import org.svnee.feign.plugin.starter.autoconfig.property.FeignTimeoutProperties.TimeoutProperty;
import org.svnee.feign.plugin.starter.constants.Constants;
import org.svnee.feign.plugin.starter.utils.CollectionUtils;
import org.svnee.feign.plugin.starter.utils.MapUtils;

/**
 * DynamicFeignTimeoutInterceptor
 *
 * @author svnee
 **/
public class DynamicFeignTimeoutInterceptor implements MethodInterceptor {

    private static final Logger log = LoggerFactory.getLogger(DynamicFeignTimeoutInterceptor.class);

    /**
     * feign args length
     *
     * {@link Client#execute(feign.Request, feign.Request.Options)}
     */
    private static final Integer FEIGN_ARGS_LEN = 2;

    /**
     * feign request args index
     *
     * {@link Client#execute(feign.Request, feign.Request.Options)}
     */
    private static final Integer FEIGN_REQUEST_ARGS_INDEX = 0;

    /**
     * feign options args index
     *
     * {@link Client#execute(feign.Request, feign.Request.Options)}
     */
    private static final Integer FEIGN_REQUEST_OPTION_ARGS_INDEX = 1;

    /**
     * timeout config
     */
    private final FeignTimeoutProperties properties;

    public DynamicFeignTimeoutInterceptor(FeignTimeoutProperties properties) {
        this.properties = properties;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {

        Object[] args = invocation.getArguments();
        if (MapUtils.isNotEmpty(this.properties.getConfig())) {
            try {
                Options options = null;
                if (args.length == FEIGN_ARGS_LEN) {
                    Request request = (Request) args[FEIGN_REQUEST_ARGS_INDEX];
                    URI uri = URI.create(request.url());
                    options = this.wrapperTimeoutOptions(this.properties.getHostConfig(uri.getHost()), uri);
                }

                if (options != null) {
                    args[FEIGN_REQUEST_OPTION_ARGS_INDEX] = options;
                }
            } catch (Exception ex) {
                log.error("[DynamicFeignTimeoutInterceptor#invoke]feign set timeout exception!", ex);
            }
        }
        return invocation.proceed();
    }

    /**
     * get timeout options
     *
     * @param propertyList timeout configs
     * @param uri uri
     * @return wrapper options
     */
    private Options wrapperTimeoutOptions(List<TimeoutProperty> propertyList, URI uri) {
        // support ip+host
        if (CollectionUtils.isEmpty(propertyList)) {
            return null;
        }
        // match property
        TimeoutProperty property = match(propertyList, uri.getPath());
        if (property == null) {
            return null;
        } else {
            if (property.getConnectTimeout() == null) {
                property.setConnectTimeout(property.getReadTimeout());
            }

            if (property.getReadTimeout() == null) {
                property.setReadTimeout(property.getConnectTimeout());
            }

            return new Options(property.getConnectTimeout(), property.getReadTimeout());
        }
    }

    /**
     * match rule
     * first match special path rule property,then match all rule property!
     *
     * @param propertyList Same host's property
     * @param path uri path
     * @return timeout property
     */
    private TimeoutProperty match(List<TimeoutProperty> propertyList, String path) {
        TimeoutProperty allMathPathTimeProperty = null;
        for (TimeoutProperty property : propertyList) {
            if (Objects.equals(property.getPath(), path)) {
                return property;
            }
            if (Objects.equals(Constants.ALL_MATCH, property.getPath())) {
                allMathPathTimeProperty = property;
            }
        }
        return allMathPathTimeProperty;
    }
}
