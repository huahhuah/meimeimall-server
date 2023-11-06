package cn.tedu.meimall.admin.mall.pojo.param;

import lombok.Data;

import java.io.Serializable;

/***
 * 新增商品評論的參數類
 */
@Data
public class CommentUpdateInfoParam implements Serializable {

    /**
     * 數據ID
     */
    private Long id;

    /**
     * 作者ID
     */
    private Long authorId;

    /**
     * 作者名字
     */
    private String authorName;

    /**
     * 商品ID
     */
    private Long goodsId;

    /**
     * 評論類型：0=好評，1=中評，2=差評
     */
    private Integer type;

    /**
     * 評論內容
     */
    private String content;

}
