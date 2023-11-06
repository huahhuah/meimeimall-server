package cn.tedu.meimall.common.consts.data;

/**
 * 通用相關常量
 */
public interface CommonConsts {

    /**
     * 截取的簡介文本最大長度，通常用於記錄在日誌中
     */
    int BRIEF_MAX_LENGTH = 32;

    /**
     * 啓用狀態：禁用
     */
    int ENABLE_STATE_OFF = 0;
    /**
     * 啓用狀態：啓用
     */
    int ENABLE_STATE_ON = 1;
    /**
     * 資料“是否啓用”的狀態文本
     */
    String[] ENABLE_STATE_TEXT = {"禁用", "啓用"};

    /**
     * 顯示狀態：隱藏
     */
    int DISPLAY_STATE_OFF = 0;
    /**
     * 顯示狀態：顯示
     */
    int DISPLAY_STATE_ON = 1;
    /**
     * 資料“是否顯示”的狀態文本
     */
    String[] DISPLAY_STATE_TEXT = {"隱藏", "顯示"};

    /**
     * 審核狀態：未審核
     */
    int CHECK_STATE_UNCHECKED = 0;
    /**
     * 審核狀態：審核通過
     */
    int CHECK_STATE_PASS = 1;
    /**
     * 審核狀態：拒絕審核
     */
    int CHECK_STATE_REJECT = 2;
    /**
     * 資料“審核狀態”的狀態文本
     */
    String[] CHECK_STATE_TEXT = {"未審核", "審核通過", "拒絕審核"};
}
