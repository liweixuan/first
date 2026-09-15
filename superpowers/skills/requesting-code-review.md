# Requesting / Receiving Code Review —— 两阶段评审

## 触发时机

- 每个 Task 完成时（内置在 executing-plans 流程中）；
- 所有 Task 完成后，对整个 implementation 的 final review。

## 两阶段评审

每个完成的 Task，依次派发 **fresh reviewer**：

1. **Spec 符合性评审**：实现与设计文档/spec delta 完全一致——不多做、不少做；
2. **代码质量评审**：可读性、结构、边界条件、错误处理。

问题按 **Critical / Important / Minor** 分级：
Critical 必须修复后才能继续；Important 优先修复；Minor 视情况安排。

## 请求评审时

提供完整上下文：变更 ID、计划片段、`git diff`（BASE_SHA → HEAD_SHA）、验证输出。

## 收到评审时（纪律）

- 禁止表演性同意（"You're absolutely right!"/"Thanks for catching that!"）；
- **必须先 verify 再 implement**：确认问题真实存在、修复确实解决问题；
- 技术正确性高于社交舒适；不同意就说明理由，同意就用代码证明。
