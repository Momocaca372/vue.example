<template>
  <form @submit.prevent="login"  class="container">
	<h2>登入</h2>
    <input v-model="username" type="text" name="username"  placeholder="帳號" class="form-floating"/><br>
    <input v-model="password" type="password" name="password" placeholder="密碼" class="form-floating" /><br>
    <button class="btn btn-primary" type="submit">登入</button>
    <p>{{ message }}</p>
  </form>
  <div class="form-check text-start my-3">
        <input class="form-check-input" type="checkbox" value="remember-me" id="checkDefault">
        <label class="form-check-label" for="checkDefault">
          Remember me
        </label>
      </div>
  <br>
  
  	<a href="./register">註冊</a>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      username: '',
      password: '',
      message: ''
    };
  },
  methods: {
    async login() {
      try {
        const res = await axios.post('/api/auth/login', {
          username: this.username,
          password: this.password
        }, {
          withCredentials: true
        });

		const msg = res.data.message;
		this.message = msg;

        // 可以在這裡導向到主頁，例如 this.$router.push('/dashboard')
      } catch (err) {
        console.error(err);
        this.message = '登入失敗，請稍後再試';
        
      }
    }
  }
};
</script>
