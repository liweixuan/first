import { http, unwrapEnvelope, type ApiEnvelope } from './request'

/** 示例资源 */
export interface DemoItem {
  id: number
  name: string
  description: string | null
  createdAt: string
}

/** 后端分页结果契约 */
export interface PageData<T> {
  records: T[]
  total: number
  current: number
  size: number
}

/** 查询示例资源分页数据 */
export async function fetchDemoItems(page: number, size: number): Promise<PageData<DemoItem>> {
  const response = await http.get<ApiEnvelope<PageData<DemoItem>>>('/demo-items', {
    params: { page, size },
  })
  return unwrapEnvelope(response.data)
}
