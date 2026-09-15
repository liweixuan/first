## Why

项目当前是空的纪律骨架（仅 harness/openspec/superpowers 目录约定，src/ 与 tests/ 无代码）。需要一个可运行的前后端分离开发骨架作为后续所有业务变更的起点：后端基于 Java 17 + Spring Boot + Maven，前端基于 Vue 3 + Vite，同仓双工程布局，开箱即跑，避免每个新业务从头搭环境。

## What Changes

- 新建 `backend/` 工程：Spring Boot 3.5.x + JDK 17 + Maven 单模块，MyBatis-Plus 持久层（连 MySQL 8），统一响应体、全局异常处理、MP 分页、SpringDoc + Knife4j API 文档。
- 新建 `frontend/` 工程：Vue 3 + TypeScript + Vite，集成 Element Plus、Pinia、Vue Router、axios，dev 环境经 Vite 代理 `/api` 到后端 8080。
- 新增两个能力规格：`backend-skeleton`（后端骨架行为）与 `frontend-skeleton`（前端骨架行为）。
- 不包含：用户鉴权与 RBAC（作为后续独立变更增量）、具体业务模块、部署脚本。

## Capabilities

### New Capabilities
- `backend-skeleton`: Spring Boot 后端工程骨架，提供健康检查、统一响应体、全局异常处理、MP 分页查询与 API 文档等基础行为。
- `frontend-skeleton`: Vue 3 前端工程骨架，提供路由、状态管理、HTTP 客户端封装与基础布局，能经代理调用后端接口并渲染结果。

### Modified Capabilities
<!-- 无：项目当前没有任何既有规格 -->

## Impact

- 新增文件：`backend/`（pom.xml、src/main、src/test）与 `frontend/`（package.json、vite.config.ts、src）全部为新文件，不修改现有文件。
- 环境依赖：JDK 17、Maven 3.9+、Node.js 20+、MySQL 8（本地或 Docker）。
- 开发约束：遵循项目 TDD 铁律——每个后端横切件（统一响应体、异常处理器、分页）先写失败测试再实现；前端以 Vitest 覆盖关键逻辑。
- 后续影响：鉴权变更将在本骨架的 controller/service/repository 分层之上增量实现。
