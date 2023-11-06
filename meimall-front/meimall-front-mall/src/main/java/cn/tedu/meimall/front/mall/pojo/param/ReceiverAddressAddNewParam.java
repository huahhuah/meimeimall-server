package cn.tedu.meimall.front.mall.pojo.param;

import lombok.Data;

/**
 * 添加收貨地址的參數類
 */
@Data
public class ReceiverAddressAddNewParam {

    /**
     * 收貨人
     */
    private String receiverName;

    /**
     * 收貨電話
     */
    private String receiverPhone;

    /**
     * 市編碼
     */
    private String cityCode;

    /**
     * 區編碼
     */
    private String areaCode;

    /**
     * 詳細地址
     */
    private String detail;

    /**
     * 是否默認
     */
    private Integer isDefault;
}
