package cn.tedu.meimall.front.mall.pojo.vo;

import lombok.Data;

/**
 * 列表項VO類: 收貨地址
 */
@Data
public class ReceiverAddressListItemVO {

    /**
     * 資料ID
     */
    private Long id;

    /**
     * 用戶ID
     */
    private Long userId;

    /**
     * 收貨人
     */
    private String receiverName;

    /**
     * 收貨電話
     */
    private String receiverPhone;

    /**
     * 市名稱
     */
    private String city;

    /**
     * 區名稱
     */
    private String area;

    /**
     * 詳細地址
     */
    private String detail;

    /**
     * 是否默認
     */
    private Integer isDefault;
}
