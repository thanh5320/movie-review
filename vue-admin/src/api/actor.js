import request from '@/utils/request'

export function getListActor(params) {
  return request({
    url: '/actor/listing',
    method: 'get',
    params
  })
}

export function createActor(params) {
  return request({
    url: '/actor/store',
    method: 'post',
    data: params
  })
}

export function updateActor(params) {
  return request({
    url: '/actor/update',
    method: 'post',
    data: params
  })
}

export function deleteActor(params) {
  return request({
    url: `/actor/delete/${params}`,
    method: 'post'
  })
}
