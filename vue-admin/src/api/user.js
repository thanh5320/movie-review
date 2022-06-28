import request from '@/utils/request'
import { getToken } from '@/utils/auth'

export function login(data) {
  return request({
    url: '/auth/login',
    method: 'post',
    data: data
  })
}

export function listingUser() {
  return request({
    url: '/admin/user/listing',
    method: 'get',
    headers: {
      Authorization: `Bearer ${getToken()}`
    }
  })
}

export function getInfo(token) {
  return request({
    url: '/user/me',
    method: 'get',
    headers: {
      Authorization: `Bearer ${token}`
    }
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
