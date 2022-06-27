import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/auth/login',
    method: 'post',
    data
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

export function logout() {
  return request({
    url: '/auth/logout',
    method: 'post'
  })
}
