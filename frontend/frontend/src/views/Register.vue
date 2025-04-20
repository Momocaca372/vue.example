<template>
  <div class="container">
    <h2>ERP 註冊</h2>
    <form @submit.prevent="register" class="form-area form-control mb-3">
      <input v-model="form.username" placeholder="使用者名稱" class="form-control mb-3" />
      <input type="password" v-model="form.password" placeholder="密碼" class="form-control" @input="validatePassword" />
      <small v-if="errors.password" class="text-danger">{{ errors.password }}</small><br>
      <button class="btn btn-primary" type="submit":disabled="errors.password !=''">註冊</button>
    </form>
    <p>{{ message }}</p>
    <br />
    <div>
      <a href="./login" class="link">登入</a>
    </div>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  data() {
    return {
      form: {
        username: '',
        password: ''
      },
      errors: {
        password: ''
      },
      message: ''
    }
  },
  methods: {
    validatePassword() {
      const pattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d).{8,}$/
      this.errors.password = this.form.password.length === 0
        ? '密碼不能為空'
        : !pattern.test(this.form.password)
          ? '格式錯誤，需大小寫英數值8碼以上'
          : ''
    },
    async register() {

      this.validatePassword()
      if (this.errors.password) return

      try {
        const res = await axios.post('/api/auth/register', {
          username: this.form.username,
          password: this.form.password
        },{
	      withCredentials: true // 如果你的驗證涉及 Cookie，請保留此項
	    });

        this.message = res.data.message
      } catch (err) {
        console.error(err)
        this.message = '註冊失敗，請稍後再試'
      }
    }
  }
}
</script>
