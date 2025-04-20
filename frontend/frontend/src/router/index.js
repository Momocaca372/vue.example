import { createRouter, createWebHistory } from 'vue-router'
import Login from '../views/Login.vue'
import Register from '../views/Register.vue'
import Dashboard from '../views/Dashboard.vue'
import DProduct from '../views/DProduct.vue'
const routes = [
  {
    path: '/',
    redirect: '/login' // 預設導向登入
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/register',
    name: 'Register',
    component: Register
  },
  {
    path: '/dashboard',
    name: 'Dashboard',
    component: Dashboard,
	children: [
	  {
	    path: '/product',
	    component: DProduct
	  }
	  
    ]
  },
  
  
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
