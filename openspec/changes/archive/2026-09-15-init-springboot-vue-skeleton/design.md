## Context

当前项目为纪律骨架，无任何生产代码。本设计面向 JDK 17 环境、Maven 构建、同仓双工程布局，动机见 proposal.md。规格契约见 specs/backend-skeleton 与 specs/frontend-skeleton。

## Goals / Non-Goals

**Goals:**
- 后端与前端两个工程可独立启动、dev 环境经代理联通，跑通一条端到端链路（列表页 → `/api` → Controller → Service → MP → MySQL）。
- 后端横切件（统一信封、异常处理、分页）以可测试的纯逻辑交付，满足项目 TDD 铁律。
- 骨架不留"隐式假设"：所有公共约定（响应格式、错误码、分页字段）由规格显式定义。

**Non-Goals:**
- 不实现鉴权/RBAC（独立变更）。
- 不引入多模块 Maven 与微服务设施（骨架保持单模块）。
- 不配置生产部署（无 Dockerfile、无 CI），仅保证 dev 链路可用。

## Decisions

### D1 后端基线：Spring Boot 3.5.x + JDK 17 + Maven 单模块
理由：JDK 17 是用户环境约束；3.5 线官方兼容 Java 17–25，是 3.x 当前主流且第三方生态（MyBatis-Plus、Knife4j）适配最全。Boot 4.x 虽官方基线含 Java 17，但生态兼容尚在追赶期，骨架阶段不冒险。
备选：Boot 4.1.x（新但生态风险）、Boot 2.7.x（已 EOL，排除）。

### D2 持久层：MyBatis-Plus + MySQL 8+
理由：MP 是 MyBatis 生态事实标准，分页插件、条件构造器开箱即用，SQL 可见便于排查；与国内业务场景契合。
版本决策（2026-09-15 apply 时用户确认）：联调使用本地已运行的 MySQL 9.7.1（root 免密），骨架场景与 MySQL 8 完全兼容；测试环境仍用 H2 隔离。驱动版本由 Spring Boot BOM 管理。
备选：Spring Data JPA（简单 CRUD 零代码但复杂查询成本高）、JOOQ（类型安全但学习曲线陡）；Docker MySQL 8（版本一字不差但多一个常驻容器）。

### D3 分层：Controller / Service / Repository + 统一信封 + 全局异常
理由：三层是 Spring 惯例，骨架即团队默认；统一信封 `{code, message, data}` 与异常处理器集中承载规格中的"统一响应"与"错误转换"两条行为契约，避免各 Controller 自行处理。
备选：命令查询分离（CQRS）等重模式，骨架阶段过度设计，排除。

### D4 鉴权：骨架期不引入
理由：鉴权与业务模型（用户/角色/权限）强耦合，选错返工成本高；骨架只需无争议地基。作为下一变更以 TDD 增量进入。
备选：Sa-Token / Spring Security+JWT，均留待鉴权变更评估。

### D5 前端：Vue 3 + TypeScript + Vite + Element Plus + Pinia + Vue Router + axios
理由：Vue 3 + Vite 是当前 Vue 生态默认；TS 为骨架提供类型安全底线；Element Plus 是后台系统事实标准；Pinia 为官方推荐状态库；axios 拦截器天然承载"统一错误处理"契约。
备选：React 生态（团队无此偏好，排除）；UI 库 Naive UI / Ant Design Vue（均可替换，不影响契约）。

### D6 dev 联通：Vite 代理 `/api` → `http://localhost:8080`
理由：同仓双工程下 dev 跨域最省事方案，前端代码无需感知后端地址；生产部署形态留给部署变更。
备选：后端 CORS 放行（暴露更多配置且 dev 仍需改代码，排除）。

### D7 API 文档：SpringDoc OpenAPI + Knife4j
理由：两个依赖即可从注解生成文档页面，满足"API 文档访问"契约；Knife4j 提供中文友好的 UI。
备选：手写文档（易腐化，排除）。

### D8 示例资源：DemoItem（id/name/description/createdAt）+ 种子数据
理由：分页契约需要一个最小资源来端到端验证；种子数据保证"空数据分页"与"有数据分页"两个场景都可手测。
备选：不建示例资源（无法验证 MP 链路，排除）。

## Risks / Trade-offs

- [Boot 3.5 与 MP 版本兼容性] → Maven 中用 Spring Boot BOM 管理版本，MP 使用 spring-boot3 starter，版本在 tasks 中锁定为当前稳定组合。
- [MySQL 环境缺失导致 apply 后跑不通] → 示例资源用 H2 测试库跑测试（规格不变）；本地联调用 docker-compose 一条命令起 MySQL 8，或复用已有实例，连接串走 application.yml 环境变量。
- [前端 TS 配置细节拖慢骨架] → 用官方 create-vue 模板起步再裁剪，不手搓 Vite 配置。
- [骨架被误当生产模板直接上线] → README 中显式标注"dev 骨架，生产部署另行变更"。
- [分页字段命名与未来前端分页组件耦合] → 分页字段（records/total/current/size）已在规格中固化，后续组件按契约适配，不随实现变动。
