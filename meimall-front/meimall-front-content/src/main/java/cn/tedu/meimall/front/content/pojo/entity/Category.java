package cn.tedu.meimall.front.content.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 實體類: 內容-類別
 */
@Data
@TableName("content_category")
public class Category implements Serializable {

    /**
     * 資料ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 類别名稱
     */
    private String name;

    /**
     * 關鍵詞列表，各關鍵詞使用英文的逗號分隔
     */
    private String keywords;

    /**
     * 排序序號
     */
    private Integer sort;

    /**
     * 是否啟用，1=啟用，0=未啟用
     */
    private Integer enable;

    /**
     * 是否顯示在導航欄中，1=啟用，0=未啟用
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
