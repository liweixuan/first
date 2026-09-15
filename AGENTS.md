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
| `backend/` | **Git submodule**：Spring Boot 业务后端 | submodule 独立 commit（详见下节） |
| `frontend/` | **Git submodule**：Vue3 + TS SPA 前端 | submodule 独立 commit（详见下节） |
| `src/` | 跨子模块实验性代码（一般不使用） | 仅原型场景 |
| `tests/` | 跨子模块实验性测试（一般不使用） | 仅原型场景 |

## Git Submodule 工作流

本项目是三层仓库结构：

| 仓库层 | 远程仓库 | 内容 |
| --- | --- | --- |
| **Workspace（本仓库）** | https://github.com/liweixuan/first.git | orchestration 层：`harness/`、`openspec/`、`superpowers/`、`.gitmodules`、`AGENTS.md` + 两个 submodule 指针 |
| **`backend/`** (submodule) | https://github.com/liweixuan/first_backend.git | Java 17 + Spring Boot + MyBatis-Plus + MySQL/H2 + Maven + JUnit/Mockito |
| **`frontend/`** (submodule) | https://github.com/liweixuan/first_frontend.git | Vue 3 + TypeScript + Vite + Pinia + Element Plus + Vitest + axios |

`harness/`、`openspec/`、`superpowers/` 留在 workspace 仓库本体（不分子模块）。

### 首次克隆 workspace

```bash
git clone <workspace-url> my-first-project
cd my-first-project
git submodule update --init --recursive
```

**未跑 `submodule update --init` 时，`backend/` 与 `frontend/` 是空目录**——CI / 新成员 onboarding 必须显式 init。

### 在子模块内开发

```bash
cd backend           # 进入独立仓库工作树
git checkout main
# 修改文件……
git add . && git commit -m "feat: ..."
git push origin main
cd ..

cd frontend          # 同样模式
```

子模块有独立 commit 历史与 remote。**禁止在 workspace 根直接编辑 `backend/` 或 `frontend/` 内的文件**——下次 `git submodule update` 会覆盖本地改动。

### workspace 升级子模块指针

当 backend/frontend 推送了新 commit 后，回到 workspace 根记录新引用：

```bash
git add backend frontend
git commit -m "chore: bump submodule refs to <reason>"
```

workspace 的 commit 仅记录 submodule 引用版本与 orchestration 层（spec、harness）变更，不混入子模块代码改动。

### 跨子模块的 OpenSpec 协调

OpenSpec 变更同时涉及 backend 与 frontend 时：

- `proposal.md` / `design.md` / `specs/<capability>/spec.md` / `tasks.md` 在 **workspace 仓库** 写（spec 是跨模块契约）
- 后端实现细节在 `backend/` 独立 commit（Java 类型、Mapper、Controller）
- 前端实现细节在 `frontend/` 独立 commit（TS 类型、Store、View、API client）
- workspace 的 commit 仅承载 spec 文档 + submodule 指针 bump
- `tasks.md` 中 1 个子模块任务对应 1 个独立 PR（submodule 仓库 PR），不是 workspace 1 个 commit

### 排错指引

| 症状 | 原因 | 处理 |
| --- | --- | --- |
| `backend/` 或 `frontend/` 为空 | 未 init submodule | `git submodule update --init --recursive` |
| `git submodule status` 显示 `-` 前缀 | submodule 未初始化 | 同上 |
| submodule 内 commit 找不到 branch | detached HEAD | `cd backend && git checkout main` |
| workspace commit 想纳入 backend 代码改动 | 违反 submodule 边界 | revert workspace 改动，去 backend/ 内独立 commit |

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