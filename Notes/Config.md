# 简介

SpringCloud Config是微服务架构中的微服务提供集中化的外部配置支持，配置服务器为各个不同微服务应用的所有环境提供了一个中心化的外部配置

# 配置

与github联动

```yaml
server:
  port: 3344
spring:
  application:
    name: cloud-config-center
  cloud:
    config:
      server:
        git:
          uri: https://github.com/andochiwa/SpringCloud
          # 搜索目录
          search-paths:
            - SpringCloud.config
      label: master
```

主启动类加上`@EnableConfigServer`注解