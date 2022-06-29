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

export function updateMovie(params) {
  return request({
    url: '/movie/update',
    method: 'post',
    data: params
  })
}

export function deleteMovie(params) {
  return request({
    url: `movie/delete/${params.id}`,
    method: 'post'
  })
}
