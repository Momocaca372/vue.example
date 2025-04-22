<template>
  <div class="container upperBanner custom-gradient-shadow shadow p-10 mb-2 rounded">
    <h2 >Welcome Manager Dashboard</h2>
  </div>

  <div class="container mt-4 mainspace">
    <div class="card shadow subspace">


      <!-- dropdown 區塊 -->
	  <div class="dropdown-wrapper">
      <div class="d-flex gap-3 p-3">
        <div class="dropdown">
          <button
            class="btn btn-secondary dropdown-toggle"
            type="button"
            id="userDropdown"
            data-bs-toggle="dropdown"
            aria-expanded="false"
          >
            使用者管理
          </button>
          <ul class="dropdown-menu" aria-labelledby="userDropdown">
            <li><a class="dropdown-item" href="#">使用者管理</a></li>
            <li><a class="dropdown-item" href="#">產品管理</a></li>
            <li><a class="dropdown-item" href="#">成本管理</a></li>
            <li><hr class="dropdown-divider" /></li>
            <li><a class="dropdown-item" href="#">Help</a></li>
          </ul>
        </div>

        <div class="dropdown">
          <button
            class="btn btn-secondary dropdown-toggle"
            type="button"
            id="productDropdown"
            data-bs-toggle="dropdown"
            aria-expanded="false"
          >
            產品管理
          </button>
          <ul class="dropdown-menu" aria-labelledby="productDropdown">
            <li><a class="dropdown-item" href="#">使用者管理</a></li>
            <li><a class="dropdown-item" href="/product">產品管理</a></li>
            <li><a class="dropdown-item" href="#">成本管理</a></li>
            <li><hr class="dropdown-divider" /></li>
            <li><a class="dropdown-item" href="#">Help</a></li>
          </ul>
        </div>

        <div class="dropdown">
          <button
            class="btn btn-secondary dropdown-toggle"
            type="button"
            id="costDropdown"
            data-bs-toggle="dropdown"
            aria-expanded="false"
          >
            成本管理
          </button>
          <ul class="dropdown-menu" aria-labelledby="costDropdown">
            <li><a class="dropdown-item" href="#">使用者管理</a></li>
            <li><a class="dropdown-item" href="#">產品管理</a></li>
            <li><a class="dropdown-item" href="#">成本管理</a></li>
            <li><hr class="dropdown-divider" /></li>
            <li><a class="dropdown-item" href="#">Help</a></li>
          </ul>
        </div>
      </div>
	

      </div>
	  <div  class="card-body viewarea">
		<router-view></router-view>

	  </div>
    </div>
  </div>
</template>
