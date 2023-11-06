package cn.tedu.meimall.common.pojo.authentication;

import lombok.Data;

import java.io.Serializable;

/**
 * 當前認證信息中的當事人
 */
@Data
public class CurrentPrincipal implements Serializable {

    /**
     * 當事人ID
     */
    private Long id;

    /**
     * 當事人用戶名
     */
    private String username;
}
