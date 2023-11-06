package cn.tedu.meimall.admin.mall.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 標準VO類: 商品信息
 */
@Data
public class GoodsStandardVO implements Serializable {

    /**
     * 資料ID
     */
    private Long id;

    /**
     * 類别ID
     */
    private Long categoryId;

    /**
     * 類別名稱
     */
    private String categoryName;

    /**
     * 條型碼
     */
    private String barCode;

    /**
     * 標題
     */
    private String title;

    /**
     * 摘要
     */
    private String brief;

    /**
     * 封面圖
     */
    private String coverUrl;

    /**
     * 售價
     */
    private BigDecimal salePrice;

    /**
     * 關鍵詞列表
     */
    private String keywords;

    /**
     * 排序序號
     */
    private Integer sort;

    /**
     * 是否推薦
     */
    private Integer isRecommend;

    /**
     * 審核狀態，0=未審核，1=審核通過，2=拒絕審核
     */
    private Integer checkState;

    /**
     * 上架狀態，0=下架，1=上架
     */
    private Integer isPutOn;

    /**
     * 詳情
     */
    private String detail;

    /**
     * 銷量
     */
    private Integer salesCount;

    /**
     * 評論數量
     */
    private Integer commentCount;

    /**
     * 好評數量
     */
    private Integer positiveCommentCount;

    /**
     * 差評數量
     */
    private Integer negativeCommentCount;

    /**
     * 資料創建時間
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm.ss")
    private LocalDateTime gmtCreate;

    /**
     * 資料最後修改時間
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm.ss")
    private LocalDateTime gmtModified;

}
