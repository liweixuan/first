# Superpowers 层 —— 工程纪律

本目录把 [obra/superpowers](https://github.com/obra/superpowers) 的完整软件工程方法论
落地为本项目的流程纪律。14 个 Skill 的精髓浓缩为 9 个核心流程 Skill，
存于 `skills/` 目录。

## 四句哲学

- **Test-Driven Development**：没有先失败的测试，不允许写生产代码。
- **Systematic over ad-hoc**：按流程走，不临时发挥。
- **Complexity reduction**：每一步只做一件小事（2–5 分钟粒度）。
- **Evidence over claims**：没有现场运行证据，禁止声称"完成/修好/通过"。

## Skill 清单（14 → 9 核心骨架）

| Skill 文件 | 来源 Skill | 定位 |
| --- | --- | --- |
| `skills/brainstorming.md` | brainstorming | 策划：Socratic 对话出设计，HARD-GATE |
| `skills/writing-plans.md` | writing-plans | 策划：拆 2–5 分钟 bite-sized tasks |
| `skills/using-git-worktrees.md` | using-git-worktrees | 隔离：进入 worktree，验证 baseline |
| `skills/test-driven-development.md` | test-driven-development | 执行：RED-GREEN-REFACTOR 铁律 |
| `skills/executing-plans.md` | subagent-driven-development + executing-plans + dispatching-parallel-agents | 执行：按计划逐 task 推进 |
| `skills/systematic-debugging.md` | systematic-debugging | 调试：4 阶段根因定位 |
| `skills/requesting-code-review.md` | requesting-code-review + receiving-code-review | 评审：两阶段评审 + 收到评审的纪律 |
| `skills/verification-before-completion.md` | verification-before-completion | 收尾：验证门 |
| `skills/finishing-a-branch.md` | finishing-a-development-branch | 收尾：Merge/PR/Keep/Discard 四选一 |

（writing-skills 与 using-superpowers 为元 Skill，其纪律已并入根目录 `AGENTS.md`。）

## 与 OpenSpec 层的衔接

| Superpowers 产出 | 去向 |
| --- | --- |
| `specs/YYYY-MM-DD-<topic>-design.md`（brainstorming 批准后） | 同步为 `openspec/changes/<change-id>/design.md` |
| `plans/YYYY-MM-DD-<topic>-plan.md`（writing-plans 产出） | 同步为 `openspec/changes/<change-id>/tasks.md` |

## 触发原则

**只要有 1% 可能某个 Skill 适用，就必须先走该 Skill 流程。**
流程 Skill 优先于实施 Skill（具体技术实现）。
