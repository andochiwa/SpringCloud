# 客户端

客户端只需要controller层，配置端口，不需要写业务逻辑，注意不要配置数据源

# RestTemplate

RestTemplate提供了多种便捷访问远程Http服务的方法，是一种简单便捷的访问Restful服务模板类，是Spring提供的用于访问Rest**服务的客户端模板工具集**

需要注入到Bean

## 使用方法

使用RestTemplate访问Restful接口很简单，(Url, RequestMap, ResponseBean.class)三个参数分别代表Rest请求地址、请求参数、HTTP响应转换被转换成的对象类型