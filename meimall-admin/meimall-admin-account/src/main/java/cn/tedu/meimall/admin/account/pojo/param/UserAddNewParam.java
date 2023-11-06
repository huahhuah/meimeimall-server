package cn.tedu.meimall.admin.account.pojo.param;

import cn.tedu.meimall.common.validation.BaseRules;
import cn.tedu.meimall.common.validation.account.UserRules;
import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * 添加用戶的參數類
 */
@Data
public class UserAddNewParam implements Serializable, UserRules {

    /**
     * 用戶名
     */
    @NotEmpty(message = MESSAGE_USERNAME_NOT_NULL)
    @Pattern(regexp = PATTERN_USERNAME, message = MESSAGE_USERNAME_PATTERN)
    private String username;

    /**
     * 密碼（原文）
     */
    @NotEmpty(message = MESSAGE_PASSWORD_NOT_NULL)
    @Pattern(regexp = PATTERN_PASSWORD, message = MESSAGE_PASSWORD_PATTERN)
    private String password;

    /**
     * 頭像URL
     */
    private String avatar;

    /**
     * 手機號碼
     */
    private String phone;

    /**
     * 電子信箱
     */
    private String email;

    /**
     * 描述
     */
    private String description;

    /**
     * 是否啟用，1=啟用，0=未啟用
     */
    @NotNull(message = MESSAGE_ENABLE_NOT_NULL)
    @Min(value = BaseRules.RANGE_ENABLE_MIN, message = MESSAGE_ENABLE_MIN)
    @Max(value = BaseRules.RANGE_ENABLE_MAX, message = MESSAGE_ENABLE_MAX)
    private Integer enable;

    /**
     * 用戶的角色ID的數組
     */
    @NotNull(message = MESSAGE_ROLE_IDS_NOT_NULL)
    @Size(min = UserRules.SIZE_ROLE_IDS_MIN, message = MESSAGE_ROLE_IDS_MIN_SIZE)
    private Long[] roleIds;
}
