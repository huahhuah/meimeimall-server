package cn.tedu.meimall.admin.mall.pojo.param;

import lombok.Data;

import java.io.Serializable;

/**
 * 創建訂單的參數類
 */
@Data
public class OrderUpdateParam implements Serializable {

    /**
     * 收件人
     */
    private String receiverName;

    /**
     * 收件人電話
     */
    private String receiverPhone;

    /**
     * 收件人地址
     */
    private String receiverAddress;

    /**
     * 訂單總金額
     */
    private Double totalPrice;

    /**
     * 支付方式：1=匯款，2=貨到付款
     */
    private Integer payMethod;

    /**
     * 訂單狀態 :  0=待支付，1=已支付，待發貨, 2=已發貨/待收貨，3=確認收貨/已完成
     */
    private String orderStatus;
}
