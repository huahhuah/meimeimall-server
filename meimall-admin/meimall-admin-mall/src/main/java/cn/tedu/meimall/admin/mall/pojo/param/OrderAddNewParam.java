package cn.tedu.meimall.admin.mall.pojo.param;

import lombok.Data;

import java.io.Serializable;

/**
 * 創建訂單的參數類
 */
@Data
public class OrderAddNewParam implements Serializable {

    /**
     * 用戶ID
     */
    private Long buyerId;
    /**
     * 收貨地址ID
     */
    private Long receiverAddressId;

    /**
     * 商品ID
     */
    private Long[] goodsIdList;

    /**
     * 訂單總金額
     */
    private Double totalPrice;

    /**
     * 訂單狀態
     */
    private String orderStatus;
}
