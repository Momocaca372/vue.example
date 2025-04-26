# ERP System Prototype

一個簡易的 ERP 系統原型，提供商品與標籤管理、使用者登入驗證與資料同步功能。

## ✅ 已完成功能

1. **系統初始化與框架搭建**
   - Spring Boot + Spring Security
   - Vue 前端整合

2. **登入介面**
   - CSRF 設定調適

3. **商品與標籤管理 API**
   - RESTful 設計
   - 商品/標籤增刪改查
   - 同步機制（支援 lastModified + Optimistic Lock 重試）

## ⌛ 開發中功能

1. 商品註冊/編輯/刪除頁面（前端）
2. 使用者密碼重設 + 信箱驗證功能
3. 盈餘記錄頁面（可查詢出貨商品與總金額）
4. Docker 打包與 AWS 部署

## 🛠 技術棧

- **後端**: Spring Boot, Spring Data JPA, JWT, Hibernate
- **前端**: Vue.js, Axios, Element Plus, jQuery
- **資料庫**: MySQL
- **部署**: Docker, AWS EC2 (開發中)

## 🧪 API 測試

建議使用 Postman 或 Swagger 進行測試，所有 API 都遵循 RESTful 原則。

## 📌 專案目標

7 天內完成一個具有基本功能的 ERP 原型系統，支援商品與標籤管理、用戶驗證與同步機制，並可部署至雲端展示。
