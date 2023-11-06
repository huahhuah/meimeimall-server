package cn.tedu.meimall.front.account.pojo.param;

import cn.tedu.meimall.common.validation.account.UserRules;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.lang.NonNull;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 用戶註冊資料的參數類
 */
@Data
public class UserRegisterParam implements Serializable, UserRules {

    /**
     * 用戶名
     */
    @NotNull(message = MESSAGE_USERNAME_NOT_NULL)
    @Pattern(regexp = PATTERN_USERNAME, message = MESSAGE_USERNAME_PATTERN)
    @ApiModelProperty(value = "用户名", required = true, example = "test")
    private String username;

    /**
     * 密碼(原文)
     */
    @NotNull(message = MESSAGE_PASSWORD_NOT_NULL)
    @Pattern(regexp = PATTERN_PASSWORD, message = MESSAGE_PASSWORD_PATTERN)
    @ApiModelProperty(value = "密碼", required = true, example = "123456")
    private String password;

    /**
     * 用戶頭像URL
     */
    @ApiModelProperty(value = "頭像URL")
    private String avatar;

    /**
     * 手機號碼
     */
    @NotNull(message = MESSAGE_PHONE_NOT_NULL)
    @Pattern(regexp = PATTERN_PHONE, message = MESSAGE_PHONE_PATTERN)
    @ApiModelProperty(value = "手機號碼" ,required = true, example = "0912345678")
    private String phone;

    /**
     * 電子信箱
     */
    @NotNull(message = MESSAGE_EMAIL_NOT_NULL)
    @Pattern(regexp = PATTERN_EMAIL, message = MESSAGE_EMAIL_PATTERN)
    @ApiModelProperty(value = "電子信箱", required = true , example = "test@gmail.com")
    private String email;

}
