-- 資訊相關數據表

DROP TABLE IF EXISTS content_category;
CREATE TABLE content_category
(
    id           BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '數據ID',
    name         VARCHAR(16)      DEFAULT '' COMMENT '類别名稱',
    keywords     VARCHAR(255)     DEFAULT '' COMMENT '關鍵詞列表，各關鍵詞使用英文的逗號分隔',
    sort         TINYINT UNSIGNED DEFAULT 0 COMMENT '排序序號',
    enable       TINYINT UNSIGNED DEFAULT 0 COMMENT '是否啟用，1=啟用，0=未啟用',
    is_display   TINYINT UNSIGNED DEFAULT 0 COMMENT '是否顯示在導航欄中，1=啟用，0=未啟用',
    gmt_create   DATETIME         DEFAULT NULL COMMENT '數據創建時間',
    gmt_modified DATETIME         DEFAULT NULL COMMENT '數據最後修改時間',
    PRIMARY KEY (id)
) COMMENT ='内容-類别' CHARSET = utf8mb4;

DROP TABLE IF EXISTS content_article;
CREATE TABLE content_article
(
    id            BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '數據ID',
    author_id     BIGINT UNSIGNED  DEFAULT 0 COMMENT '作者ID',
    author_name   VARCHAR(32)      DEFAULT '' COMMENT '作者名字',
    category_id   BIGINT UNSIGNED  DEFAULT 0 COMMENT '類别ID',
    category_name VARCHAR(16)      DEFAULT '' COMMENT '類别名稱',
    title         VARCHAR(255)     DEFAULT '' COMMENT '標題',
    brief         VARCHAR(255)     DEFAULT '' COMMENT '摘要',
    cover_url     VARCHAR(255)     DEFAULT '' COMMENT '封面圖',
    keywords      VARCHAR(255)     DEFAULT '' COMMENT '關鍵詞列表，各關鍵詞使用英文的逗號分隔',
    ip            VARCHAR(32)      DEFAULT '' COMMENT 'IP',
    sort          TINYINT UNSIGNED DEFAULT 0 COMMENT '排序序號',
    up_count      INT UNSIGNED     DEFAULT 0 COMMENT '頂數量',
    down_count    INT UNSIGNED     DEFAULT 0 COMMENT '踩數量',
    click_count   INT UNSIGNED     DEFAULT 0 COMMENT '瀏覽量',
    comment_count INT UNSIGNED     DEFAULT 0 COMMENT '評論量',
    check_state   TINYINT UNSIGNED DEFAULT 0 COMMENT '審核狀態，0=未審核，1=審核通過，2=拒絕審核',
    is_display    TINYINT UNSIGNED DEFAULT 0 COMMENT '顯示狀態，0=不顯示，1=顯示',
    is_recommend  TINYINT UNSIGNED DEFAULT 0 COMMENT '是否推薦，0=不推薦，1=推薦',
    gmt_create    DATETIME         DEFAULT NULL COMMENT '數據創建時間',
    gmt_modified  DATETIME         DEFAULT NULL COMMENT '數據最後修改時間',
    PRIMARY KEY (id)
) COMMENT '内容-文章' CHARSET = utf8mb4;

DROP TABLE IF EXISTS content_article_detail;
CREATE TABLE content_article_detail
(
    id           BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '數據ID',
    article_id   BIGINT UNSIGNED DEFAULT 0 COMMENT '文章ID',
    detail       TEXT COMMENT '詳情',
    gmt_create   DATETIME        DEFAULT NULL COMMENT '數據創建時間',
    gmt_modified DATETIME        DEFAULT NULL COMMENT '數據最後修改時間',
    PRIMARY KEY (id)
) COMMENT '内容-文章詳情' CHARSET = utf8mb4;

