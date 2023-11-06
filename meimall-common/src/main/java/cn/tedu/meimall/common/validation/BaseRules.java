package cn.tedu.meimall.common.validation;

/**
 * 通用數據相關規則配置
 */
public interface BaseRules {

    // ====== 【啓用狀態】 =====

    /**
     * 區間：啓用狀態：最小值
     */
    int RANGE_ENABLE_MIN = 0;
    /**
     * 驗證失敗描述文本：啓用狀態最小值
     */
    String MESSAGE_ENABLE_MIN = "啓用狀態的值必須是0或1";

    /**
     * 區間：啓用狀態：最大值
     */
    int RANGE_ENABLE_MAX = 1;
    /**
     * 驗證失敗描述文本：啓用狀態最大值
     */
    String MESSAGE_ENABLE_MAX = "啓用狀態的值必須是0或1";

    /**
     * 驗證失敗描述文本：非NULl：啓用狀態
     */
    String MESSAGE_ENABLE_NOT_NULL = "請選擇用户的啓用狀態";
}
