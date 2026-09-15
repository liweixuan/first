import { beforeEach, describe, expect, it, vi } from 'vitest'
import { ElMessage } from 'element-plus'
import { BizApiError, handleRequestError, unwrapEnvelope } from '../request'

vi.mock('element-plus', () => ({
  ElMessage: { error: vi.fn() },
}))

describe('request 封装', () => {
  beforeEach(() => {
    vi.clearAllMocks()
  })

  it('code=0 时返回 data', () => {
    const data = unwrapEnvelope({ code: 0, message: 'success', data: { records: [] } })
    expect(data).toEqual({ records: [] })
    expect(ElMessage.error).not.toHaveBeenCalled()
  })

  it('code 非 0 时弹出 message 并抛出异常（Promise 被 reject）', () => {
    expect(() => unwrapEnvelope({ code: 4001, message: '资源不存在', data: null }))
      .toThrow(BizApiError)
    expect(ElMessage.error).toHaveBeenCalledWith('资源不存在')
  })

  it('网络/HTTP 错误时弹出通用提示并抛出异常', () => {
    expect(() => handleRequestError(new Error('Network Error'))).toThrowError('网络异常，请稍后重试')
    expect(ElMessage.error).toHaveBeenCalledWith('网络异常，请稍后重试')
  })
})
