-- 商城相關數據表

DROP TABLE IF EXISTS mall_category;
CREATE TABLE mall_category
(
    id           BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '數據ID',
    name         VARCHAR(32)      DEFAULT '' COMMENT '類别名稱',
    parent_id    BIGINT UNSIGNED  DEFAULT 0 COMMENT '父級類别ID，如果無父級，則為0',
    depth        TINYINT UNSIGNED DEFAULT 0 COMMENT '深度，最頂級類别的深度為1，次級為2，以此類推',
    keywords     VARCHAR(255)     DEFAULT '' COMMENT '關鍵詞列表，各關鍵詞使用英文的逗號分隔',
    sort         TINYINT UNSIGNED DEFAULT 0 COMMENT '排序序號',
    icon         VARCHAR(255)     DEFAULT '' COMMENT '圖标圖片的URL',
    enable       TINYINT UNSIGNED DEFAULT 0 COMMENT '是否啟用，1=啟用，0=未啟用',
    is_parent    TINYINT UNSIGNED DEFAULT 0 COMMENT '是否為父級（是否包含子級），1=是父級，0=不是父級',
    is_display   TINYINT UNSIGNED DEFAULT 0 COMMENT '是否顯示在導航欄中，1=啟用，0=未啟用',
    gmt_create   DATETIME         DEFAULT NULL COMMENT '數據創建時間',
    gmt_modified DATETIME         DEFAULT NULL COMMENT '數據最後修改時間',
    PRIMARY KEY (id)
) COMMENT '商城-類别' CHARSET = utf8mb4;

DROP TABLE IF EXISTS mall_goods;
CREATE TABLE mall_goods
(
    id                     BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '數據ID',
    category_id            BIGINT UNSIGNED  DEFAULT 0 COMMENT '類别ID',
    category_name          VARCHAR(32)      DEFAULT '' COMMENT '類别名稱',
    bar_code               VARCHAR(32)      DEFAULT '' COMMENT '商品條型碼',
    title                  VARCHAR(255)     DEFAULT '' COMMENT '標題',
    brief                  VARCHAR(255)     DEFAULT '' COMMENT '摘要',
    cover_url              VARCHAR(255)     DEFAULT '' COMMENT '封面圖',
    sale_price             DECIMAL(10, 2)   DEFAULT 0 COMMENT '價格',
    keywords               VARCHAR(255)     DEFAULT '' COMMENT '關鍵詞列表，各關鍵詞使用英文的逗號分隔',
    sort                   TINYINT UNSIGNED DEFAULT 0 COMMENT '排序序號',
    is_recommend           TINYINT UNSIGNED DEFAULT 0 COMMENT '是否推薦，0=不推薦，1=推薦',
    check_state            TINYINT UNSIGNED DEFAULT 0 COMMENT '審核狀態，0=未審核，1=審核通過，2=拒绝審核',
    is_put_on              TINYINT UNSIGNED DEFAULT 0 COMMENT '是否上架，0=下架，1=上架',
    sales_count            INT UNSIGNED     DEFAULT 0 COMMENT '銷量',
    comment_count          INT UNSIGNED     DEFAULT 0 COMMENT '評論數量',
    positive_comment_count INT UNSIGNED     DEFAULT 0 COMMENT '好評數量',
    negative_comment_count INT UNSIGNED     DEFAULT 0 COMMENT '差評數量',
    gmt_create             DATETIME         DEFAULT NULL COMMENT '數據創建時間',
    gmt_modified           DATETIME         DEFAULT NULL COMMENT '數據最後修改時間',
    PRIMARY KEY (id)
) COMMENT '商城-商品' CHARSET = utf8mb4;

DROP TABLE IF EXISTS mall_goods_detail;
CREATE TABLE mall_goods_detail
(
    id           BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '數據ID',
    goods_id     BIGINT UNSIGNED DEFAULT 0 COMMENT '商品ID',
    detail       TEXT COMMENT '詳情',
    gmt_create   DATETIME        DEFAULT NULL COMMENT '數據創建時間',
    gmt_modified DATETIME        DEFAULT NULL COMMENT '數據最後修改時間',
    PRIMARY KEY (id)
) COMMENT '商城-商品詳情' CHARSET = utf8mb4;

DROP TABLE IF EXISTS mall_comment;
CREATE TABLE mall_comment
(
    id           BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '數據ID',
    author_id    BIGINT UNSIGNED  DEFAULT 0 COMMENT '作者ID',
    author_name  VARCHAR(32)      DEFAULT '' COMMENT '作者名字',
    goods_id     BIGINT UNSIGNED  DEFAULT 0 COMMENT '商品ID',
    type         TINYINT UNSIGNED DEFAULT 0 COMMENT '評論類型：0=好評，1=中評，2=差評',
    content      VARCHAR(512)     DEFAULT '' COMMENT '評論内容',
    check_state  TINYINT UNSIGNED DEFAULT 0 COMMENT '審核狀態，0=未審核，1=審核通過，2=拒绝審核',
    gmt_create   DATETIME         DEFAULT NULL COMMENT '數據創建時間',
    gmt_modified DATETIME         DEFAULT NULL COMMENT '數據最後修改時間',
    PRIMARY KEY (id)
) COMMENT '商城-評論' CHARSET = utf8mb4;

