# Test-Driven Development —— RED-GREEN-REFACTOR

## Iron Law

> **NO PRODUCTION CODE WITHOUT A FAILING TEST FIRST**
> 没有先失败的测试，不允许写生产代码。
> 写早了的代码必须 delete + start over，不允许"留作参考"。

## 循环

```
RED    写一条失败测试 → 运行 → 确认失败（且失败原因符合预期）
GREEN  写最小实现让它通过 → 运行 → 确认通过
REFACTOR 清理代码 → 运行 → 仍全绿
```

每个循环完成后 commit。

## 细则

- 测试必须针对**真实行为**，不是 mock 对 mock。
- 一次只推进一个微小增量（complexity reduction）。
- REFACTOR 阶段不许改变行为；想加新行为，先写新测试。
- 若测试"意外通过"，说明测试没测到目标行为，回头检查测试本身。

## 与 debugging 的衔接

修 bug 时：先写一条稳定复现 bug 的失败测试（RED），
再写最小修复（GREEN），最后还原修复确认测试回到 RED（regression 验证）。
