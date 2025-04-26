<template>

  <form id="product-form" @submit.prevent="handleSubmit">

    <div class="form-floating mb-3">
	  <input v-model="form.name" type="text" id="name" class="form-control" required />
	  <label for="name" class="form-label">產品名稱</label>
  </div>

    <div class="form-floating mb-3">
      <input v-model.number="form.price" type="number" id="price" class="form-control" required />
	  <label for="price" class="form-label">價格</label>
    </div>

    <div class="form-floating mb-3">
      <input v-model.number="form.amount" type="number" id="amount" class="form-control" required />
	  <label for="amount" class="form-label">數量</label>
	</div>

    <div class="form-floating mb-3">
      <select v-model="form.status" id="status" class="form-select" required>
        <option value="">請選擇</option>
        <option value="DESIGN_CONFIRMED">確認設計</option>
        <option value="QUOTED">已報價</option>
		<option value="IN_PRODUCTION">製作中</option>
		<option value="READY_TO_SHIP">待出貨</option>
		<option value="SHIPPED">已出貨</option>
		<option value="PAUSED">暫停</option>
		<option value="DISCONTINUED">下架</option>
      </select>
	  <label for="status" class="form-label">狀態</label>
    </div>

    <button type="submit" class="btn btn-primary">送出</button>
  </form>

</template>

<script setup>
import { ref } from 'vue'
import axios from 'axios'

// 表單資料
const form = ref({
  name: '',
  price: null,
  amount: null,
  status: ''
})

// 提交處理
const handleSubmit = async () => {
  try {
    const response = await axios.post('/api/product/create', form.value, {
      withCredentials: true
    })
    console.log('成功送出', response.data)
    alert('新增成功 ✅')
    // 清空表單
    form.value = {
      name: '',
      price: null,
      amount: null,
      status: ''
    }
  } catch (error) {
    console.error('送出錯誤', error.response || error)
    alert('新增失敗 ❌')
  }
}
</script>
