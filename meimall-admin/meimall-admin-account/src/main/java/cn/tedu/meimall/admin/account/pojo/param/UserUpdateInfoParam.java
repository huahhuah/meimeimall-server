package cn.tedu.meimall.admin.account.pojo.param;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 修改用戶基本資料的參數類
 */
@Data
@Accessors(chain = true)
public class UserUpdateInfoParam implements Serializable {
    /**
     * 資料ID
     */
    private Long id;
    /**
     * 用戶名
     */
    private String username;
    /**
     * 密碼
     */
    private String password;
    /**
     * 暱稱
     */
    private String nickname;
    /**
     * 頭像
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
     * 啟用
     */
    private Integer enable;
}
