package cn.tedu.meimall.front.account.pojo.param;

import cn.tedu.meimall.common.validation.account.UserRules;
import lombok.Data;

import java.io.Serializable;

/**
 * 修改用戶基本資料的參數類
 */
@Data
public class UserUpdateInfoParam implements Serializable, UserRules {

    /**
     * 簡介
     */
    private String description;
}
