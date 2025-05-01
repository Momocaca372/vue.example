<template>
  <div class="container viewarea">
	  <h2>ERP Vue</h2>
	  <form @submit.prevent="login" class="form-area form-control mb-3" >
	    <input v-model="username" type="text" name="username"  placeholder="使用者名稱" class="form-control mb-3"/>
	    <input v-model="password" type="password" name="password" placeholder="密碼" class="form-control" /><br>
	    <button class="btn btn-primary" type="submit">登入</button>
	    <p>{{ message }}</p>
	  </form>
	  <div class="form-check text-start my-3">
	        <input class="form-check-input" type="checkbox" v-model="rememberMe" id="checkDefault">
	        <label class="form-check-label" for="checkDefault">
	          Remember me
	        </label>
	      </div>
	  <br>
	  <div >
	  <a href="./register" class= "link">註冊</a>
	  </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      username: '',
      password: '',
      message: '',
	  rememberMe: false // Track the state of the "Remember me" checkbox
    };
  },
  methods: {
    async login() {
      try {
        const res = await axios.post('/api/auth/login', {
          username: this.username,
          password: this.password
        },{
		    withCredentials: true // 如果你的驗證涉及 Cookie，請保留此項
		});

		const msg = res.data.message;
		this.message = msg;
		if (msg=="登入成功"){
			this.$router.push('/dashboard')	
		}
        
      } catch (err) {
        console.error(err);
        this.message = '伺服器異常，請稍後在試';
		// Handle "Remember me" functionality
		}
        if (this.rememberMe) {
          localStorage.setItem('username', this.username);
        } else {
          localStorage.removeItem('username');
        }	       
     }
  },
  mounted() {
    // Pre-fill the username and password if stored in localStorage
    this.username = localStorage.getItem('username') || '';

   }
		  
  }; 

</script>
