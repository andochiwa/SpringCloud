# 简介

LoadBalancer是SpringCloud开发的服务调用组件，用于代替已停更的Ribbon



# 负载均衡配置

LoadBalancer默认负载均衡配置接口为`ReactorLoadBalancer`，目前只有两个实现类`RoundRobinLoadBalancer`和`RandomLoadBalancer`，分别代表轮询和随机

配置方法:

```java
// 这里不需要@Configuration
public class CustomLoadBalancerConfiguration {
    @Bean
    public ReactorLoadBalancer<ServiceInstance> randomLoadBalancer(Environment environment,
                                                LoadBalancerClientFactory loadBalancerClientFactory) {
        String name = environment.getProperty(LoadBalancerClientFactory.PROPERTY_NAME);
        return new RandomLoadBalancer(loadBalancerClientFactory
                .getLazyProvider(name, ServiceInstanceListSupplier.class),
                name);
    }
}
```

然后在配置类上加上注解`@LoadBalancerClient(name = "CLOUD-PAYMENT-SERVICE", configuration = CustomLoadBalancerConfiguration.class)`，名字为服务端的别名

如果需要自定义负载均衡算法，则需要实现`ReactorServiceInstanceLoadBalancer`接口