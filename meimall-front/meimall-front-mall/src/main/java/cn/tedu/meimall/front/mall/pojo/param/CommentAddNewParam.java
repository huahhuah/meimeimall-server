package cn.tedu.meimall.front.mall.pojo.param;

import lombok.Data;

import java.io.Serializable;

/**
 * 新增商品評論的參數類
 */
@Data
public class CommentAddNewParam implements Serializable {

    /**
     * 商品ID
     */
    private Long goodsId;

    /**
     * 評論類型：0=好評，1=中評，2=差評
     */
    private Integer type;

    /**
     * 評論内容
     */
    private String content;
}
