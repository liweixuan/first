## 1. 后端工程初始化

- [x] 1.1 创建 `backend/` Maven 工程（pom.xml：spring-boot-starter-parent 3.5.x，依赖 web/mybatis-plus-boot3-starter/mysql/h2(测试)/springdoc+knife4j），验证 `mvn -q compile` 成功
- [x] 1.2 建立测试基线：application-test.yml（H2）+ 最小 @SpringBootTest 空上下文测试，验证 `mvn test` 全绿

## 2. 后端横切件（TDD：先红后绿）

- [x] 2.1 统一信封：先写失败测试覆盖"成功响应 code=0 / 业务错误 data=null"两场景，再实现 ApiResponse，验证测试红转绿
- [x] 2.2 全局异常处理：先写失败测试覆盖"业务异常→200 信封 / 未知异常→500 信封且无堆栈"，再实现 BizException + GlobalExceptionHandler，验证红转绿
- [x] 2.3 健康检查：先写 MockMvc 失败测试（GET /api/health 返回 code=0 且 data.status=UP），再实现接口，验证红转绿

## 3. 后端示例资源（TDD）

- [x] 3.1 DemoItem 实体 + MP Mapper + H2 schema/种子数据，验证 mapper 集成测试通过
- [x] 3.2 分页查询：先写失败测试覆盖"空数据 total=0/有数据 records+total 正确"，再实现 service + controller（GET /api/demo-items），验证 MockMvc 全链路测试红转绿
- [x] 3.3 配置 MP 分页插件并启用，验证分页集成测试仍绿
- [x] 3.4 接入 SpringDoc + Knife4j，验证 `/doc.html` 可访问且列出健康检查与示例资源接口

## 4. 前端工程初始化

- [ ] 4.1 用 create-vue 脚手架 `frontend/`（TS + Router + Pinia + Vitest），验证 `npm run dev` 可启动
- [ ] 4.2 集成 Element Plus + axios，Vite 代理 `/api` → `http://localhost:8080`，验证 `npm run build` 成功

## 5. 前端骨架行为（TDD）

- [ ] 5.1 axios 封装：先写 Vitest 失败测试（code≠0 → rejected 且提示 message / 网络错误 → 通用提示），再实现拦截器封装，验证红转绿
- [ ] 5.2 demo store：先写失败测试（查询成功写入 records/total），再实现 Pinia store，验证红转绿
- [ ] 5.3 页面：首页布局（导航+内容区）+ 示例资源列表页（Element Plus 表格+分页），组件测试验证渲染，红转绿

## 6. 端到端验证

- [ ] 6.1 同时启动前后端，浏览器验证：列表页展示 MySQL 种子数据；后端停掉时页面显示统一错误提示（对照 specs 场景逐条核验）
- [ ] 6.2 全量回归：`mvn test` 与 `npm run test` 均 0 failures；在根 README 补写"一键启动"说明
