import { createApp } from 'vue'
import './style.css'
import App from './App.vue'
import $ from 'jquery'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap'
import router from './router'
// 在 main.js 中導入
import 'bootstrap/dist/css/bootstrap.css';

// 如果您想使用 Bootstrap 的 JS 功能（例如 Modal、Tooltip 等），可以額外導入 Bootstrap 的 JS：
import 'bootstrap/dist/js/bootstrap.bundle.js';
import './static/App.css'; // Import the CSS file


window.$ = $
window.jQuery = $

import axios from 'axios';

axios.defaults.withCredentials = true; // ⬅️ 讓瀏覽器送 Cookie（必須）

async function bootstrap() {
  try {
    const res = await axios.get('/api/csrf-token');
    const csrfHeaderName = res.data.headerName;
    const csrfToken = res.data.token;

    console.log('[CSRF]', csrfHeaderName, csrfToken.length);

    // 將 CSRF token 加入所有 axios 請求
    axios.interceptors.request.use(config => {
      config.headers[csrfHeaderName] = csrfToken;
	  console.log('[CSRF]', csrfHeaderName, csrfToken.length);
      return config;
    });
  } catch (err) {
    console.warn('[CSRF] 無法取得 token：', err);
  }

  const app = createApp(App);
  app.use(router).mount('#app');
}

bootstrap(); // ✅ 確保一切先取得 CSRF 再初始化 Vue