package cn.tedu.meimall.common.enumerator;

/**
 * 業務狀態碼
 */
public enum ServiceCode {

    /**
     * 操作成功
     */
    OK(20000),
    /**
     * 錯誤：請求參數格式錯誤
     */
    ERROR_BAD_REQUEST(40000),
    /**
     * 錯誤：未認證
     */
    ERROR_UNAUTHORIZED(40100),
    /**
     * 錯誤：未認證，因為被禁用
     */
    ERROR_UNAUTHORIZED_DISABLED(40101),
    /**
     * 錯誤：禁止訪問，用於無權限
     */
    ERROR_FORBIDDEN(40300),
    /**
     * 錯誤：資料不存在
     */
    ERROR_NOT_FOUND(40400),
    /**
     * 錯誤：資料衝突
     */
    ERROR_CONFLICT(40900),
    /**
     * 錯誤：未知的插入資料失敗
     */
    ERROR_INSERT(50000),
    /**
     * 錯誤：未知的删除資料失敗
     */
    ERROR_DELETE(50100),
    /**
     * 錯誤：未知的修改資料失敗
     */
    ERROR_UPDATE(50200),
    /**
     * 錯誤：JWT已過期
     */
    ERR_JWT_EXPIRED(60000),
    /**
     * 錯誤：JWT驗證簽名失敗，可能使用了偽造的JWT
     */
    ERR_JWT_SIGNATURE(60100),
    /**
     * 錯誤：JWT格式錯誤
     */
    ERR_JWT_MALFORMED(60200),
    /**
     * 錯誤：上傳的文件為空（没有選擇有效的文件）
     */
    ERROR_UPLOAD_EMPTY(90000),
    /**
     * 錯誤：上傳的文件類型有誤
     */
    ERROR_UPLOAD_INVALID_TYPE(90100),
    /**
     * 錯誤：上傳的文件超出限制
     */
    ERROR_UPLOAD_EXCEED_MAX_SIZE(90200),
    /**
     * 錯誤：其它異常
     */
    ERROR_UNKNOWN(99999);

    /**
     * 枚舉對象的值
     */
    private Integer value;

    ServiceCode(Integer value) {
        this.value = value;
    }

    /**
     * 獲取枚舉對象的值
     *
     * @return 枚舉對象的值
     */
    public Integer getValue() {
        return value;
    }

}
