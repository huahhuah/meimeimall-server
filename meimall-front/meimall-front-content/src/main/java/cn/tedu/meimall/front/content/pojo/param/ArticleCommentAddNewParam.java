package cn.tedu.meimall.front.content.pojo.param;

import lombok.Data;

import java.io.Serializable;

/**
 * 新增文章評論的參數類
 */
@Data
public class ArticleCommentAddNewParam implements Serializable {

    /**
     * 文章ID
     */
    private Long articleId;

    /**
     * 評論內容
     */
    private String content;
}
