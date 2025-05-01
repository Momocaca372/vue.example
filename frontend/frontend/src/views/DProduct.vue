<template>
  <div>
    <!-- 表格、功能按鈕等等 -->
	<div class="container-fluid">
	  <div class="row">
	    <div class="col-md-12">
	      <div class="p-1 bg-secondary text-white">
			
			<div class="container-fluid">
				<div class="d-flex align-items-center">
				  <input type="text" class="form-control me-2" placeholder="輸入商品查詢關鍵字">
				  <button class="btn btn-primary"><small>查詢</small></button>
				</div>
			  <div class="row">
			    <div class="col-md-12">
			      <div class=" bg-secondary text-white"  style="padding-top: 1em;">
			        <div class="button-container status-buttons">
					  <label class="status-label">Filter:</label>
			          <button class="filter-button">全部</button>
			          <button class="filter-button">確認設計</button>
			          <button class="filter-button">已報價</button>
			          <button class="filter-button">製作中</button>
			          <button class="filter-button">待出貨</button>
			          <button class="filter-button">已出貨</button>
					  <button class="filter-button">暫停</button>
					  <button class="filter-button">下架</button>
			        </div>
			        <div class="button-container status-buttons">
			          <label class="status-label">Order:</label>
			          <button class="order-button">日期</button>
			          <button class="order-button">價格</button>
			          <button class="order-button">數量</button>
			          <button class="order-button">名稱</button>
			        </div>

			      </div>
			    </div>
			  </div>
			  
			</div>

	      </div>
	    </div>
	  </div>
	  
	<div class="row ">
	  <div class="card-subviewarea">
	    <div class="bg-light position-relative">
	      <div id="scrollspy-container" class="position-relative" >
	        <div id="scrollspy-content" class="p-3">
				<div class="product-card ">
					<h5>Product 1</h5>
				</div>
				<div class="product-card ">
					<h5>Product 2</h5>
				</div>
				<div class="product-card ">
					<h5>Product 3</h5>
				</div>
				<div class="product-card ">
					<h5>Product 4</h5>
				</div>
				<div class="product-card ">
					<h5>Product 5</h5>
				</div>
	        </div>
	      </div>
	    </div>
	  </div>
    </div>

	</div>
  </div>
  
 
</template>
