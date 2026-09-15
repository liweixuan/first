# 项目反馈日志（FEEDBACK-LOG）

> 本文件是项目反馈回路（finding-bound fixes）的持久记录。
> 每发现一个问题登记一条；每个问题必须绑定修复，不允许只记录不处理。

## 状态约定

- `待修复`：已登记，尚未处理
- `已修复`：必须附修复标识（commit hash / 变更 ID / 文件与行号）
- `不修复`：必须附明确理由与确认人

---

## 问题记录

| # | 日期 | 发现场景 | 问题描述 | 绑定修复 | 状态 |
| --- | --- | --- | --- | --- | --- |
| 1 | 2026-09-15 | 会话收尾 | DeleteFile 工具删除浏览器验证截图后文件被恢复，最终用 `rm` 删除成功并提交 fb63be2 | commit fb63be2 | 已修复 |
| 2 | 2026-09-15 | 后端 3.4 API 文档 | knife4j 4.5.0 传递依赖 springdoc 2.3.0，与 Boot 3.5(Spring 6.2) 不兼容，/v3/api-docs 抛 NoSuchMethodError | backend/pom.xml 显式钉住 springdoc-openapi-starter-webmvc-ui 2.8.14 | 已修复 |
| 3 | 2026-09-15 | 前端 6.2 全量回归 | create-vue 默认 `test:unit` 脚本为 watch 模式，`npm run test:unit` 永不退出，遗留 26 分钟僵尸进程 | frontend/package.json 改为 `vitest run`；kill 僵尸进程 | 已修复 |

<!-- 示例（使用后删除）：
| 1 | 2026-09-15 | 集成测试偶发失败 | 导出文件偶尔为空 | 修复计划 repair-plan-2026-09-15-empty-export.md → commit a1b2c3d | 已修复 |
-->
