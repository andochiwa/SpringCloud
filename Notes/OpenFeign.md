# 简介

Feign是一个声明式WebService客户端，使用Feign能让编写Web Service客户端更简单

它的使用方法是定义一个服务接口，在上面添加注解。Feign也支持可拔插式的编码器和解码器。

Spring Cloud对Feign(OpenFeign)进行了封装，使其支持了SpringMVC标准注解和HttpMessageConverters。Feign也可以与Eureka和Ribbon组合使用以支持负载均衡。



# 作用

Feign旨在使编写Java Http客户端变的更容易。

在使用RestTemplate时，利用RestTemplate对http请求的封装处理，形成了一套模板化的调用方法。但实际开发中，由于对服务依赖的调用可能不止一处，往往一个借口会被多处调用，所以通常都会针对每个微服务自行封装一些客户端类来包装这些依赖服务的调用。所以，Feign在此基础上做了进一步封装，由他来帮助我们定义和实现依赖服务接口的定义。

在Feign的实现下，我们只需创建一个接口并使用注解的方式来配置他们，即可完成对服务提供方的接口绑定，自动封装服务调用客户端的开发量。



# 使用步骤

1. 微服务调用接口 + `@FeignClient` 注解
2. 编写Controller，不再需要RestTemplate



# 超时机制

超时以后会报出`RetryableException`异常，默认为1秒



# 日志机制

Feign提供了日志打印功能，可以通过配置来调整日志级别，从而了解Feign中Http请求的细节，对Feign接口的调用情况进行监控和输出

* `NONE` 默认，不显示任何日志
* `BASIC` 仅记录请求方法，URL，响应状态码和执行时间
* `HEADERS` 追加请求和响应头信息
* `FULL` 追加请求和响应的正文以及元数据

## 配置方法

```yaml
feign:
  client:
    config:
      default.loggerLevel: FULL
```

或者创建一个配置bean，返回Logger.Level对象

然后在yml中开启接口日志监控

```yaml
logging:
  level: 
    com.github.springcloud.service.PaymentFeignService: debug
```