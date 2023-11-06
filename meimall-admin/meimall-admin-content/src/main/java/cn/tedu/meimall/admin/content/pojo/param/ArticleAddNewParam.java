package cn.tedu.meimall.admin.content.pojo.param;

import lombok.Data;

import java.io.Serializable;

/**
 * 新增文章的參數類
 */
@Data
public class ArticleAddNewParam implements Serializable {

    /**
     * 類别ID
     */
    private Long categoryId;

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
     * 關鍵詞列表，各關鍵詞使用英文的逗號分隔
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
