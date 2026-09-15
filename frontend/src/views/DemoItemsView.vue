<script setup lang="ts">
import { onMounted } from 'vue'
import { storeToRefs } from 'pinia'
import { useDemoStore } from '@/stores/demo'

const store = useDemoStore()
const { records, total } = storeToRefs(store)

onMounted(() => {
  store.fetchPage(1, 10)
})
</script>

<template>
  <div>
    <h2>示例资源</h2>
    <p>共 {{ total }} 条</p>
    <el-table :data="records" border>
      <el-table-column prop="id" label="ID" width="80" />
      <el-table-column prop="name" label="名称" />
      <el-table-column prop="description" label="描述" />
      <el-table-column prop="createdAt" label="创建时间" />
    </el-table>
    <el-pagination
      layout="total, prev, pager, next"
      :total="total"
      :page-size="10"
      @current-change="(page: number) => store.fetchPage(page, 10)"
    />
  </div>
</template>
