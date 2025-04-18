<template>
  <div>
    <h2>註冊</h2>
    <form @submit.prevent="register" class="container">
      <input v-model="username" placeholder="使用者名稱" class="form-floating"/><br>
      <input type="password" v-model="password" placeholder="密碼" class="form-floating"/><br>
      <button class="btn btn-primary" type="submit">註冊</button>
    </form>
    <p>{{ message }}</p>
  </div>
  <br>
  	<a href="./login">登入</a>
</template>

<script>
import axios from 'axios'

export default {
  data() {
    return {
      username: '',
      password: '',
      message: ''
    }
  },
  methods: {
    async register() {
      try {
        const res = await axios.post('/api/auth/register', {
		    username: this.username,
		    password: this.password
		}, {
		    withCredentials: true, // 確保攜帶 cookie
			
		});
		const msg = res.data.message;
		this.message = msg;
      } catch (err) {
        console.error(err);
		}
    }
  }
}
</script>
