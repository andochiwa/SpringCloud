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