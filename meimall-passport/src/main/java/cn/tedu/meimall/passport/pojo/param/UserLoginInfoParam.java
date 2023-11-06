package cn.tedu.meimall.passport.pojo.param;


import cn.tedu.meimall.common.validation.account.UserRules;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 用戶登入的參數類
 *
 */
@Data
public class UserLoginInfoParam implements Serializable, UserRules {

    /**
     * 用戶名
     */
    @NotNull(message = MESSAGE_USERNAME_NOT_NULL)
    @Pattern(regexp = PATTERN_USERNAME, message = MESSAGE_USERNAME_PATTERN)
    @ApiModelProperty(value = "用戶名", required = true, example = "root")
    private String username;

    /**
     * 密碼（原文）
     */
    @NotNull(message = MESSAGE_PASSWORD_NOT_NULL)
    @Pattern(regexp = PATTERN_PASSWORD, message = MESSAGE_PASSWORD_PATTERN)
    @ApiModelProperty(value = "密碼", required = true, example = "123456")
    private String password;

}
