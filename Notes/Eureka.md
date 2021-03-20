# Eureka两个组件

## Eureka Server

各个微服务节点通过配置启动后，会在Eureka Server中进行注册，这样Eureka Server中的服务注册表将会存储所有可用服务节点的信息，服务节点的信息可以在界面中直观看到

## Eureka Client

是一个Java客户端，用于简化Eureka Server的交互，客户端同时也具备一个内置的，使用轮询负载均衡算法的负载均衡器。在应用启动后，将会向Eureka Server发送心跳(默认周期为30秒)。如果Eureka Server在多个心跳周期内没有接受到某个节点的心跳，Eureka Server将会从服务注册表中把这个服务节点移除(默认90秒)



# 单机Eureka构建步骤

1. 导入`spring-cloud-starter-netflix-eureka-server`依赖，生成Eureka Server端服务注册中心(也就是微服务模块)，加入`@EnableEurekaServer`注解，表示这是个服务注册中心
2. 导入`spring-cloud-starter-netflix-eureka-client`依赖，把其他微服务模块注册进Eureka，加入`@EnableEurekaClient`注解



# 集群构建步骤(单机构建[没有那么多服务器呜呜呜])

集群的作用：相互注册，互相守望

1. 修改hosts文件映射，把不同的端口号映射到同一个地址
2. yml配置成相互的路径，**一定要注意defaultZone的大小写！！！！！！！**
3. 消费生产者配置两个集群的路径，用逗号隔开配置多个路径

## 配置消费者集群

复制一份消费者，改成另一个端口号

这时候客户端就不能把URL写死了，需要通过服务别名来找。另外还需要给RestTemplate赋予负载均衡功能，在RestTemplate的Bean上加上`@LoadBalance`即可



# 服务发现Discovery

> 对于注册进eureka里面的微服务，可以通过服务发现来获得该服务的信息

1. `DiscoveryClient`已经自动注入好了，可以用来获取各种信息
2. `@EnableDiscoveryClient`注解



# 自我保护机制

保护模式主要用于一组客户端和Eureka Server之间存在网络分区场景下的保护。一旦进入保护模式，Eureka Server将会尝试保护其服务注册表中的信息，不再删除服务注册表中的数据，也就是不会注销任何微服务。

默认情况下，如果EurekaServer在一定时间内没有接受到某个微服务实例的心跳，EurekaServer将会注销该实例。但是当网络分区故障发生(延迟、卡顿、拥挤)时，微服务与EurekaServer之间无法正常通信，以上行为可能变得非常危险。因为微服务本身是健康的，**此时不应该注销这个微服务**。Eureka通过自我保护模式来解决这个问题，当EurekaServer节点在短时间内丢失过多客户端时(可能发生了网络分区故障)，那么这个节点就会进入自我保护模式。

这是一种应对网络异常的安全保护措施。它的架构哲学是宁可同时保留所有微服务，也不盲目注销任何健康的微服务。使用自我保护模式，可以用Eureka集群更加的健壮稳定

属于CAP中AP的思想(可用性，分区容错性)

## 禁用方法

Eureka Server的配置`eureka.server.enable-self-preservation = false`

Eureka Client的配置

*  `eureka.instance.lease-renewal-interval-in-seconds` 客户端向服务端发送心跳的间隔，默认为30
*  `eureka.instance.lease-renewal-interval-in-seconds` 服务端在收到最后一次心跳后等待时间上限，默认90，超时将剔除服务

