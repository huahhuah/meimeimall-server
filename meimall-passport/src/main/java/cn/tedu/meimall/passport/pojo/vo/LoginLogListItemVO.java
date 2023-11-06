package cn.tedu.meimall.passport.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 列表項VO類：用戶登入日誌
 */
@Data
public class LoginLogListItemVO implements Serializable {

    /**
     * 資料id
     */
    private Long id;

    /**
     * 用戶ID
     */
    private Long userId;

    /**
     * 用戶名
     */
    private String username;

    /**
     * IP地址
     */
    private String ip;

    /**
     * 瀏覽器信息
     */
    private String userAgent;

    /**
     * 登入時間
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime gmtLogin;

}
