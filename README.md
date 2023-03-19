# spring-cloud-feign-plugin

## 欢迎Star (Welcome Star)!!!
**[主页](https://svnlab.github.io/)** \
**[Github](https://github.com/svnlab/spring-cloud-feign-plugin) [Gitee](https://gitee.com/svnee/spring-cloud-feign-plugin)**


### 解决问题

使用`Feign`时出现对方服务设置超时时间不合理,需要重新改配置并发布服务,影响很大。同时不同时间可能由于业务的不同RT也不同。有些业务需要动态的调整时间。\
但是`Spring-Cloud-OpenFeign` 不支持 动态调整。此插件主要是针对`Spring-Cloud-OpenFeign`的做的一个插件式的增强。

### 特性
支持`SpringCloud-Config`、`Spring-Cloud-Nacos`等的基于`org.springframework.cloud.context.config.annotation.RefreshScope`动态配置发布刷新

#### 1、支持服务级别的设置超时时间
设置配置

`feign.plugin.client.config.{config-alias}.host = {host}`、\
`feign.plugin.client.config.{config-alias}.path = *`、\
`feign.plugin.client.config.{config-alias}.connect-timeout = {connect-timeout}`、\
`feign.plugin.client.config.{config-alias}.read-timeout = {read-timeout}`

例如：

```properties
feign.plugin.client.config.server-one-all.host=server-one
feign.plugin.client.config.server-one-all.path=*
feign.plugin.client.config.server-one-all.read-timeout=100000
feign.plugin.client.config.server-one-all.connect-timeout=100000
```

#### 2、支持指定接口级别的超时时间

设置配置

`feign.plugin.client.config.{config-alias}.host = {host}`、\
`feign.plugin.client.config.{config-alias}.path = {url-path}`、\
`feign.plugin.client.config.{config-alias}.connect-timeout = {connect-timeout}`、\
`feign.plugin.client.config.{config-alias}.read-timeout = {read-timeout}`

例如：

```properties
feign.plugin.client.config.server-one-say-hello.host=server-one
feign.plugin.client.config.server-one-say-hello.path=/hello
feign.plugin.client.config.server-one-say-hello.read-timeout=100000
feign.plugin.client.config.server-one-say-hello.connect-timeout=100000
```

`接口级别`的配置优先级大于`服务级别`的配置,如果先匹配到`接口级别`的配置以`接口级别`为准,其次查询匹配所有的`服务级别`的兜底配置。

### 快速开始

#### 1、maven 坐标

```xml

<dependency>
    <groupId>org.svnee</groupId>
    <artifactId>feign-plugin-spring-boot-starter</artifactId>
    <version>1.0.0</version>
</dependency>
```

#### 2、配置
##### 公用配置
如果需要禁用plugin 可以设置

```properties
feign.plugin.enable=true
```

如果需要设置plugin 切面顺序。可以在配置中指定

```properties
feign.plugin.advisor-order=1000
```

