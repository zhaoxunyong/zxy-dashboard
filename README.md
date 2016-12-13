# hystrix监控
基于spring boot实现。

### hystrix web界面
```html
url: http://localhost:8050/hystrix
application.yml文件配置：
#端口：
server:
  port: 8050
该模块基于spring-cloud-starter-hystrix-dashboard。
```
更多请参考[spring-cloud-netflix](http://cloud.spring.io/spring-cloud-netflix/spring-cloud-netflix.html)

### turbine服务聚合
```html
url: http://localhost:8050/turbine.stream?cluster=datacenter
由于spring-cloud-netflix-turbine是基于spring cloud项目，普通项目不能使用，所以此处为自己实现。
```

#### 统一在config.properties中进行配置：
```html
#集群名称，多个用逗号分隔
turbine.aggregator.clusterConfig=datacenter
#具体集群的实例，多个用逗号分隔
turbine.ConfigPropertyBasedDiscovery.datacenter.instances=localhost
#具体集群的url
turbine.instanceUrlSuffix.datacenter=:8088/qn/hystrix.stream
```

### 启动：
#### gradle快速启动：
gradle bootrun
#### 通过jar启动：
gradle build && java -jar build/libs/zxy-dashboard-$version.jar
