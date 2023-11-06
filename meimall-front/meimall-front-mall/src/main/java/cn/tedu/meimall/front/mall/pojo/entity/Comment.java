package cn.tedu.meimall.front.mall.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 實體類: 商城-評論
 */
@Data
@TableName("mall_comment")
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

    /**
     * 審核狀態，0=未審核，1=審核通過，2=拒絕審核
     */
    private Integer checkState;

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
