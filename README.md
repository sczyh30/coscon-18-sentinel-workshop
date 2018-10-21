<img src="https://user-images.githubusercontent.com/9434884/43697219-3cb4ef3a-9975-11e8-9a9c-73f4f537442d.png" alt="Sentinel Logo" height="50%" width="50%">

# COSCon'18 Sentinel Workshop

欢迎参加 COSCon'18 Sentinel Workshop，我们将通过几个常见的场景帮您快速入门 [Sentinel](https://github.com/alibaba/Sentinel)。
[Sentinel](https://github.com/alibaba/Sentinel) 是阿里巴巴集团开源的，面向分布式服务架构的轻量级流量控制组件，主要以流量为切入点，从流量控制、熔断降级、系统负载保护等多个维度来帮助用户保护服务的稳定性。（欢迎 star/fork）

> 注：由于现场的网络环境不是非常好，因此强烈建议您提前下载好 Sentinel 控制台 jar 包，并提前 clone 本工程解析一下 Maven 依赖。

## 准备工作

首先下载打包好的 Sentinel 控制台 jar 包，下载链接：https://sentinel-workshop.oss-cn-shenzhen.aliyuncs.com/sentinel-dashboard.jar

然后通过命令行启动 Sentinel 控制台：

```bash
java -Dserver.port=8080 -Dcsp.sentinel.dashboard.server=localhost:8080 -Dproject.name=sentinel-dashboard -jar sentinel-dashboard.jar
```

当控制台启动成功后，访问 localhost:8080 查看控制台。注意第一次访问控制台会触发 Sentinel 的初始化，因此可能会比较慢。

## 场景介绍

Workshop 共包含三个场景：

- Web 服务端限流
- Dubbo consumer / provider 的最佳实践
- 流量整形（匀速器模式）

## 应用启动参数

- `project.name`：应用名称，在 Sentinel 控制台上显示的名称即为此应用名
- `csp.sentinel.dashboard.server`：Sentinel 控制台的地址
- `csp.sentinel.api.port`：Sentinel transport server 的端口，默认为 8719

以下是一个示例：

```bash
-Dcsp.sentinel.api.port=8723 -Dcsp.sentinel.dashboard.server=localhost:8080 -Dproject.name=web-demo
```