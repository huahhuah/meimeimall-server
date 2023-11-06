package cn.tedu.meimall.front.mall.pojo.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 列表項VO類: 商品-購物車
 */
@Data
@Accessors(chain = true)
public class CartListItemVO implements Serializable {

    /**
     * 商品ID
     */
    private Long goodsId;

    /**
     * 商品標題
     */
    private String goodsTitle;

    /**
     * 商品簡介
     */
    private String goodsBrief;

    /**
     * 商品封面圖
     */
    private String goodsCoverUrl;

    /**
     * 商品單價
     */
    private BigDecimal goodsSalePrice;

    /**
     * 商品是否上架
     */
    private Integer goodsIsPutOn;

    /**
     * 商品數量
     */
    private Integer goodsNum;
}
