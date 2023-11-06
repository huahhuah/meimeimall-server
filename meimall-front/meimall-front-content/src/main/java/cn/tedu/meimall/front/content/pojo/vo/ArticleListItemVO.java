package cn.tedu.meimall.front.content.pojo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 列表項VO類: 內容-文章
 */
@Data
public class ArticleListItemVO implements Serializable {

    /**
     * 資料ID
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
     * 類别ID
     */
    private Long categoryId;

    /**
     * 類别名稱
     */
    private String categoryName;

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
     * IP地址
     */
    private String ip;

    /**
     * 排序序號
     */
    private Integer sort;

    /**
     * 頂數量
     */
    private Integer upCount;

    /**
     * 踩數量
     */
    private Integer downCount;

    /**
     * 瀏覽量
     */
    private Integer clickCount;

    /**
     * 評論量
     */
    private Integer commentCount;

    /**
     * 是否推薦，0=不推薦，1=推薦
     */
    private Integer isRecommend;
}
