-- 帳號相關數據表

DROP TABLE IF EXISTS account_user;
CREATE TABLE account_user
(
    id             BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '數據ID',
    username       VARCHAR(32)      DEFAULT '' COMMENT '用戶名',
    password       VARCHAR(128)     DEFAULT '' COMMENT '密碼（密文）',
    avatar         VARCHAR(255)     DEFAULT '' COMMENT '頭像URL',
    phone          VARCHAR(32)      DEFAULT '' COMMENT '手機號碼',
    email          VARCHAR(64)      DEFAULT '' COMMENT '電子信箱',
    description    VARCHAR(255)     DEFAULT '' COMMENT '簡介',
    enable         TINYINT UNSIGNED DEFAULT 0 COMMENT '是否啟用，1=啟用，0=未啟用',
    last_login_ip  VARCHAR(32)      DEFAULT '' COMMENT '最後登入IP地址（冗餘）',
    login_count    INT UNSIGNED     DEFAULT 0 COMMENT '累計登入次數（冗餘）',
    gmt_last_login DATETIME         DEFAULT NULL COMMENT '最後登入時間（冗餘）',
    gmt_create     DATETIME         DEFAULT NULL COMMENT '數據創建時間',
    gmt_modified   DATETIME         DEFAULT NULL COMMENT '數據最後修改時間',
    PRIMARY KEY (id)
) DEFAULT CHARSET = utf8mb4 COMMENT ='用戶';

DROP TABLE IF EXISTS account_role;
CREATE TABLE account_role
(
    id           BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '數據ID',
    name         VARCHAR(32)      DEFAULT '' COMMENT '名稱',
    description  VARCHAR(255)     DEFAULT '' COMMENT '簡介',
    sort         TINYINT UNSIGNED DEFAULT 0 COMMENT '排序序號',
    gmt_create   DATETIME         DEFAULT NULL COMMENT '數據創建時間',
    gmt_modified DATETIME         DEFAULT NULL COMMENT '數據最後修改時間',
    PRIMARY KEY (id)
) DEFAULT CHARSET = utf8mb4 COMMENT ='角色';

DROP TABLE IF EXISTS account_user_role;
CREATE TABLE account_user_role
(
    id           BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '數據ID',
    user_id      BIGINT UNSIGNED DEFAULT 0 COMMENT '用戶ID',
    role_id      BIGINT UNSIGNED DEFAULT 0 COMMENT '角色ID',
    gmt_create   DATETIME        DEFAULT NULL COMMENT '數據創建時間',
    gmt_modified DATETIME        DEFAULT NULL COMMENT '數據最後修改時間',
    PRIMARY KEY (id)
) DEFAULT CHARSET = utf8mb4 COMMENT ='用戶角色關聯';

DROP TABLE IF EXISTS account_permission;
CREATE TABLE account_permission
(
    id           BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '數據ID',
    name         VARCHAR(32)      DEFAULT '' COMMENT '名稱',
    value        VARCHAR(255)     DEFAULT '' COMMENT '值',
    description  VARCHAR(255)     DEFAULT '' COMMENT '簡介',
    sort         TINYINT UNSIGNED DEFAULT 0 COMMENT '排序序號',
    gmt_create   DATETIME         DEFAULT NULL COMMENT '數據創建時間',
    gmt_modified DATETIME         DEFAULT NULL COMMENT '數據最後修改時間',
    PRIMARY KEY (id)
) DEFAULT CHARSET = utf8mb4 COMMENT ='權限';

DROP TABLE IF EXISTS account_role_permission;
CREATE TABLE account_role_permission
(
    id            BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '數據ID',
    role_id       BIGINT UNSIGNED DEFAULT 0 COMMENT '角色ID',
    permission_id BIGINT UNSIGNED DEFAULT 0 COMMENT '權限ID',
    gmt_create    DATETIME        DEFAULT NULL COMMENT '數據創建時間',
    gmt_modified  DATETIME        DEFAULT NULL COMMENT '數據最後修改時間',
    PRIMARY KEY (id)
) DEFAULT CHARSET = utf8mb4 COMMENT ='角色權限關聯';

DROP TABLE IF EXISTS account_login_log;
CREATE TABLE account_login_log
(
    id           BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '數據ID',
    user_id      BIGINT UNSIGNED DEFAULT 0 COMMENT '用戶ID',
    username     VARCHAR(32)     DEFAULT '' COMMENT '用戶用戶名（冗餘）',
    ip           VARCHAR(32)     DEFAULT '' COMMENT '登入IP地址',
    user_agent   VARCHAR(255)    DEFAULT '' COMMENT '瀏覽器内核',
    gmt_login    DATETIME        DEFAULT NULL COMMENT '登入時間',
    gmt_create   DATETIME        DEFAULT NULL COMMENT '數據創建時間',
    gmt_modified DATETIME        DEFAULT NULL COMMENT '數據最後修改時間',
    PRIMARY KEY (id)
) DEFAULT CHARSET = utf8mb4 COMMENT ='用戶登入日誌';
