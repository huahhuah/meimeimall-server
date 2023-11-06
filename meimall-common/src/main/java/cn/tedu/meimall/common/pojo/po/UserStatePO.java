package cn.tedu.meimall.common.pojo.po;

import lombok.Data;

import java.io.Serializable;

/**
 * 用戶狀態信息的PO
 * 此類資料將存入到緩存中,管理員修改用戶關鍵信息時也會同步修改緩存中對應的資料
 * 用戶提交請求時也會實時檢查緩存中的用戶狀態
 */
@Data
public class UserStatePO implements Serializable {

    /**
     * 啓用狀態，0=禁用，1=啓用
     */
    private Integer enable;

    /**
     * 權限列表的JSON字符串
     */
    private String authoritiesJsonString;
}
