package cn.tedu.meimall.admin.content.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 實體類: 內容-頂踩日誌
 */
@Data
@TableName("content_up_down_log")
public class UpDownLog implements Serializable {

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
     * 操作類型，0=踩，1=頂
     */
    private Integer opType;

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
