package cn.tedu.meimall.admin.account.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用戶的標準VO類
 */
@Data
public class UserStandardVO implements Serializable {

    /**
     * 資料id
     */
    private Long id;

    /**
     * 用戶名
     */
    private String username;

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
     * 簡介
     */
    private String description;

    /**
     * 是否啓用，1=啓用，0=未啓用
     */
    private Integer enable;

    /**
     * 最後登入IP地址（冗餘）
     */
    private String lastLoginIp;

    /**
     * 累計登入次數（冗餘）
     */
    private Integer loginCount;

    /**
     * 最後登入時間（冗餘）
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm.ss")
    private LocalDateTime gmtLastLogin;
}
