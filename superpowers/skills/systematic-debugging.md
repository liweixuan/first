# Systematic Debugging —— 四阶段根因定位

## Iron Law

> **NO FIXES WITHOUT ROOT CAUSE INVESTIGATION FIRST**
> 未完成根因调查，禁止动手修复。

## 四阶段

### Phase 1：复现（Root Cause）

- 完整阅读 error 信息与 stack trace；
- 尝试**稳定复现**；无法稳定复现，先解决复现问题；
- 查看最近 commit，多组件系统在每个 component boundary 加诊断日志。

### Phase 2：找差异（Pattern）

找一个**正常工作的参照**（旧版本 / 类似功能 / 官方示例），逐项对比差异，
缩小可疑面。

### Phase 3：假设（Hypothesis）

形成**单一、可证伪**的根因假设（不是多重猜测），
并用最小化实验测试该假设。

### Phase 4：修复（Implementation）

- 必须先写一条复现 bug 的失败测试（用 `test-driven-development`）；
- 写最小修复，看测试 GREEN；
- 还原修复，确认测试回到 RED（证明测试真的在测这个 bug）。

## 失败上限

同一个修复连续失败 **3 次**：停止修补。质疑架构与假设，
回到 Phase 1 重新调查，而不是尝试第 4 次修补。

## 收尾

修复后必须经过 `verification-before-completion`（全套测试 0 failures），
并走一次 `requesting-code-review`，确认没有引入新问题。
