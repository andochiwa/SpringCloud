# 简介

Spring Cloud Ribbon是基于Netflix Ribbon实现的一套**客户端负载均衡工具**，为Netflix发布的开源项目

主要功能是提供客户端的软件负载均衡算法和服务调用。Ribbton客户端组件提供一系列完善的配置项，如连接超时、重试等。简单来说，就是在配置文件中列出Load Balancer(LB)后面所有的机器，Ribbon会自动的帮你基于某种规则(轮询，随机等)去连接这些机器，我们可以很容易的使用Ribbon实现自定义的负载均衡算法



# Ribbon本地负载均衡客户端 VS Nginx服务端负载均衡

Nginx是服务器负载均衡，客户端所有请求都会交给Nginx，然后由Nginx实现转发请求，即负载均衡是由服务端实现的（针对服务端的调用）

Ribbon本地负载均衡，在调用微服务接口的时候，会在注册中心上获取注册信息服务列表之后缓存到JVM本地，从而在本地实现RPC远程服务调用技术（针对微服务间的调用）



# 工作步骤

1. 先选择一个Server，优先选择同一个区域内负载较少的Server
2. 再根据用户指定的策略，从Server取到的服务注册列表中选择一个地址。其中Ribbon提供了多种策略，比如轮询，随机和响应时间加权



# Ribbon负载均衡核心组件IRule

这是个接口，有很多实现类，每个实现类是一个负载均衡算法

1. `com.netflix.loadbalancer.RoundRobinRule` 轮询
2. `com.netflix.loadbalancer.RandomRule` 随机
3. `com.netflix.loadbalancer.RetryRule` 先按照`RoundRobinRule`的策略获取服务，如果获取服务失败则在指定时间内重试
4. `WeightedResponseTimeRule ` 对`RoundRobinRule`的扩展，响应速度越快的实例选择权重越大，越容易被选择
5. `BestAvailableRule` 会先过滤掉由于多次访问故障而处于断路器跳闸状态的服务，然后选择一个并发量最小的服务
6. `AvailabilityFilteringRule` 先过滤掉故障实例，再选择并发较小的实例
7. `ZoneAvoidanceRule` 复合判断Server所在区域的性能和Server的可用性选择服务器



# 替换方法

自定义配置类不能放在@ComponentScan所扫描的包以及子包下，否则自定义类会被Ribbon客户端共享，达不到特殊定制的目的

1. 在客户端模块下新建一个包，这个包不能被SpringBoot所扫描到
2. 新增一个配置类，返回IRule这个Bean对象
3. 在主启动类上添加`@RibbonClient`，标注好name的配置类