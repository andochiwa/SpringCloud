# 简介

Hystrix是一个用于处理分布式系统的延迟和容错的开源库，在分布式系统中，许多依赖不可避免的会调用失败，比如超时、异常等，Hystrix能保证在一个依赖出问题的情况下，不会导致整体服务失败，避免级联故障，以提高分布式系统的弹性

断路器本身是一种开关装置，当某个服务单元发生故障之后，通过断路器的故障监控(类似熔断保险丝)，向调用方返回一个符合预期的，可处理的备选响应(FallBack)，而不是长时间的等待或者抛出调用方无法处理的异常，这样就保证了服务调用方的线程不会被长时间、不必要的占用，从而避免了故障在分布式系统中的蔓延，乃至雪崩



# 生产端启动方法

1. 在service层的方法上加上注解

   ```java
   @HystrixCommand(fallbackMethod = "getByIdTimeoutHandler",
           commandProperties =  {
           @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
   })
   ```

   其中fallbackMethod表示出现异常时执行的方法，@HystrixProperty.name表示这个线程执行时间, value表示超时时间

2. 主启动类激活 `@EnableCircuitBreaker`

