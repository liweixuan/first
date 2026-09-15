# Writing Plans —— 计划拆分

## 触发时机

设计文档（`superpowers/specs/*-design.md`）已获批准后。

## 流程

### Step 1：读设计

完整阅读设计文档，先做 **File Structure 决策**：每个新增/修改文件的职责。

### Step 2：拆任务

把工作拆成多个 Task，要求：

- 每个 Task **2–5 分钟**粒度，bite-sized；
- 每个 Task 包含确切文件路径、TDD 顺序、验证命令、commit message；
- 每个 Task 的 5 步节奏：写失败测试 → 跑测试看 RED → 写最小实现 → 跑测试看 GREEN → commit。

### Step 3：落盘

将计划写入：

```
superpowers/plans/YYYY-MM-DD-<topic>-plan.md
```

并同步为 `openspec/changes/<change-id>/tasks.md`。

### Step 4：执行交接

向用户呈现两种执行模式二选一：

1. **subagent-driven（推荐）**：每个 Task dispatch 一个 fresh subagent 执行；
2. **executing-plans（备选）**：主代理串行执行，带 checkpoint。

## 注意事项

- 计划必须**自包含**：执行者不需要任何会话上下文就能照做。
- 计划里不得出现"酌情/看情况"这类词，每个决策点都要写死。
