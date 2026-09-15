# Finishing a Branch —— 收尾

## 触发时机

所有 Task 完成、final review 通过后。

## 前置条件

必须先完成 `verification-before-completion`：现场跑全套测试，0 failures 才有资格收尾。

## 四选一菜单

向用户呈现，按选择执行：

| 选项 | 动作 | 后续 |
| --- | --- | --- |
| **1. Merge** | 回到主仓库，merge 分支，验证，删除分支 | 按 provenance 决定是否删 worktree |
| **2. Push & PR** | `gh pr create`，写好 Summary + Test Plan | **保留 worktree**，等 PR 反馈 |
| **3. Keep** | 保留分支与 worktree，不清理 | 会话日志标注"保留原因" |
| **4. Discard** | 丢弃改动，清理分支 | 必须经用户明确确认 |

## 清理纪律

- 删除 worktree 前判断 provenance：只清理**本流程创建**的目录，
  不碰 harness 或其他工具自己管理的目录。
- 无论选哪项，都必须更新 `harness/reports/` 会话日志；
  若为 openspec 变更，按顺序执行 archive（delta 合并 + 移入 archive/）。
