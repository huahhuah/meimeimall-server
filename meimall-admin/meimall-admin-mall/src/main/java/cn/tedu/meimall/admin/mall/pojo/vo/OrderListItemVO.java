package cn.tedu.meimall.admin.mall.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 列表項VO類: 訂單信息
 */
@Data
public class OrderListItemVO implements Serializable {

    /**
     * 資料ID
     */
    private Long id;

    /**
     * 訂單號
     */
    private String orderNo;

    /**
     * 用戶ID
     */
    private Long buyerId;

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
     * 商品數量
     */
    private Integer goodsNum;

    /**
     * 商品總價
     */
    private BigDecimal totalPrice;

    /**
     * 物流單號
     */
    private String logisticsNo;

    /**
     * 支付方式：1=匯款，2=貨到付款
     */
    private Integer payMethod;

    /**
     * 訂單狀態: 0=待支付，1=已支付，待發貨, 2=已發貨/待收貨，3=確認收貨/已完成，4=用戶關閉，5=平台關閉(商家)，6=系統調度關閉
     */
    private Integer orderState;

    /**
     * 支付時間
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm.ss")
    private LocalDateTime gmtPay;
}
