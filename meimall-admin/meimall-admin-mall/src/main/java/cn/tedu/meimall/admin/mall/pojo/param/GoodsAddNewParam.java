package cn.tedu.meimall.admin.mall.pojo.param;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 新增商品的參數類
 */
@Data
public class GoodsAddNewParam implements Serializable {

    /**
     * 類别ID
     */
    private Long categoryId;

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
     * 詳情
     */
    private String detail;
}
