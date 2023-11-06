package cn.tedu.meimall.admin.content.pojo.param;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 內容-評論的參數類
 */
@Data
public class CommentUpdateInfoParam implements Serializable {

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
     * 資料創建時間
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;

    /**
     * 資料最修改時間
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime gmtModified;
}
