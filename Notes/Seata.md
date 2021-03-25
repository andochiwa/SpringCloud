# 简介

Seata是阿里巴巴开源的分布式事务解决方案，致力于在微服务架构下提供高性能和简单易用的分布式事务服务

# 分布式事务模型

## 唯一ID+三组件

唯一ID：Transaction ID XID 全局唯一的事务ID

三组件

1. TC (Transaction Coordinator) - 事务协调者
   维护全局和分支事务的状态，驱动全局事务提交或回滚。

2. TM (Transaction Manager) - 事务管理器
   定义全局事务的范围：开始全局事务、提交或回滚全局事务。
3. RM (Resource Manager) - 资源管理器
   管理分支事务处理的资源，与TC交谈以注册分支事务和报告分支事务的状态，并驱动分支事务提交或回滚。



# 配置步骤

环境使用数据库MySQL+Nacos

1. 使用官方的SQL文件建表

2. 配置conf/registry.conf文件

3. 配置好script/config-center/config.txt文件，运行nacos文件夹内的.sh或.py把配置文件传到nacos上

4. 导入依赖，配置yml

   ```yaml
   seata:
     enabled: true
     application-id: ${spring.application.name}
     tx-service-group: my_tx_group
     config:
       type: nacos
       nacos:
         server-addr: localhost:8848
     registry:
       type: nacos
       nacos:
         application: seata-server
         server-addr: localhost:8848
         group: SEATA_GROUP
   ```



# 使用方法

只需要在业务方法上加个注解`@GlobalTransaction`

# 代码演示

需求：下订单=》减库存=》扣余额=》改订单状态