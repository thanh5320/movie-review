import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'/'el-icon-x' the icon show in the sidebar
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },

  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [{
      path: 'dashboard',
      name: 'Dashboard',
      component: () => import('@/views/dashboard/index'),
      meta: { title: 'Dashboard', icon: 'dashboard' }
    }]
  },

  // {
  //   path: '/quanlyphim',
  //   component: Layout,
  //   redirect: '/quanlyphim/table',
  //   name: 'Quản lý phim',
  //   meta: { title: 'Quản lý phim', icon: 'el-icon-s-help' },
  //   children: [
  //     {
  //       path: 'table',
  //       name: 'Table',
  //       component: () => import('@/views/table/index'),
  //       meta: { title: 'Table', icon: 'table' }
  //     },
  //     {
  //       path: 'tree',
  //       name: 'Tree',
  //       component: () => import('@/views/tree/index'),
  //       meta: { title: 'Tree', icon: 'tree' }
  //     }
  //   ]
  // },

  // {
  //   path: '/form',
  //   component: Layout,
  //   children: [
  //     {
  //       path: 'index',
  //       name: 'Form',
  //       component: () => import('@/views/form/index'),
  //       meta: { title: 'Form', icon: 'form' }
  //     }
  //   ]
  // },

  {
    path: '/quanlyphim',
    component: Layout,
    name: 'Quản lý phim',
    meta: {
      title: 'Quản lý phim',
      icon: 'nested'
    },
    children: [
      {
        path: 'list',
        name: 'Danh sách phim',
        component: () => import('@/views/table/movie'),
        meta: { title: 'Danh sách phim', icon: 'table' }
      },
      {
        path: 'add',
        component: () => import('@/views/form/movie'),
        name: 'Thêm phim',
        meta: { title: 'Thêm phim', icon: 'form' }
      },
      {
        path: 'list_category',
        name: 'Danh sách thể loại',
        component: () => import('@/views/table/category'),
        meta: { title: 'Danh sách thể loại', icon: 'table' }
      },
      {
        path: 'add_category',
        component: () => import('@/views/form/category'),
        name: 'Thêm thể loại',
        meta: { title: 'Thêm thể loại', icon: 'form' }
      },
      {
        path: 'list_actor',
        name: 'Danh sách diễn viên',
        component: () => import('@/views/table/actor'),
        meta: { title: 'Danh sách diễn viên', icon: 'table' }
      },
      {
        path: 'add_actor',
        component: () => import('@/views/form/actor'),
        name: 'Thêm diễn viên',
        meta: { title: 'Thêm diễn viên', icon: 'form' }
      }

    ]
  },
  {
    path: '/quanlytaikhoan',
    component: Layout,
    name: 'Quản lý tài khoản',
    meta: {
      title: 'Quản lý tài khoản',
      icon: 'nested'
    },
    children: [
      {
        path: 'list',
        name: 'Danh sách tài khoản',
        component: () => import('@/views/table/account'),
        meta: { title: 'Danh sách tài khoản', icon: 'table' }
      },
      {
        path: 'add',
        component: () => import('@/views/form/account'),
        name: 'Thêm tài khoản',
        meta: { title: 'Thêm tài khoản', icon: 'form' }
      }
    ]
  },
  // {
  //   path: 'external-link',
  //   component: Layout,
  //   children: [
  //     {
  //       path: 'https://panjiachen.github.io/vue-element-admin-site/#/',
  //       meta: { title: 'External Link', icon: 'link' }
  //     }
  //   ]
  // },

  // 404 page must be placed at the end !!!
  { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
