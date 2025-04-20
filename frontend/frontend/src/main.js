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



const app = createApp(App);
app.use(router).mount('#app');