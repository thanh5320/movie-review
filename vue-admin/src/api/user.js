import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/auth/login',
    method: 'post',
    data
  })
}

export function getInfo() {
  return request({
    url: '/user/me',
    method: 'get'
  })
}
export function updateUser(params) {
  return request({
    url: '/admin/user/update',
    method: 'post',
    data: params
  })
}
export function deleteUser(id) {
  return request({
    url: `/admin/user/delete/${id}`,
    method: 'post'
  })
}
export function createUser(params) {
  return request({
    url: '/admin/user/create',
    method: 'post',
    data: params
  })
}
export function logout() {
  return request({
    url: '/auth/logout',
    method: 'post'
  })
}
