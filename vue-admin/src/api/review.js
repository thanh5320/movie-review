import request from '@/utils/request'

export function getListReview(params) {
  return request({
    url: '/review/listing',
    method: 'get',
    params
  })
}
