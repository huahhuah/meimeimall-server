package cn.tedu.meimall.front.account.pojo.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
     * 密碼(密文)
     */
    private String password;

    /**
     * 用戶名頭像
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
     * 用戶名簡介
     */
    private String description;

    /**
     * 是否啟用, 1=啟用  0= 未啟用
     */
    private Integer enable;

    /**
     * 用戶最後登入IP地址(冗餘)
     */
    private String lastLoginIp;

    /**
     * 用戶登入次數(冗餘)
     */
    private Integer loginCount;

    /**
     * 用戶最後登入時間(冗餘)
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm.ss")
    private LocalDateTime gmtLastLogin;

}