DROP TABLE IF EXISTS content_up_down_log;
CREATE TABLE content_up_down_log
(
    id             BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '數據ID',
    author_id      BIGINT UNSIGNED  DEFAULT 0 COMMENT '作者ID',
    author_name    VARCHAR(32)      DEFAULT '' COMMENT '作者名字',
    resource_type  TINYINT UNSIGNED DEFAULT 0 COMMENT '資源類型，0=文章，1=評論',
    resource_id    BIGINT UNSIGNED  DEFAULT 0 COMMENT '資源ID',
    resource_brief VARCHAR(255)     DEFAULT '' COMMENT '資源摘要，截取的文章標題或評論',
    op_type        TINYINT UNSIGNED DEFAULT 0 COMMENT '操作類型，0=踩，1=頂',
    gmt_create     DATETIME         DEFAULT NULL COMMENT '數據創建時間',
    gmt_modified   DATETIME         DEFAULT NULL COMMENT '數據最後修改時間',
    PRIMARY KEY (id)
) COMMENT '内容-頂踩日誌' CHARSET = utf8mb4;

DROP TABLE IF EXISTS content_comment;
CREATE TABLE content_comment
(
    id             BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '數據ID',
    author_id      BIGINT UNSIGNED  DEFAULT 0 COMMENT '作者ID',
    author_name    VARCHAR(32)      DEFAULT '' COMMENT '作者名字',
    resource_type  TINYINT UNSIGNED DEFAULT 0 COMMENT '資源類型，0=文章，1=評論',
    resource_id    BIGINT UNSIGNED  DEFAULT 0 COMMENT '資源ID',
    resource_brief VARCHAR(255)     DEFAULT '' COMMENT '資源摘要，截取的文章標題或評論',
    content        VARCHAR(512)     DEFAULT '' COMMENT '評論内容',
    ip             VARCHAR(32)      DEFAULT '' COMMENT '踩數量',
    floor          INT UNSIGNED     DEFAULT 0 COMMENT '樓層',
    up_count       INT UNSIGNED     DEFAULT 0 COMMENT '頂數量',
    down_count     INT UNSIGNED     DEFAULT 0 COMMENT '踩數量',
    check_state    TINYINT UNSIGNED DEFAULT 0 COMMENT '審核狀態，0=未審核，1=審核通過，2=拒絕審核',
    is_display     TINYINT UNSIGNED DEFAULT 0 COMMENT '顯示狀態，0=不顯示，1=顯示',
    gmt_create     DATETIME         DEFAULT NULL COMMENT '數據創建時間',
    gmt_modified   DATETIME         DEFAULT NULL COMMENT '數據最後修改時間',
    PRIMARY KEY (id)
) COMMENT '内容-評論' CHARSET = utf8mb4;

DROP TABLE IF EXISTS content_check_log;
CREATE TABLE content_check_log
(
    id              BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '數據ID',
    resource_type   TINYINT UNSIGNED DEFAULT 0 COMMENT '資源類型，0=文章，1=評論',
    resource_id     BIGINT UNSIGNED  DEFAULT 0 COMMENT '資源ID',
    resource_brief  VARCHAR(255)     DEFAULT '' COMMENT '資源摘要，截取的文章標題或評論',
    original_state  TINYINT UNSIGNED DEFAULT 0 COMMENT '原審核狀態',
    new_state       TINYINT UNSIGNED DEFAULT 0 COMMENT '新審核狀態',
    check_user_id   BIGINT UNSIGNED  DEFAULT 0 COMMENT '審核人ID',
    check_username VARCHAR(32)      DEFAULT '' COMMENT '審核人用户名',
    check_remarks   VARCHAR(64)      DEFAULT '' COMMENT '審核備註',
    gmt_check       DATETIME         DEFAULT NULL COMMENT '審核時間',
    gmt_create      DATETIME         DEFAULT NULL COMMENT '數據創建時間',
    gmt_modified    DATETIME         DEFAULT NULL COMMENT '數據最後修改時間',
    PRIMARY KEY (id)
) COMMENT '内容-審核日誌' CHARSET = utf8mb4;