DROP TABLE IF EXISTS mall_check_log;
CREATE TABLE mall_check_log
(
    id             BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '數據ID',
    resource_type  TINYINT UNSIGNED DEFAULT 0 COMMENT '資源類型，0=商品，1=評論',
    resource_id    BIGINT UNSIGNED  DEFAULT 0 COMMENT '資源ID',
    resource_brief VARCHAR(255)     DEFAULT '' COMMENT '資源摘要，截取的商品標題或評論',
    original_state TINYINT UNSIGNED DEFAULT 0 COMMENT '原審核狀態',
    new_state      TINYINT UNSIGNED DEFAULT 0 COMMENT '新審核狀態',
    check_user_id  BIGINT UNSIGNED  DEFAULT 0 COMMENT '審核人ID',
    check_username VARCHAR(32)      DEFAULT '' COMMENT '審核人用戶名',
    check_remarks  VARCHAR(64)      DEFAULT '' COMMENT '審核備註',
    gmt_check      DATETIME         DEFAULT NULL COMMENT '審核時間',
    gmt_create     DATETIME         DEFAULT NULL COMMENT '數據創建時間',
    gmt_modified   DATETIME         DEFAULT NULL COMMENT '數據最後修改時間',
    PRIMARY KEY (id)
) COMMENT '商城-審核日誌' CHARSET = utf8mb4;

DROP TABLE IF EXISTS mall_receiver_address;
CREATE TABLE mall_receiver_address
(
    id             BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '數據ID',
    user_id        BIGINT UNSIGNED  DEFAULT 0 COMMENT '用戶ID',
    receiver_name  VARCHAR(32)      DEFAULT '' COMMENT '收貨人',
    receiver_phone VARCHAR(32)      DEFAULT '' COMMENT '收貨電話',
    province       VARCHAR(256)     DEFAULT '' COMMENT '省名稱',
    province_code  VARCHAR(32)      DEFAULT '' COMMENT '省邊碼',
    city           VARCHAR(256)     DEFAULT '' COMMENT '市名稱',
    city_code      VARCHAR(32)      DEFAULT '' COMMENT '市邊碼',
    area           VARCHAR(256)     DEFAULT '' COMMENT '區名稱',
    area_code      VARCHAR(32)      DEFAULT '' COMMENT '區邊碼',
    detail         VARCHAR(128)     DEFAULT '' COMMENT '詳細地址',
    is_default     TINYINT UNSIGNED DEFAULT 0 COMMENT '是否默認',
    gmt_create     DATETIME         DEFAULT NULL COMMENT '數據創建時間',
    gmt_modified   DATETIME         DEFAULT NULL COMMENT '數據最後修改時間',
    PRIMARY KEY (id)
) COMMENT '商城-收貨地址' CHARSET = utf8mb4;

DROP TABLE IF EXISTS mall_order;
CREATE TABLE mall_order
(
    id               BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '數據ID',
    buyer_id         BIGINT UNSIGNED  DEFAULT 0 COMMENT '用戶ID',
    buyer_username   VARCHAR(50)      DEFAULT '' COMMENT '用戶名',
    order_no         VARCHAR(50)      DEFAULT '' COMMENT '訂單編號',
    receiver_name    VARCHAR(32)      DEFAULT '' COMMENT '收貨人',
    receiver_phone   VARCHAR(32)      DEFAULT '' COMMENT '收貨電話',
    receiver_address VARCHAR(255)     DEFAULT '' COMMENT '收貨地址',
    goods_num        INT UNSIGNED     DEFAULT 0 COMMENT '商品數量',
    total_price      DECIMAL(10, 2)   DEFAULT 0 COMMENT '商品銷售總價',
    logistics_no     VARCHAR(50)      DEFAULT '' COMMENT '物流單號',
    pay_channel      INT UNSIGNED     DEFAULT 0 COMMENT '支付渠道：1=支付寶，2=微信',
    pay_method       INT UNSIGNED     DEFAULT 0 COMMENT '支付方式：1=在线支付，2=貨到付款',
    order_state      TINYINT UNSIGNED DEFAULT 0 COMMENT '訂單狀態: 0=待支付，1=已支付，待發貨, 2=已發貨/待收貨，3=確認收貨/已完成，4=用戶關閉，5=平台關閉(商家)，6=系統調度關閉',
    gmt_pay          DATETIME         DEFAULT NULL COMMENT '支付時間',
    gmt_create       DATETIME         DEFAULT NULL COMMENT '數據創建時間',
    gmt_modified     DATETIME         DEFAULT NULL COMMENT '數據最後修改時間',
    PRIMARY KEY (id)
) COMMENT '商城-訂單' CHARSET = utf8mb4;

DROP TABLE IF EXISTS mall_order_item;
CREATE TABLE mall_order_item
(
    id              BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '數據ID',
    order_id        BIGINT UNSIGNED DEFAULT 0 COMMENT '訂單ID',
    goods_id        BIGINT UNSIGNED DEFAULT 0 COMMENT '商品ID',
    goods_cover_url VARCHAR(100)    DEFAULT '' COMMENT '商品圖片',
    goods_title     VARCHAR(100)    DEFAULT '' COMMENT '商品標題',
    goods_num       INT UNSIGNED    DEFAULT 0 COMMENT '商品數量',
    sale_unit_price DECIMAL(10, 2)  DEFAULT 0 COMMENT '商品單價',
    gmt_create      DATETIME        DEFAULT NULL COMMENT '數據創建時間',
    gmt_modified    DATETIME        DEFAULT NULL COMMENT '數據最後修改時間',
    PRIMARY KEY (id)
) COMMENT '商城-訂單商品明細' CHARSET = utf8mb4;
