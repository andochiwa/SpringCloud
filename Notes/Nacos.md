# 简介

Nacos是构建以“服务”为中心的现代应用架构（例如微服务范式、云原生范式）的服务基础设施，致力于微服务的发现、管理和信息配置，能帮助开发者快速实现动态服务发现、服务配置、服务元数据及流量管理，从而更敏捷、更容易的构建、交付和管理微服务平台。Nacos支持几乎所有主流类型（诸如：Kubernetes Service、gRPC &amp; Dubbo RPC Service、SpringCloud RESTful Service）的服务的发现、配置和管理。

简单来说就是一个更易于构建云原生应用的动态服务发现、配置管理和服务管理平台，为注册中心+配置中心的组合。



# 构建

[参照官网即可](https://spring-cloud-alibaba-group.github.io/github-pages/hoxton/en-us/index.html)，只需要导入依赖以及yml配置

**大坑！**Nacos已经删除了Ribbon依赖，并且没有加入其它负载均衡，所以Nacos现在是没有负载均衡能力的，如果需要的话要另加依赖



# Nacos特性，CP+AP

Nacos可以支持切换强一致性和高可用性。

一般来说，如果不需要存储服务级别的信息且服务实例是通过nacos-client注册，并能够保持心跳上报，那么就可以选择高可用性(AP)。当前主流的服务SpringCloud和Dubbo都适用于AP，AP减弱了一致性，所以只支持注册临时实例。

如果需要在服务级别编辑或存储配置信息，那么强一致性(CP)是必须的，K8S服务和DNS服务适用于CP模式。CP模式下支持注册持久化实例，此时则是以Raft协议为集群运行模式，该模式下注册实例之前必须先注册服务，如果服务不存在，则会返回错误。

切换方法：`curl -X PUT '$NACOS_SERVER:8848/nacos/v1/ns/operator/switches?entry=serverMode&value=CP'`



# Nacos作为配置中心配置

bootstrap.yml:

```yaml
server:
  port: 3366
spring:
  application:
    name: nacos-config-client
  cloud:
    nacos:
      discovery:
        server-addr: localhost:8848 # nacos服务注册中心地址
      config:
        server-addr: localhost:8848 # nacos服务配置中心地址
        file-extension: yaml # 指定yaml格式
```

application.yml:

```yaml
spring:
  profiles:
    active: dev # 表示开发环境
```

## 配置规则

[看官方文档](https://nacos.io/zh-cn/docs/quick-start-spring-cloud.html)，不要忘记`@RefreshScope`注解

**巨坑！！！！**，SpringCloud2020后Nacos无法读取bootstrap.yml文件，需要导入依赖

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-bootstrap</artifactId>
    <version>3.0.2</version>
</dependency>
```

## 命名空间，组，DataID

类似Java里面的package名和类名，Namespace可以用于区分部署环境，Group和DataId逻辑上区分两个目标对象

默认的Namespace是public，主要用来实现隔离。比如现在有开发、测试、生产三个环境，就可以创建三个Namespace，不同的Namespace之间隔离

Group默认为DEFAULT_GROUP，Group可以把不同的微服务划分到同一个分组里去

Service就是微服务，一个Service可以保护多个Cluster，Nacos默认Cluster是DEFAULT，Cluster是对指定微服务的一个虚拟化分。比如为了容灾，把微服务分别部署在了不同服务器，这样就可以给服务器的Service微服务起不同的集群名称，还可以让不同服务器的微服务互相调用，提升性能。

Instance，微服务的实例



# 搭建集群环境

[文档见这里](https://nacos.io/zh-cn/docs/deployment.html)

默认Nacos使用嵌入式数据库实现数据的存储。所以，当启动多个默认配置下的Nacos节点时，数据存储存在一致性问题，为了解决这个问题，Nacos采用了集中式存储的方式来支持集群化部署，目前只支持MySQL

Nacos支持三种部署模式

1. 单机模式，用于测试和单机试用
2. 集群模式，用于生产环境，确保高可用
3. 多集群模式，用于多数据中心场景

### 配置MySQL步骤

1. 在nacos/conf中找到Sql脚本`nacos-mysql`执行到MySQL数据库中
2. 修改conf/application.properties文件，增加支持mysql数据源配置（目前只支持mysql），添加mysql数据源的url、用户名和密码

### 配置集群步骤

1. 配置好MySQL环境
2. 更改conf/cluster.conf.example，改名为cluster.conf，在里面配置集群服务器的ip:port
3. 更改conf/application.properties，修改端口号
4. 修改conf/startup.sh(linux) conf/startup.cmd(windows)为集群启动
5. nginx的配置文件nginx.conf中配置三个节点upstream, location
6. 在工程的yml文件中将url地址换成nginx的地址

