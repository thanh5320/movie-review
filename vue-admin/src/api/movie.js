import request from '@/utils/request'

export function getListMovie(params) {
  return request({
    url: '/movie/listing',
    method: 'get',
    params
  })
}

export function createMovie(params) {
  return request({
    url: '/movie/store',
    method: 'post',
    data: params
  })
}
