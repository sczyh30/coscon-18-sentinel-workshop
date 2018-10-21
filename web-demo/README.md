# Sentinel Web Demo

本示例会演示如何快速地在一个 Web 应用中引入 Sentinel 来进行流控，并且会演示如何通过注解对某个方法进行资源保护。

在操作本示例之前，您需要先启动 Sentinel 控制台。

## 引入 Sentinel

首先我们新建一个模块，引入 Spring Boot 的依赖，编写一个简单的 Controller，这就是一个最简单的 Spring Boot 应用。

接下来我们引入 Sentinel 的相关依赖，包括：

- `sentinel-core`：Sentinel 核心依赖
- `sentinel-transport-simple-http`：Sentinel transport 模块（用于提供监控、心跳）
- `sentinel-web-servlet`：Sentinel Web Servlet 适配依赖

```xml
        <dependency>
            <groupId>com.alibaba.csp</groupId>
            <artifactId>sentinel-core</artifactId>
            <version>0.2.0</version>
        </dependency>
        <dependency>
            <groupId>com.alibaba.csp</groupId>
            <artifactId>sentinel-transport-simple-http</artifactId>
            <version>0.2.0</version>
        </dependency>
        <dependency>
            <groupId>com.alibaba.csp</groupId>
            <artifactId>sentinel-web-servlet</artifactId>
            <version>0.2.0</version>
        </dependency>
```

然后编写一个配置类，引入 Sentinel Web Filter：

```java
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean sentinelFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new CommonFilter());
        registration.addUrlPatterns("/*");
        registration.setName("sentinelFilter");
        registration.setOrder(1);

        return registration;
    }
}
```

现在我们可以启动刚刚编写的 Web 应用了。启动时需要一些参数：

```bash
-Dcsp.sentinel.api.port=8723 -Dcsp.sentinel.dashboard.server=localhost:8080 -Dproject.name=web-demo
```

访问一下我们编写的 Web 接口，初次访问会触发 Sentinel 的初始化，因此可能会比较慢。我们可以在 Sentinel 控制台上看到对应资源的访问量。

## 发起流量并查看监控

接下来我们发起一些流量，可以通过 ab 或者编写 shell 脚本循环 curl（假设 QPS 约为 10）:

```bash
while true
do 
    curl $1
    echo ""
    sleep 0.1
done
```

发起流量后，我们可以在 Sentinel 控制台的"实时监控"页面看到对应的访问量。

## 配置规则并查看监控

接下来我们给指定的 Web URL 资源添加流控规则，限制 QPS = 5。
配置完毕后，在监控页面应该马上可以看到通过的 QPS 稳定在 5。

## 注解方式保护资源

Sentinel 支持通过注解的方式对某个方法进行流控、降级等。使用注解需要引入以下依赖：

```xml
<dependency>
    <groupId>com.alibaba.csp</groupId>
    <artifactId>sentinel-annotation-aspectj</artifactId>
    <version>0.2.0</version>
</dependency>
```

然后我们可以创建一个服务，在服务方法上加上 `@SentinelResource` 注解：

```java
@Service
public class DemoService {

    @SentinelResource(value = "greetingFor", blockHandler = "handleBlockedForGreeting")
    public String greetingFor(String name) {
        return "Greeting: " + name;
    }

    /*
     * 此函数会在原函数被流控或降级时提供 fallback 实现。
     */
    public String handleBlockedForGreeting(String name, BlockException ex) {
        ex.printStackTrace();
        return "Blocked: " + name;
    }
}
```

其中各个参数的含义可以参考 [注解文档](https://github.com/alibaba/Sentinel/wiki/%E6%B3%A8%E8%A7%A3%E6%94%AF%E6%8C%81)。

我们还需要注册 Sentinel 注解 Aspect 以便注解可以生效，同时需要引入 Spring AOP 支持:

```java
@Configuration
public class AopConfig {

    @Bean
    public SentinelResourceAspect sentinelResourceAspect() {
        return new SentinelResourceAspect();
    }
}
```

现在可以在现有 controller 里面新加一个 URL，调用此服务。启动应用，访问新的 URL，通过 Sentinel 控制台观察监控。