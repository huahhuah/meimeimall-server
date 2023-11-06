package cn.tedu.meimall.admin.mall.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 實體類: 訂單
 */
@Data
@TableName("mall_order")
public class Order implements Serializable {

    /**
     * 資料ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 買家ID
     */
    private Long buyerId;

    /**
     * 買家用戶名
     */
    private String buyerUsername;

    /**
     * 訂單號
     */
    private String orderNo;

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
    private LocalDateTime gmtPay;

    /**
     * 資料創建時間
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;

    /**
     * 資料最後修改時間
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime gmtModified;
}
