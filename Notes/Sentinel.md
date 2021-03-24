# 简介

[看官方文档，写的非常好](https://github.com/alibaba/Sentinel/wiki/%E4%BB%8B%E7%BB%8D)

随着微服务的流行，服务和服务之间的稳定性变得越来越重要。Sentinel 以流量为切入点，从流量控制、熔断降级、系统负载保护等多个维度保护服务的稳定性。

Sentinel 具有以下特征:

- **丰富的应用场景**：Sentinel 承接了阿里巴巴近 10 年的双十一大促流量的核心场景，例如秒杀（即突发流量控制在系统容量可以承受的范围）、消息削峰填谷、集群流量控制、实时熔断下游不可用应用等。
- **完备的实时监控**：Sentinel 同时提供实时的监控功能。可以在控制台中看到接入应用的单台机器秒级数据，甚至 500 台以下规模的集群的汇总运行情况。
- **广泛的开源生态**：Sentinel 提供开箱即用的与其它开源框架/库的整合模块，例如与 Spring Cloud、Dubbo、gRPC 的整合。只需要引入相应的依赖并进行简单的配置即可快速地接入 Sentinel。
- **完善的 SPI 扩展点**：Sentinel 提供简单易用、完善的 SPI 扩展接口。您可以通过实现扩展接口来快速地定制逻辑。例如定制规则管理、适配动态数据源等。

Sentinel 分为两个部分:

- 核心库（Java 客户端）不依赖任何框架/库，能够运行于所有 Java 运行时环境，同时对 Dubbo / Spring Cloud 等框架也有较好的支持。
- 控制台（Dashboard）基于 Spring Boot 开发，打包后可以直接运行，不需要额外的 Tomcat 等应用容器。



## 配置流程

1. 导入依赖

   ```xml
   <dependency>
       <groupId>com.alibaba.cloud</groupId>
       <artifactId>spring-cloud-starter-alibaba-sentinel</artifactId>
       <version>2020.0.RC1</version>
   </dependency>
   ```

2. 配置yml

   ```yaml
   server:
     port: 8401
   spring:
     application:
       name: cloud-sentinel-service
     cloud:
       nacos:
         discovery:
           server-addr: localhost:8848
       sentinel:
         transport:
           # 配置sentinel
           dashboard: localhost:8080
           # 默认8719，如果被占用会+1找到未占用的为止
           port: 8719
   ```



# 流控规则

[官方文档](https://github.com/alibaba/Sentinel/wiki/%E6%B5%81%E9%87%8F%E6%8E%A7%E5%88%B6)

* 资源名：唯一名称，默认请求路径
* 针对来源：Sentinel可以针对调用者进行限流，填写微服务吗，默认default(不区分来源)
* 阈值类型/单机阈值
  * QPS(每秒钟的请求数量)：当调用该api的QPS达到阈值时进行限流
  * 线程数：当调用该api的线程数达到阈值时进行限流
* 是否集群：不需要集群
* 流控模式：
  * 直接：api达到限流条件时，直接限流
  * 关联：当关联的资源达到阈值时，限流自己
  * 链路：只记录指定链路上的流量(指定资源从入口资源进来的流量，如果达到阈值就进行限流)
* 流控效果
  * 快速失败：直接失败，抛异常
  * Warm Up：根据codeFactor(冷加载因子，默认为3)的值，经过预热时长，才达到设置的QPS阈值
  * 排队等待：匀速排队，让请求以匀速的速度通过，阈值必须设置为QPS，否则无效



# 熔断降级

[官方文档](https://github.com/alibaba/Sentinel/wiki/%E7%86%94%E6%96%AD%E9%99%8D%E7%BA%A7)

## 熔断策略

Sentinel 提供以下几种熔断策略：

- 慢调用比例：选择以慢调用比例作为阈值，需要设置允许的慢调用 RT（最大响应时间），请求的响应时间大于该值则统计为慢调用。当单位统计时长内请求数目大于设置的最小请求数目，并且慢调用的比例大于阈值，则接下来的熔断时长内请求会自动被熔断。
  经过熔断时长后熔断器会进入半开状态（HALF-OPEN 状态），若接下来的一个请求响应时间小于设置的慢调用 RT 则结束熔断，若大于设置的慢调用 RT 则会再次被熔断
- 异常比例 ：当单位统计时长内请求数目大于设置的最小请求数目，并且异常的比例大于阈值，则接下来的熔断时长内请求会自动被熔断。
  经过熔断时长后熔断器会进入半开状态，若接下来的一个请求成功完成（没有错误）则结束熔断，否则会再次被熔断。异常比率的阈值范围是 `[0.0, 1.0]`，代表 0% - 100%。
- 异常数 ：当单位统计时长内的异常数目超过阈值之后会自动进行熔断。
  经过熔断时长后熔断器会进入半开状态，若接下来的一个请求成功完成（没有错误）则结束熔断，否则会再次被熔断。



# 热点key限流

热点参数限流会统计传入参数中的热点参数，并根据配置的限流阈值与模式，对包含热点参数的资源调用进行限流。热点参数限流可以看做是一种特殊的流量控制，仅对包含热点参数的资源调用生效

简单来说，对某个热点参数做限制，当参数在设定时间内调用次数超过设定范围，则进行限流，转入blockHandler规定的方法

具体可以看[官方文档](https://github.com/alibaba/Sentinel/wiki/%E7%83%AD%E7%82%B9%E5%8F%82%E6%95%B0%E9%99%90%E6%B5%81)

```java
// 热点限流
@GetMapping("/hotKey")
@SentinelResource(value = "hotKey", blockHandler = "hotKeyHandler") // blockHandler表示备用方法
public String hotKey(@RequestParam(value = "p1", required = false) String p1,
                     @RequestParam(value = "p2", required = false) String p2) {
    return "test hot key";
}

public String hotKeyHandler(String p1, String p2, BlockException blockException) {
    return "test hot key fail!";
}
```