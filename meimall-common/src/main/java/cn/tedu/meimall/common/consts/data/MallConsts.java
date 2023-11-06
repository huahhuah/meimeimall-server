package cn.tedu.meimall.common.consts.data;

/**
 * 商城管理相關常量
 */
public interface MallConsts extends CommonConsts {

    /**
     * 資源類型：商品
     */
    int RESOURCE_TYPE_GOODS = 0;
    /**
     * 資源類型：評論
     */
    int RESOURCE_TYPE_COMMENT = 1;

    /**
     * 商品上架狀態：下架
     */
    int PUT_ON_STATE_OFF = 0;
    /**
     * 商品上架狀態：上架
     */
    int PUT_ON_STATE_ON = 1;
    /**
     * 商品“是否上架”的狀態文本
     */
    String[] PUT_ON_STATE_TEXT = {"下架", "上架"};

    /**
     * 商品推薦：推薦
     */
    int RECOMMEND_STATE_OFF = 0;
    /**
     * 商品推薦：不推薦
     */
    int RECOMMEND_STATE_ON = 1;
    /**
     * 商品“是否推薦”的狀態文本
     */
    String[] RECOMMEND_STATE_TEXT = {"取消推薦", "推薦"};

    /**
     * 評論類型：好評
     */
    int COMMENT_TYPE_POSITIVE = 0;
    /**
     * 評論類型：中評
     */
    int COMMENT_TYPE_NEUTRAL = 1;
    /**
     * 評論類型：差評
     */
    int COMMENT_TYPE_NEGATIVE = 2;
    /**
     * “評論類型”的狀態文本
     */
    String[] COMMENT_TYPE_TEXT = {"好評", "中評", "差評"};

    /**
     * 支付渠道：匯款
     */
    int PAY_CHANNEL_REMITTANCE = 0;
    /**
     * 支付渠道：貨到付款
     */
    int PAY_CHANNEL_CARD_PAYMENT = 1;
    /**
     * “支付渠道”的狀態文本
     */
    String[] PAY_CHANNEL_TEXT = {"匯款", "貨到付款"};

    /**
     * 支付方式：在線支付
     */
    int PAY_METHOD_ONLINE = 0;
    /**
     * 支付方式：貨到付款
     */
    int PAY_METHOD_ON_DELIVERY = 1;
    /**
     * “支付方式”的狀態文本
     */
    String[] PAY_METHOD_TEXT = {"在線支付", "貨到付款"};

    /**
     * 訂單狀態：待支付
     */
    int ORDER_STATE_UNPAID = 0;
    /**
     * 訂單狀態：已支付
     */
    int ORDER_STATE_PAID = 1;
    /**
     * 訂單狀態：已發貨
     */
    int ORDER_STATE_DELIVERED = 2;
    /**
     * 訂單狀態：已支付
     */
    int ORDER_STATE_COMPLETED = 3;
    /**
     * 訂單狀態：待支付
     */
    int ORDER_STATE_CLOSED_BY_USER = 4;
    /**
     * 訂單狀態：已支付
     */
    int ORDER_STATE_CLOSE_BY_SELLER = 5;
    /**
     * 訂單狀態：待支付
     */
    int ORDER_STATE_CLOSED_BY_SYSTEM = 6;
    /**
     * “訂單狀態”的狀態文本
     */
    String[] ORDER_STATE_TEXT = {"待支付", "已支付，待發貨", "已發貨，待收貨", "確認收貨，已完成", "用戶關閉", "平台關閉", "系統調度關閉"};
}
