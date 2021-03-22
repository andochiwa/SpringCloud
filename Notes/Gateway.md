# 简介

SpringCloud Gateway是在Spring生态系统之上构建的API网关服务，基于Spring5，SpringBoot2和Project Reactor等技术。Gateway旨在提供一种简单而有效的方式来对API进行路由，以及提供一些强大的过滤器功能，例如熔断、限流、重试等

Gateway作为SpringCloud生态系统的网关，目标是代替Zuul，在SpringCloud2.0以上版本中，没有对新版本的Zuul2.0以上最新高性能版本进行集成，仍然使用Zuul1.x非Reactor模式的老版本。为了提升网关的性能，SpringCloud Gateway是基于WebFlux框架实现，而WebFlux框架低层则使用了高性能的Reactor模式通信框架Netty。

# 构成

web请求通过一些匹配条件，定位到真正的服务节点。并在这个转发过程的前后，进行一些精细化控制。

predicate就是匹配条件，而filiter是一个无所不能的拦截器，有了这两个元素再加上目标uri，就可以实现一个具体的路由

## Route 路由

构建网关的基本模块，由ID，目标URI，一系列的断言和过滤器组成，如果断言为true则匹配该路由

## Predicate 断言

匹配HTTP中的所有内容(请求头，请求参数等)，如果请求与断言相匹配则进行路由

## Filter 过滤器

Spring框架中的GatewayFilter的实例，使用过滤器，可以在请求被路由前或者后对请求进行修改

# 流程

客户端向SpringCloud Gateway发出请求，然后在Gateway Handler Mapping中找到与请求匹配的路由，将其发送到Gateway Web Handler

Handler再通过指定的过滤器链来将请求发送到实际的服务执行业务逻辑然后返回。过滤器可能在发送代理请求之前(pre)或之后(post)执行业务逻辑

Filter在pre类型的过滤器可以做参数校验、权限校验、流量监控、日志输出、协议转换等

在post类型的过滤器中可以做响应内容、响应头的修改、日志输出、流量监控等，有着非常重要的作用



# 配置流程

1. yml配置

   ```yaml
   server:
     port: 9527
   spring:
     application:
       name: cloud-gateway-service
     cloud:
       gateway:
         routes:
           - id: payment_routh           # 路由的id，没有固定规则，但要求唯一，建议配合服务名
             uri: http://localhost:8001  # 匹配后提供服务的路由地址
             predicates:
               - Path=/payment/**        # 断言，路径相匹配的进行路由
   ```

   

2. 