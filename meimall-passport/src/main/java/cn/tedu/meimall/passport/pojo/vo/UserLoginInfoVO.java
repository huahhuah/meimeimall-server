package cn.tedu.meimall.passport.pojo.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 用戶登入信息的VO類
 *
 */
@Data
public class UserLoginInfoVO implements Serializable {

    /**
     * 資料id
     */
    private Long id;

    /**
     * 用戶名
     */
    private String username;

    /**
     * 密碼（密文）
     */
    private String password;

    /**
     * 頭像URL
     */
    private String avatar;

    /**
     * 是否啓用，1=啓用，0=未啓用
     */
    private Integer enable;

    /**
     * 登入次數
     */
    private Integer loginCount;

    /**
     * 權限列表
     */
    private List<String> permissions;

}
