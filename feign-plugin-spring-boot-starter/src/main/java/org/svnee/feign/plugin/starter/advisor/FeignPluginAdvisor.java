package org.svnee.feign.plugin.starter.advisor;

import java.lang.reflect.Method;
import org.aopalliance.aop.Advice;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.aop.support.StaticMethodMatcher;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.cloud.openfeign.ribbon.LoadBalancerFeignClient;

/**
 * dynamic set feign timeout for spring cloud feign!
 *
 * @author svnee
 */
public class FeignPluginAdvisor extends AbstractPointcutAdvisor implements BeanFactoryAware {

    private final transient Advice advice;
    private final transient Pointcut pointcut;

    private static final String EXECUTE_METHOD_NAME = "execute";

    public FeignPluginAdvisor(DynamicFeignTimeoutInterceptor interceptor) {
        this.advice = interceptor;
        this.pointcut = buildPointcut();
    }

    private Pointcut buildPointcut() {
        return new FullyQualifiedNameMethodPoint(LoadBalancerFeignClient.class, EXECUTE_METHOD_NAME);
    }

    private static class FullyQualifiedNameMethodPoint implements Pointcut {

        private final Class<?> parentClazz;
        private final String methodName;

        public FullyQualifiedNameMethodPoint(Class<?> parentClazz, String methodName) {
            this.parentClazz = parentClazz;
            this.methodName = methodName;
        }

        @Override
        public ClassFilter getClassFilter() {
            return parentClazz::isAssignableFrom;
        }

        @Override
        public MethodMatcher getMethodMatcher() {
            return new FullQualifiedNameMethodMatcher(methodName);
        }

        private static class FullQualifiedNameMethodMatcher extends StaticMethodMatcher {

            private final String methodName;

            public FullQualifiedNameMethodMatcher(String methodName) {
                this.methodName = methodName;
            }

            @Override
            public boolean matches(Method method, Class<?> targetClass) {
                return matchesMethod(method);
            }

            private boolean matchesMethod(Method method) {
                return method.getName().equals(methodName);
            }
        }
    }

    @Override
    public Pointcut getPointcut() {
        return pointcut;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        if (this.advice instanceof BeanFactoryAware) {
            ((BeanFactoryAware) this.advice).setBeanFactory(beanFactory);
        }
    }
}
