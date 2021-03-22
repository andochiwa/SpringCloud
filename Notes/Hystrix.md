# 简介

Hystrix是一个用于处理分布式系统的延迟和容错的开源库，在分布式系统中，许多依赖不可避免的会调用失败，比如超时、异常等，Hystrix能保证在一个依赖出问题的情况下，不会导致整体服务失败，避免级联故障，以提高分布式系统的弹性

断路器本身是一种开关装置，当某个服务单元发生故障之后，通过断路器的故障监控(类似熔断保险丝)，向调用方返回一个符合预期的，可处理的备选响应(FallBack)，而不是长时间的等待或者抛出调用方无法处理的异常，这样就保证了服务调用方的线程不会被长时间、不必要的占用，从而避免了故障在分布式系统中的蔓延，乃至雪崩



# 服务端备用方法

1. 在service层的方法上加上注解

   ```java
   @HystrixCommand(fallbackMethod = "getByIdTimeoutHandler",
           commandProperties =  {
           @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
   })
   ```

   其中fallbackMethod表示出现异常时执行的方法，@HystrixProperty.name表示这个线程执行时间, value表示超时时间，`ignoreExceptions`可以过滤异常

2. 主启动类激活 `@EnableHystrix`



# 客户端备用方法

1. 配置文件开启`feign.circuitbreaker.enabled = true`
2. 主启动类激活 `@EnableHystrix`
3. 在业务类上的方法加上同上注解



# 指定默认的备用方法

1. 编写全局的处理异常方法
2. 在类上加上注解`@DefaultProperties(defaultFallback = "")`， 其中defaultFallback内是方法名
3. 在需要降级的方法上加上注解`@HystrixCommand`



# 服务降级

客户端去调用服务端，但服务端宕机或已关闭时的处理

在之前的情况中，`@HystrixCommand`注解需要标注在方法上，耦合度很高，所以需要解耦

1. 重新建一个自定义类，实现自定义的`Feign`接口
2. 在自定义类中重写接口的方法
3. 开启`feign.circuitbreaker.enabled = true`
4. 在Feign接口的注解`FeignClient`内添加`fallback = xxx.class`