import { describe, expect, it } from 'vitest'
import { mount } from '@vue/test-utils'
import ElementPlus from 'element-plus'
import App from '@/App.vue'

describe('App 布局', () => {
  it('渲染导航区（首页/示例资源）与内容区', () => {
    const wrapper = mount(App, {
      global: {
        plugins: [ElementPlus],
        stubs: {
          RouterLink: { template: '<a><slot /></a>' },
          RouterView: { template: '<div class="router-view" />' },
        },
      },
    })

    expect(wrapper.text()).toContain('首页')
    expect(wrapper.text()).toContain('示例资源')
    expect(wrapper.find('.router-view').exists()).toBe(true)
  })
})
