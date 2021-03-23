# 简介

Spring Cloud STream是一个构建消息驱动微服务的框架。

应用个程序通过inputs或outputs与Spring Cloud Stream中的绑定(binder)对象交互，通过我们配置来绑定，而Spring Cloud Stream的绑定对象负责与消息中间件交互。所以我们只需要搞清楚如何与Spring Cloud Stream交互就可以方便使用消息驱动的方式。

通过使用Spring Integration来连接消息代理中间件以实现消息事件驱动。Spring Cloud Stream为一些供应商的消息中间件产品提供了个性化的自动化配置实现，引用了发布-订阅、消费组、分区的三个核心概念。

目前只支持RabbitMQ、Kafka



# 实现基本原理

在没有绑定器的概念时，SpringBoot应用要直接与消息中间件进行消息交互的时候，由于各消息中间件的构建初衷的不同，他们的实现细节上也会有较大的差异

通过定义绑定器Binder作为中间层，完美的实现了应用程序与消息中间件细节之间的隔离。向应用程序暴露统一的Channel通道，使得应用程序不再需要考虑各种不同消息中间件实现。

Binder中，output对应生产者，input对应消费者。消息通信方式遵循发布-订阅模式



# 生产者配置步骤

1. yml配置

   ```yaml
   server:
     port: 8801
   spring:
     application:
       name: cloud-stream-provider
     cloud:
       stream:
         binders: # 配置要绑定的rabbitmq的服务信息
           defaultRabbit: # 表示定义的名称，用于与Binding整合
             type: rabbit # 消息组件类型
             environment: # 设置rabbitmq的相关环境配置
               spring:
                 rabbitmq:
                   host: localhost
                   port: 5672
                   username: admin
                   password: admin
         bindings: # 服务整合的处理
           output: # 这个名字是一个信道的名称
             destination: studyExchange # 表示要使用的Exchange名称定义
             content-type: application/json # 消息类型，文本为text/plain
             binder: defaultRabbit # 要绑定的消息服务的具体设置
   ```

2. 编写业务类

   ```java
   @EnableBinding(Source.class)
   public class MessageProviderImpl implements MessageProvider {
       @Resource
       private MessageChannel output; // 消息发送信道
       
       @Override
       public String send() {
           String uuid = IdUtil.simpleUUID();
           output.send(MessageBuilder.withPayload(uuid).build());
           return null;
       }
   }
   ```



# 消费者配置步骤

1. yml配置，把生产者的output改成input即可

2. 编写业务类

   ```java
   @EnableBinding(Sink.class)
   public class ReceiveMessageController {
       @Value("${server.port}")
       private String serverPort;
   
       @StreamListener(Sink.INPUT)
       public void input(Message<String> message) {
           System.out.println("consumer1 ===>" + message.getPayload() + "\t" + serverPort);
       }
   }
   ```



# 多个消费者重复消费问题

例如，订单系统做了集群部署，都会从RabbitMQ中获取消息，但如果同一个订单被两个服务获取到，就会造成数据错误，我们要避免这种情况。（即不处于同一个队列上，而队列为主题订阅模式）

这时可以使用Stream中的消息分组解决，Stream中同一个组(队列？)中的多个消费者是竞争关系，能够保证消息只会被其中一个应用消费一次，不同组可以全面消费。

在yml中进行自定义分组

```
spring:
  application:
    name: cloud-stream-consumer
  cloud:
    stream:
      bindings: # 服务整合的处理
        input: # 信道的名称
          group: groupB
```

