# AGENTS.md —— AI 编码代理总指令

本文件是本项目的入口纪律，任何 AI 编码代理在本项目中工作时必须遵守。
灵感来自 superpowers 的 `using-superpowers` Skill：**只要有 1% 可能某个流程适用，就必须先走流程。**

## 三条 Iron Law（铁律）

1. **NO CODE WITHOUT APPROVED DESIGN**
   未经过 brainstorming 产出并获用户批准的设计文档，禁止写任何生产代码、禁止 scaffold 项目。
   违反字面规则即违反规则精神（Violating the letter of the rules is violating the spirit of the rules）。

2. **NO PRODUCTION CODE WITHOUT A FAILING TEST FIRST**
   写生产代码前必须先写失败测试并亲眼看到 RED。写早了的代码必须 delete + start over，不允许"留作参考"。

3. **NO COMPLETION CLAIMS WITHOUT FRESH VERIFICATION EVIDENCE**
   声称"完成/修好/通过"前必须现场 RUN 验证命令、READ 输出、VERIFY 一致。
   "看起来 / 应该 / 大概"都是红旗词。没有新鲜证据的成功声明等于撒谎。

## 会话生命周期

- **会话开始**：必须先执行 [harness/lifecycle/SESSION-START.md](harness/lifecycle/SESSION-START.md)：
  读最近会话日志、检测重复工作、检查待处理反馈、确认 openspec 变更状态。
- **会话结束**：必须执行 [harness/lifecycle/SESSION-END.md](harness/lifecycle/SESSION-END.md)：
  验证声明、更新会话日志、记录未解决问题与反馈。

## 目录职责

| 目录 | 职责 | 谁写 |
| --- | --- | --- |
| `harness/` | 外层代理治理：会话状态、报告、反馈 | 代理（每会话） |
| `openspec/specs/` | 当前现实规格（source of truth） | 仅 archive 时合并 |
| `openspec/changes/` | 提议变更（proposal/specs delta/design/tasks） | 代理（变更生命周期内） |
| `openspec/changes/archive/` | 已完成变更 | archive 动作移入 |
| `superpowers/specs/` | 设计文档（brainstorming 产出） | 代理（HARD-GATE 批准后） |
| `superpowers/plans/` | 执行计划（writing-plans 产出） | 代理 |
| `superpowers/skills/` | 流程纪律定义 | 项目维护者 |
| `src/` | 生产代码 | 仅 TDD 流程内 |
| `tests/` | 测试代码 | 先于生产代码 |

## 优先级顺序

1. **流程 Skill 优先**：brainstorming、writing-plans、TDD、systematic-debugging 等流程纪律先于一切。
2. **实施 Skill 其次**：具体技术实现（框架、工具用法）在流程决定"怎么干"之后再考虑。
3. **隔离优先**：正式开发动作先进入隔离 workspace（worktree），
   未经用户明确同意不得直接在 main/master 上实施。

## 失败处置

- 同一个修复连续失败 3 次：停止修补，质疑架构与假设，回到 systematic-debugging 的根因分析，
  而不是尝试第 4 次修补。
- 出现需要修复计划的失败时，按 [harness/reports/repair-plan-template.md](harness/reports/repair-plan-template.md)
  写入修复计划，并在反馈日志中记录问题与绑定修复的关系。
