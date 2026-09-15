# Using Git Worktrees —— 隔离工作区

## 触发时机

计划已就绪、即将开始执行前。

## 规则

**未经用户明确同意，不得在 main/master 上直接实施开发。**

## 流程

### Step 0：检测

是否已经在隔离 workspace（worktree）中？
- 是 → 直接进入 Step 2。
- 否 → 继续 Step 1。

### Step 1：创建隔离 workspace

优先使用 harness 自带的原生 worktree 工具；回退方案：

```bash
git worktree add .worktrees/<branch-name> -b <branch-name>
```

### Step 2：跑项目 setup

安装依赖（`npm install` / `pip install` / `cargo build` 等）。

### Step 3：验证 baseline

运行全套测试。**baseline 必须全绿**才能开始执行任务。
若 baseline 有失败，先记录到 FEEDBACK-LOG，解决后再继续。

## 后续

执行任务全程在 worktree 内进行；收尾用 `finishing-a-branch`。
