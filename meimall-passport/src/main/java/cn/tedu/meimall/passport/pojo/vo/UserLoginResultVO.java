package cn.tedu.meimall.passport.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 用戶登入結果的VO類
 *
 */
@Data
@Accessors(chain = true)
public class UserLoginResultVO implements Serializable {

    /**
     * 用戶ID
     */
    @ApiModelProperty("用戶ID")
    private Long id;

    /**
     * 用戶名
     */
    @ApiModelProperty("用戶名")
    private String username;

    /**
     * 頭像
     */
    @ApiModelProperty("頭像")
    private String avatar;

    /**
     * Token（JWT）
     */
    @ApiModelProperty("Token（JWT）")
    private String token;

    /**
     * 權限清單
     */
    @ApiModelProperty("權限清單")
    private String[] authorities;

}
