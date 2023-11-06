# 關於此項目佔用的通訊埠

- 19801：單點登入
- 19082：附件上傳管理
- 19080：地址管理

- 19180：帳號後臺管理
- 19181：資訊(内容)後臺管理
- 19182：商城後臺管理

- 19280：帳號前臺管理
- 19281：資訊(内容)前臺管理
- 19282：商城前臺管理



### 相關軟體
- **Java 1.8**
  - 强烈建議使用1.8版本，無論是哪種JDK均可，如果使用較高版本，可能存在啟動項目時因為缺少`javax`包下的内容而失敗。
- **MySQL 5.5或以上版本**
  - MySQL或MariaDB均可
- **Redis**
  - 這是一款基於内存的資料庫軟體，使用它可以非常明顯的提升資料查詢效率，並保障關係型資料庫的可用性
  - 使用任意版本均可
- **elasticsearch 7.6.2**
  - 這是一款文檔資料庫軟體，使用它可以解決關係型資料庫無法高效的處理搜索的相關問題
- **Node.js**
  - 這是學習Vue腳手架的必要軟體
  - 使用任意版本均可

### 相關框架

- **基礎框架**
  - 包括：Spring / Spring MVC / MyBatis / Spring Boot / Spring Validation / Spring Boot / Lombok / Knife4j
- **spring-boot-starter-aop**
  - 實現Spring AOP編程的框架
- **spring-boot-starter-data-redis**
  - 實現Redis編程的框架
- **spring-boot-starter-data-elasticsearch**
  - 實現elasticsearch編程的框架
- **mybatis-plus-boot-starter**
  - 整合Spring Boot與MyBatis Plus的框架，MyBatis Plus是一款中國人開發的基於MyBatis的無侵入性框架，可以提高資料庫編程的效率
- **druid-spring-boot-starter**
  - 整合Spring Boot與Druid的框架，可以應用Druid資料庫連接池
- **jjwt**
  - 處理JWT的框架
- **fastjson**
  - 處理對象與JSON字符串互相轉換的框架


## 項目介绍


這是一個後臺管理的平台，其内容主要包括：手做蛋糕手繪圖稿相關資訊(文章)並且設置評論互動以及甜點相館商品的呈現與販售


本項目將分為2個專案來開發，分别是前臺、後端。

前臺倉庫： https://github.com/huahhuah/meimall-admin-client.git



