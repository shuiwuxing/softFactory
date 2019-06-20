import request from '@/utils/request'

//登录
export function login(data) {
  return request({
    url: '/login',
    method: 'post',
    data
  })
}
//登出
export function logout() {
  return request({
    url: '/logout',
    method: 'post'
  })
}
//获取用户列表
export function list(data) {
  return request({
    url: '/user/list',
    method: 'post',
    data
  })
}
//获取用户信息
export function getInfo(data) {
  return request({
    url: '/user/getinfo',
    method: 'post',
    data
  })
}
//新增用户信息
export function add(data) {
  return request({
    url: '/user/add',
    method: 'post',
    data
  })
}
//更新用户信息
export function update(data) {
  return request({
    url: '/user/update',
    method: 'post',
    data
  })
}
//删除用户信息
export function remove(data) {
  return request({
    url: '/user/remove',
    method: 'post',
    data
  })
}

