# changes/ —— 活动变更

本目录保存**提议中的修改**，与现实（`openspec/specs/`）分离，归档时才合并。

## 变更生命周期

```
propose（创建变更文件夹）
   → apply（按 tasks 实施，TDD 开发）
   → verify（验证 gate：测试 + 与 delta 一致性检查）
   → archive（delta 合并进 specs/，变更移入 archive/）
```

动作而非阶段：可以随时回到 apply 继续改、随时补充 proposal，
没有状态机锁死；但 **archive 是一次性动作**，归档前必须通过 verify。

## 变更文件夹结构

```
changes/
└── <change-id>/           # 例：add-user-auth
    ├── proposal.md        # 提案：为什么、改什么、影响什么
    ├── design.md          # 设计：方案与权衡
    ├── tasks.md           # 任务拆分（bite-sized，TDD 顺序）
    └── specs/             # specs delta（每个受影响能力一个文件）
        └── auth.md        # 使用 ADDED/MODIFIED/REMOVED Requirements 标记
```

## 变更 ID 约定

- kebab-case，动词开头：`add-`、`fix-`、`refactor-`、`remove-`、`update-`
- 同一主题的新变更可加序号：`add-export-v2`

## 归档

归档时变更文件夹移入 `archive/2026-09-15-<change-id>/`，
delta 按需求粒度合并进 `specs/` 对应文件。

## 当前活动变更

（暂无。）
