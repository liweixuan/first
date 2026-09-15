import { beforeEach, describe, expect, it, vi } from 'vitest'
import { createPinia, setActivePinia } from 'pinia'
import { useDemoStore } from '../demo'
import { fetchDemoItems } from '@/api/demo'

vi.mock('@/api/demo', () => ({
  fetchDemoItems: vi.fn(),
}))

describe('demo store', () => {
  beforeEach(() => {
    setActivePinia(createPinia())
    vi.clearAllMocks()
  })

  it('查询成功写入 records 与 total', async () => {
    vi.mocked(fetchDemoItems).mockResolvedValue({
      records: [{ id: 1, name: '条目一', description: '种子数据', createdAt: '2026-09-15T00:00:00' }],
      total: 3,
      current: 1,
      size: 10,
    })

    const store = useDemoStore()
    await store.fetchPage(1, 10)

    expect(fetchDemoItems).toHaveBeenCalledWith(1, 10)
    expect(store.records).toHaveLength(1)
    expect(store.records[0]?.name).toBe('条目一')
    expect(store.total).toBe(3)
  })
})
