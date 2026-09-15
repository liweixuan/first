## Purpose
后端工程骨架能力：定义 Spring Boot 后端的基础行为契约，包括健康检查、统一响应格式、全局异常处理、示例资源分页查询与 API 文档访问，作为后续业务模块开发的公共地基。

## ADDED Requirements

### Requirement: 健康检查
系统 MUST 提供健康检查接口，用于确认后端服务处于运行状态。

#### Scenario: 查询服务健康状态
- **WHEN** 客户端向 `/api/health` 发起 GET 请求
- **THEN** 服务返回 HTTP 200，响应体为统一响应格式，`code` 为 0 且 `data.status` 为 "UP"

### Requirement: 统一响应体
所有业务接口的响应 MUST 使用统一信封结构 `{code, message, data}`，其中 `code` 为 0 表示成功，非 0 表示业务错误。

#### Scenario: 成功响应使用统一信封
- **WHEN** 任意业务接口成功返回
- **THEN** 响应体包含 `code=0`、非空 `message` 与 `data` 字段

#### Scenario: 业务错误响应使用统一信封
- **WHEN** 任意业务接口返回业务错误
- **THEN** 响应体包含非 0 的 `code`、描述错误的 `message`，且 `data` 为 null

### Requirement: 全局异常处理
系统 MUST 将未捕获异常与业务异常统一转换为统一响应格式，不得向客户端泄露堆栈信息。

#### Scenario: 业务异常被转换
- **WHEN** 服务层抛出业务异常
- **THEN** 响应体为统一信封，`code` 为该业务异常对应的错误码，HTTP 状态为 200

#### Scenario: 未知异常被兜底
- **WHEN** 服务层抛出未预期的运行时异常
- **THEN** 响应体为统一信封，`code` 为通用服务器错误码，HTTP 状态为 500，且响应不含堆栈细节

### Requirement: 示例资源分页查询
系统 MUST 提供示例资源的分页查询接口，用于端到端验证持久层与统一响应格式。

#### Scenario: 按页码与页大小查询
- **WHEN** 客户端向示例资源列表接口发起 GET 请求并携带页码与页大小参数
- **THEN** 服务返回统一信封，`data` 包含 `records`（当页记录）、`total`（总记录数）、`current`（当前页）与 `size`（页大小）字段

#### Scenario: 数据为空时的分页响应
- **WHEN** 数据库中不存在任何示例资源记录
- **THEN** 分页查询返回 `total=0` 且 `records` 为空数组，而非报错

### Requirement: API 文档访问
系统 MUST 对外提供可浏览的 API 文档页面，展示全部已定义接口。

#### Scenario: 打开文档页面
- **WHEN** 浏览器访问 API 文档地址
- **THEN** 页面成功渲染，且列出健康检查与示例资源接口
