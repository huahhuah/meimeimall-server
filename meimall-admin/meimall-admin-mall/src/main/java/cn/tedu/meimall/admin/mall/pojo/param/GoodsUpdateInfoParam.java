package cn.tedu.meimall.admin.mall.pojo.param;

import cn.tedu.meimall.admin.mall.pojo.entity.GoodsDetail;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 新增商品的參數類
 */
@Data
public class GoodsUpdateInfoParam  implements Serializable {

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
     * 關鍵詞列表
     */
    private String keywords;

    /**
     * 售價
     */
    private BigDecimal salePrice;

    /**
     * 銷量
     */
    private Integer salesCount;

    /**
     * 排序序號
     */
    private Integer sort;

    /**
     * 詳情
     */
    private String detail;


}
