import { defineStore } from 'pinia'
import { fetchDemoItems, type DemoItem } from '@/api/demo'

/** 示例资源集中式状态：列表数据从本 store 读取 */
export const useDemoStore = defineStore('demo', {
  state: () => ({
    records: [] as DemoItem[],
    total: 0,
  }),
  actions: {
    async fetchPage(page = 1, size = 10) {
      const data = await fetchDemoItems(page, size)
      this.records = data.records
      this.total = data.total
    },
  },
})
