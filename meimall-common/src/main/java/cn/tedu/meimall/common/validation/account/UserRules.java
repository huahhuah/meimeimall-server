package cn.tedu.meimall.common.validation.account;

import cn.tedu.meimall.common.validation.BaseRules;

/**
 * 用戶資料相關規則配置
 */
public interface UserRules extends BaseRules {

    // ====== 【用户名】 =====

    /**
     * 正則：用户名
     */
    String PATTERN_USERNAME = "^[a-zA-Z]{1}[a-zA-Z0-9_]{3,14}$";
    /**
     * 驗證失敗描述文本：正則：用户名
     */
    String MESSAGE_USERNAME_PATTERN = "用户名必須由4~15長度的字母、數组、下滑線組成，且第1個字符必須是字母";
    /**
     * 驗證失敗描述文本：非NULl：用户名
     */
    String MESSAGE_USERNAME_NOT_NULL = "請提交用户名";

    // ====== 【密碼】 =====

    /**
     * 正則：密碼
     */
    String PATTERN_PASSWORD = "^.{4,15}$";
    /**
     * 驗證失敗描述文本：正則：密碼
     */
    String MESSAGE_PASSWORD_PATTERN = "密碼必須是4~15長度的字元组成";
    /**
     * 驗證失敗描述文本：非NULl：密碼
     */
    String MESSAGE_PASSWORD_NOT_NULL = "請提交密碼";

    // ====== 【手機號碼】 =====

    /**
     * 正則：手機號碼
     */
    String PATTERN_PHONE = "^09[0-9]{8}$";
    /**
     * 驗證失敗描述文本：正則：手機號碼
     */
    String MESSAGE_PHONE_PATTERN = "手機號碼必須是11位的纯數字";
    /**
     * 驗證失敗描述文本：非NULl：手機號碼
     */
    String MESSAGE_PHONE_NOT_NULL = "請提交手機號碼";

    // ====== 【電子信箱】 =====

    /**
     * 正則：電子信箱
     */
    String PATTERN_EMAIL = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    /**
     * 驗證失敗描述文本：正則：電子信箱
     */
    String MESSAGE_EMAIL_PATTERN = "請输入正确格式的電子信箱";
    /**
     * 驗證失敗描述文本：非NULl：電子信箱
     */
    String MESSAGE_EMAIL_NOT_NULL = "請提交電子信箱";

    // ====== 【角色列表】 =====

    /**
     * 驗證失敗描述文本：非NULl：角色列表
     */
    String MESSAGE_ROLE_IDS_NOT_NULL = "請至少選擇1種角色";
    /**
     * 長度：角色列表：最小值
     */
    int SIZE_ROLE_IDS_MIN = 1;
    /**
     * 驗證失敗描述文本：最小值：角色列表
     */
    String MESSAGE_ROLE_IDS_MIN_SIZE = "請至少選擇1種角色";
}
