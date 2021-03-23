# 简介

SpringCloud Config是微服务架构中的微服务提供集中化的外部配置支持，配置服务器为各个不同微服务应用的所有环境提供了一个中心化的外部配置

# 服务端配置

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

# 客户端配置

1. 为了防止本地配置覆盖，首先要将配置文件改为`bootstrap.yml`，它具有更高的优先级

2. 配置文件

   ```yaml
   spring:
     application:
       name: cloud-config-client
     cloud:
       config:
         name: config # 配置文件名称
         label: master # 分支名称
         profile: dev # 读取后缀名称
         uri: http://localhost:3344 # 配置中心地址
   ```

3. 