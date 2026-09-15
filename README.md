# my-first-project

基于 **Harness + OpenSpec + Superpowers** 三大理念初始化的 AI Coding 项目目录。

本项目不是某个具体技术栈的应用，而是一套 **AI 编码代理的工作环境与开发纪律骨架**：
让 AI 编码代理像一支严肃的工程团队那样工作——先澄清需求、再写规格、拆计划、隔离执行、
TDD 开发、两阶段评审、验证后收尾，同时由外层 Harness 层持续记录会话状态与项目反馈。

## 三大理念

### ① Harness —— 外层代理协调层

`harness/` 目录是外层编码代理（outer coding-agent Harness）的治理层，负责：

| 要素 | 位置 | 说明 |
| --- | --- | --- |
| 生命周期控制 | `harness/lifecycle/` | 会话开始/结束钩子，控制代理状态边界 |
| 重复工作检测 | `harness/lifecycle/SESSION-START.md` | 每次会话先读最近会话日志，避免重做已完成工作 |
| 项目反馈回路 | `harness/feedback/` | 记录发现的问题并绑定到修复（finding-bound fixes） |
| 代理资产 | `harness/assets/` | 存放代理可复用的资产（提示词、检查清单等） |
| 会话结果 | `harness/reports/` | 每次会话的持久结果记录 |
| 修复计划 | `harness/reports/repair-plan-template.md` | 失败后的结构化修复计划 |
| 持久报告 | `harness/reports/` | 不随会话消失的 durable reports |

### ② OpenSpec —— 规格驱动开发（SDD）

参考 [Fission-Al/OpenSpec](https://github.com/Fission-Al/OpenSpec)，`openspec/` 目录实现：

- **现实与提议分离**：`openspec/specs/` 保存当前现实（source of truth），
  `openspec/changes/` 保存提议的修改，归档时才合并。
- **每个变更一个文件夹**：`changes/<change-id>/` 内含 proposal、specs delta、design、tasks 四类工件。
- **语义化 delta 合并**：spec delta 使用 `## ADDED Requirements` / `## MODIFIED Requirements` /
  `## REMOVED Requirements` 标记，按需求粒度合并。
- **动作而非阶段**：propose → apply → verify → archive 可按任意顺序调用，无状态机锁死。

### ③ Superpowers —— 工程纪律层

参考 [obra/superpowers](https://github.com/obra/superpowers)，`superpowers/` 目录把完整软件工程
方法论固化为可组合的流程 Skill，四句哲学：

- **Test-Driven Development**：没有先失败的测试，不允许写生产代码。
- **Systematic over ad-hoc**：按流程走，不临时发挥。
- **Complexity reduction**：每一步只做一件小事（2–5 分钟粒度）。
- **Evidence over claims**：没有现场运行证据，禁止声称"完成/修好/通过"。

## 核心工作流：一个想法到合并的完整链路

```
用户想法
   │
   ▼
① brainstorming（HARD-GATE：未批准设计不写代码）
   │  └─ 产出 → superpowers/specs/YYYY-MM-DD-<topic>-design.md
   ▼
② openspec propose：创建 changes/<change-id>/
   │  └─ 写入 proposal.md + specs/ 下的 delta + design.md
   ▼
③ writing-plans：把 spec 拆成 2–5 分钟的 bite-sized tasks
   │  └─ 产出 → superpowers/plans/YYYY-MM-DD-<topic>-plan.md
   ▼
④ using-git-worktrees：进入隔离 workspace，验证 baseline 全绿
   ▼
⑤ 执行（每个 task）：
   │  test-driven-development：RED（写失败测试）→ GREEN（最小实现）→ REFACTOR
   │  subagent-driven / executing-plans：fresh 上下文执行
   ▼
⑥ requesting-code-review：spec 符合性 + 代码质量两阶段评审
   ▼
⑦ verification-before-completion：现场跑测试，0 failures 才算过
   ▼
⑧ openspec archive：delta 合并进 specs/，change 移入 changes/archive/
   │
   ▼
⑨ harness 收尾：更新 session log，记录反馈，finishing-a-branch（Merge/PR/Keep/Discard）
```

## 目录结构

```
my-first-project/
├── README.md                      # 本文件：三大理念与工作流总览
├── AGENTS.md                      # AI 编码代理总指令（入口纪律）
├── .gitignore
├── harness/                       # ① Harness 层：外层代理协调
│   ├── README.md
│   ├── lifecycle/                 # 生命周期控制钩子
│   │   ├── SESSION-START.md       # 会话开始检查清单（含重复工作检测）
│   │   └── SESSION-END.md         # 会话结束检查清单
│   ├── reports/                   # 持久报告与会话结果
│   │   ├── session-log-template.md
│   │   └── repair-plan-template.md
│   ├── feedback/                  # 项目反馈回路
│   │   └── FEEDBACK-LOG.md
│   └── assets/                    # 代理资产
├── openspec/                      # ② OpenSpec 层：规格驱动开发
│   ├── config.yaml                # OpenSpec 配置
│   ├── specs/                     # 当前现实（source of truth）
│   ├── changes/                   # 活动变更（每变更一文件夹）
│   │   └── archive/               # 已完成变更归档
│   └── templates/                 # 变更工件模板
│       ├── proposal.md
│       ├── spec-delta.md
│       ├── design.md
│       └── tasks.md
├── superpowers/                   # ③ Superpowers 层：工程纪律
│   ├── README.md                  # 方法论总纲（14 Skill 说明）
│   ├── skills/                    # 核心流程 Skill
│   ├── specs/                     # brainstorming 产出的设计文档
│   └── plans/                     # writing-plans 产出的执行计划
├── src/                           # 生产代码
└── tests/                         # 测试代码
```

## 快速开始

1. 阅读 [AGENTS.md](AGENTS.md)，了解代理总纪律。
2. 每次会话开始时，代理必须先执行 `harness/lifecycle/SESSION-START.md` 检查清单。
3. 收到新需求时，从 `superpowers/skills/brainstorming.md` 开始，未批准设计不得写代码。
4. 会话结束时，代理必须执行 `harness/lifecycle/SESSION-END.md` 并更新持久报告。

## 参考项目

- OpenSpec：<https://github.com/Fission-Al/OpenSpec>
- Superpowers：<https://github.com/obra/superpowers>
