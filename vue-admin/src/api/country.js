import request from '@/utils/request'

export function getListCountry() {
  return request({
    url: '/country/listing',
    method: 'get'
  })
}
