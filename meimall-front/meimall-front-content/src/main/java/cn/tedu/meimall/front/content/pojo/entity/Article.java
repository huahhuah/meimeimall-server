package cn.tedu.meimall.front.content.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 實體類: 內容-文章
 */
@Data
@TableName("content_article")
public class Article implements Serializable {

    /**
     * 資料ID
     */
    @TableId(type = IdType.AUTO)
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
     * 排序序号
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
     * 審核狀態，0=未審核，1=審核通過，2=拒絕審核
     */
    private Integer checkState;

    /**
     * 顯示狀態，0=不顯示，1=顯示
     */
    private Integer isDisplay;

    /**
     * 是否推薦，0=不推薦，1=推薦
     */
    private Integer isRecommend;

    /**
     * 資料創建時間
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;

    /**
     * 資料最後修改時間
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime gmtModified;
}
