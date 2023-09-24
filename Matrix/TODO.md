# Matrix

## Silk

基于规则控制的客户端

不依赖入参，且参数可自定义

规则、AB、同步、压缩、授权

## Ink

代码平滑切换工具

问题：将类A修改为A‘，线上灰度，新老平滑切换，旧代码自动删除，多段修改并存

## Silk-DashBoard Architecture

- domain 领域对象
    - entity 领域实体
    - executor 业务编排
- facade：对外
    - model 标准对外DTO
    - scheduler 对调度
        - timing 定时调度
        - message 消息调度
    - service 对服务
    - controller 对前端
        - model 前端VO
- infrastructure
    - log 日志处理
    - error 异常定义
- manager
    - gateway 外部应用依赖
    - repository 内部数据源依赖
        - model DO