# Eureka两个组件

## Eureka Server

各个微服务节点通过配置启动后，会在Eureka Server中进行注册，这样Eureka Server中的服务注册表将会存储所有可用服务节点的信息，服务节点的信息可以在界面中直观看到

## Eureka Client

是一个Java客户端，用于简化Eureka Server的交互，客户端同时也具备一个内置的，使用轮询负载均衡算法的负载均衡器。在应用启动后，将会向Eureka Server发送心跳(默认周期为30秒)。如果Eureka Server在多个心跳周期内没有接受到某个节点的心跳，Eureka Server将会从服务注册表中把这个服务节点移除(默认90秒)



# 单机Eureka构建步骤

1. 生成Eureka Server端服务注册中心(也就是微服务模块)，注意加入`@EnableEurekaServer`注解，表示这是个服务注册中心