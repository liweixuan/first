# Executing Plans —— 按计划执行

## 触发时机

计划已就绪、已进入隔离 workspace、baseline 已全绿。

## 两种执行模式

### 模式 A：Subagent-driven（推荐）

每个 Task dispatch 一个 **fresh subagent**：

- fresh 上下文：只传递该 Task 所需的计划片段与文件路径，**不继承主会话历史**；
- 执行者必须走 `test-driven-development` 的 RED-GREEN-REFACTOR；
- 执行者报告 DONE 后，依次过两关：
  1. **spec 符合性评审**：实现与 spec 完全一致、不多不少；
  2. **代码质量评审**。
- 任一关有问题 → 返回执行者修改 → 重新评审，通过后才 mark complete。

### 模式 B：Executing-plans（无 subagent 能力时的备选）

主代理按计划逐个 Task 串行执行：

- 每完成一个 Task，先跑验证命令，再 commit，**checkpoint 明确**；
- 完成所有 Task 后，走 `requesting-code-review` 做整体评审。

## 并行例外

仅当存在 **3 个以上真正独立**的故障域/子系统时，才可并行派发多个执行代理。
相互关联的失败必须先一起调查，不得并行掩盖因果关系。
