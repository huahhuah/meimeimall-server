package cn.tedu.meimall.admin.account.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用戶的實體類
 */
@Data
@TableName("account_user")
public class User implements Serializable {

    /**
     * 資料id
     */
    @TableId(type = IdType.AUTO)
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
    private LocalDateTime gmtLastLogin;

    /**
     * 資料創建時間
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;

    /**
     * 資料最後修改時間
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime gmtModified;

}
