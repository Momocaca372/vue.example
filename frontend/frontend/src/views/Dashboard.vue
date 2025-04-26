<template>
  <div class="container upperBanner custom-gradient-shadow shadow mb-2 rounded">
    <h2>Welcome Manager Dashboard</h2>
  </div>

  <div class="container mainspace">
    <div class="card shadow subspace">
      <!-- dropdown 區塊 -->
      <div class="dropdown-wrapper py-3 px-3 d-flex flex-wrap gap-3">
        <!-- 使用者管理 -->
        <div class="dropdown">
          <button class="btn btn-secondary dropdown-toggle" type="button" id="userDropdown" data-bs-toggle="dropdown">
            使用者管理
          </button>
          <ul class="dropdown-menu" aria-labelledby="userDropdown">
            <li><a class="dropdown-item" href="#">重設密碼</a></li>
            <li><a class="dropdown-item" href="#">重新驗證信箱</a></li>
			<li><a class="dropdown-item" href="./login">登出</a></li>
          </ul>
        </div>
        <!-- 產品管理 -->
        <div class="dropdown">
          <button class="btn btn-secondary dropdown-toggle" type="button" id="productDropdown" data-bs-toggle="dropdown">
            產品管理
          </button>
          <ul class="dropdown-menu" aria-labelledby="productDropdown">
            <li><a class="dropdown-item" href="/product">產品查詢</a></li>
            <li><a class="dropdown-item" href="/product-create">新增產品</a></li>
          </ul>
        </div>
        <!-- 成本管理 -->
        <div class="dropdown">
          <button class="btn btn-secondary dropdown-toggle" type="button" id="costDropdown" data-bs-toggle="dropdown">
            成本管理
          </button>
          <ul class="dropdown-menu" aria-labelledby="costDropdown">
            <li><a class="dropdown-item" href="#">成本管理</a></li>
          </ul>
        </div>
      </div>

      <div class="card-body viewarea">
        <router-view />
      </div>
    </div>
  </div>
</template>
