package cn.tedu.meimall.front.content.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *實體類: 內容-評論
 */
@Data
@TableName("content_comment")
public class Comment implements Serializable {

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
     * 資源類型，0=文章，1=評論
     */
    private Integer resourceType;

    /**
     * 資源ID
     */
    private Long resourceId;

    /**
     * 資源摘要，截取的文章標題或評論
     */
    private String resourceBrief;

    /**
     * 評論内容
     */
    private String content;

    /**
     * IP
     */
    private String ip;

    /**
     * 樓層
     */
    private Integer floor;

    /**
     * 頂數量
     */
    private Integer upCount;

    /**
     * 踩數量
     */
    private Integer downCount;

    /**
     * 審核狀態，0=未審核，1=審核通過，2=拒絕審核
     */
    private Integer checkState;

    /**
     * 顯示狀態，0=不顯示，1=顯示
     */
    private Integer isDisplay;

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
