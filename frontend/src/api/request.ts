import axios from 'axios'
import { ElMessage } from 'element-plus'

/** 后端统一信封结构 */
export interface ApiEnvelope<T = unknown> {
  code: number
  message: string
  data: T
}

/** 业务错误：code 非 0 时抛出，供调用方感知失败 */
export class BizApiError extends Error {
  readonly code: number

  constructor(code: number, message: string) {
    super(message)
    this.name = 'BizApiError'
    this.code = code
  }
}

/** 统一信封拆解：code=0 返回 data；否则弹出 message 并抛出业务错误 */
export function unwrapEnvelope<T>(body: ApiEnvelope<T>): T {
  if (body.code !== 0) {
    ElMessage.error(body.message || '请求失败')
    throw new BizApiError(body.code, body.message)
  }
  return body.data
}

/** 网络/HTTP 错误处理：弹出通用提示并抛出（Promise 被 reject） */
export function handleRequestError(error: unknown): never {
  void error
  ElMessage.error('网络异常，请稍后重试')
  throw new Error('网络异常，请稍后重试')
}

/** 骨架 axios 实例：baseURL 走 Vite 代理 /api，统一信封与错误处理挂到拦截器 */
export const http = axios.create({
  baseURL: '/api',
  timeout: 10_000,
})

http.interceptors.response.use(
  (response) => {
    unwrapEnvelope(response.data as ApiEnvelope)
    return response
  },
  (error) => handleRequestError(error),
)
