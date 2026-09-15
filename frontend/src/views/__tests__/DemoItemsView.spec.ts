import { beforeEach, describe, expect, it, vi } from 'vitest'
import { flushPromises, mount } from '@vue/test-utils'
import { createPinia, setActivePinia } from 'pinia'
import ElementPlus from 'element-plus'
import DemoItemsView from '../DemoItemsView.vue'
import { fetchDemoItems } from '@/api/demo'

vi.mock('@/api/demo', () => ({
  fetchDemoItems: vi.fn(),
}))

describe('DemoItemsView', () => {
  beforeEach(() => {
    setActivePinia(createPinia())
    vi.clearAllMocks()
  })

  it('挂载后加载后端数据并在页面展示列表与总记录数', async () => {
    vi.mocked(fetchDemoItems).mockResolvedValue({
      records: [
        { id: 1, name: '条目一', description: '种子数据 1', createdAt: '2026-09-15T00:00:00' },
        { id: 2, name: '条目二', description: '种子数据 2', createdAt: '2026-09-15T00:00:01' },
      ],
      total: 2,
      current: 1,
      size: 10,
    })

    const wrapper = mount(DemoItemsView, {
      global: { plugins: [ElementPlus] },
    })
    await flushPromises()

    expect(fetchDemoItems).toHaveBeenCalled()
    expect(wrapper.text()).toContain('共 2 条')
    expect(wrapper.findAll('.el-table__row')).toHaveLength(2)
    expect(wrapper.text()).toContain('条目一')
    expect(wrapper.text()).toContain('条目二')
  })
})
