# 社区化电商平台 V1.0 微信小程序

## 一、产品原型
[http://www.jisuapp.cn/make/makeapp/iD4648756L.html][产品原型]

[产品原型]: http://www.jisuapp.cn/make/makeapp/iD4648756L.html

## 二、技术选型
- **开发工具：** IDE开发工具(Idea)+WorkBatch+Git+GitHub+Maven+Pd+Hbuild-X(uni-app)+Tomcat

- **服务器运行环境：** Linux服务器、Docker容器

- **数据库：** Mysql、Oracle

- **No-SQL数据库：** Redis(缓存、数据共享、榜单)  jedis

- **测试：** swagger(在线文档、测试)、postman、单元测试、mockito(Spring提供测试网络接口)、jmeter(压力测试)

- **微服务：** SpringBoot+SpringCloud(注册中心：Eureka、Zookeeper 网关：zuul 熔断器：Hystrix 声明式：Feign 负载均衡：Ribbon 配置：Config)

- **核心框架：** Spring+SpringMVC+Mybatis

- **亮点技术：** 搜索（Elasticsearch）、消息中间件（RabbitMQ）、定时（Quartz、Spring Task）、数据库分片（Mycat）

- **第三方：** 短信、支付（支付宝和微信）、统计、图表（Echarts）、物流

## 三、实现功能
- **用户中心**
  - 注册
  - 完善资料
  - 签到
  - 抽奖
- **登录中心**
  - JWT(Json Web Token)
- **商品中心**
  - 爬虫
  - 服务
  - 榜单（热门商品-Redis）
- **搜索服务**
  - Elasticsearch
- **缓存中心**
  - 通用数据缓存
  - 数据优化
- **购物车服务**
  - 服务
  - RabbitMQ
- **消息监听服务**
  - 消息消费
- **订单服务**
  - 服务
- **支付服务**
  - 支付宝支付
  - 微信支付
    
## 四、项目上线
- Nginx 负载均衡
- Swagger 在线API文档
- 测试报告

## 五、终端联调
- 微信小程序端测试
    
## 六、优化处理
- 前端优化
- 后端优化
- 数据优化
  - SQL优化
  - 缓存处理
  - 数据分片
  - 读写分离
- 环境优化
  - Tomcat 优化
  - JVM 优化

## 七、开发文档
1. 需求文档
2. 功能文档
3. 概要文档
4. 数据接口文档
5. 测试用例文档
6. 测试报告文档
7. 压力测试文档
8. 部署文档
9. 数据库设计文档
10. 需求变更文档
11. 项目计划进度文档
    
## 八、代码仓库地址
[https://github.com/yuuyoo/shop_parent][仓库地址]

[仓库地址]: https://github.com/yuuyoo/shop_parent
	
## 联系作者
Email: yuuyoo@163.com

