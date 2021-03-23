# 简介

在微服务框架中，一个由客户端发起的请求在后端系统中会经过多个不同的服务节点调用来协同产生最后的请求结果，每一个前端请求都会形成一条复杂的分布式服务调用链路，链路中的任何一环出现高延时或错误都会引起整个请求最后的失败

Spring Cloud Sleuth提供了一套完整的服务追踪解决方案，并且兼容支持zipkin

代码中监控8001和80端口



# 基本术语

1. `Trace` 它是由一组有相同Trace ID的`Span`串联形成一个树状结构。为了实现请求跟踪，当请求请求到分布式系统的入口端点时，只需要服务跟踪框架为该请求创建一个唯一的跟踪标识，同时在分布式系统内部流转的时候，框架始终保持传递该唯一标识，直到返回请求为止，我们通过它将所有请求过程中的日志关联起来
2. `Span` 它代表了一个基础的工作单元，例如服务调用。为了统计各处理单元的时间延迟，当前请求到达各个服务组件时，也通过一个唯一标识来标记它的开始、具体过程以及结束。通过span的开始和结束的时间戳，就能统计该span的时间延迟，除此之外，我们还可以获取如事件名称、请求信息等元数据

# 采样率

如果服务的流量很大，全部采集对传输、存储压力比较大。这个时候可以设置采样率，sleuth 可以通过配置 `spring.sleuth.sampler.probability=X.Y`(如配置为1.0，则采样率为100%，采集服务的全部追踪数据)，若不配置默认采样率是0.1(即10%)。也可以通过实现bean的方式来设置采样为全部采样(AlwaysSampler)或者不采样(NeverSampler)：如

```java
@Bean public Sampler defaultSampler() { return new AlwaysSampler(); }
```

sleuth采样算法的实现是 Reservoir sampling（水塘抽样）。实现类是 `PercentageBasedSampler`。

# 使用流程

1. 引入依赖`spring-cloud-starter-zipkin`，包含了sleuth和zipkin
2. yml添加配置

```yaml
spring:
  zipkin:
    base-url: http://localhost:9411
  sleuth:
    sampler:
```