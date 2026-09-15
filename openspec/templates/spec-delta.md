# Specs Delta 模板（spec-delta.md）

> 复制到 `openspec/changes/<change-id>/specs/<capability>.md`。
> 文件名 = 受影响的能力名（kebab-case）。
> 合并时按需求（Requirement）粒度做语义合并。

---

# Delta Spec: <capability 能力名>

<!-- 新增需求：归档时整体追加到 specs/<capability>.md -->

## ADDED Requirements

### Requirement: <新增需求名>

系统必须……

#### Scenario: <场景名>

- **WHEN** <触发条件>
- **THEN** <预期行为>

---

<!-- 修改需求：归档时替换 specs/<capability>.md 中的同名需求 -->

## MODIFIED Requirements

### Requirement: <被修改的需求名>

> 说明：<改了什么、为什么>

系统必须……

#### Scenario: <更新后的场景名>

- **WHEN** <触发条件>
- **THEN** <预期行为>

---

<!-- 删除需求：归档时从 specs/<capability>.md 中移除同名需求 -->

## REMOVED Requirements

### Requirement: <被删除的需求名>

> 删除理由：<为什么不再需要>
