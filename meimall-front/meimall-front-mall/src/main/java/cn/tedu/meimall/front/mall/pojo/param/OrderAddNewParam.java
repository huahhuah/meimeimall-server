package cn.tedu.meimall.front.mall.pojo.param;

import lombok.Data;

import java.io.Serializable;

/**
 * 創建訂單的參數類
 */
@Data
public class OrderAddNewParam implements Serializable {

    /**
     * 收貨地址ID
     */
    private Long receiverAddressId;

    /**
     * 商品ID
     */
    private Long[] goodsIdList;
}
