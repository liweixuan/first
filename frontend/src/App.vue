<script setup lang="ts">
import { RouterLink, RouterView } from 'vue-router'
</script>

<template>
  <el-container class="layout">
    <el-aside width="200px" class="aside">
      <div class="brand">Skeleton</div>
      <el-menu default-active="/" router>
        <el-menu-item index="/">
          <RouterLink to="/">首页</RouterLink>
        </el-menu-item>
        <el-menu-item index="/demo-items">
          <RouterLink to="/demo-items">示例资源</RouterLink>
        </el-menu-item>
      </el-menu>
    </el-aside>
    <el-main class="main">
      <RouterView />
    </el-main>
  </el-container>
</template>

<style scoped>
.layout {
  height: 100vh;
}
.aside {
  border-right: 1px solid var(--el-border-color);
}
.brand {
  padding: 16px;
  font-weight: 700;
  font-size: 18px;
}
.main {
  padding: 24px;
}
</style>
