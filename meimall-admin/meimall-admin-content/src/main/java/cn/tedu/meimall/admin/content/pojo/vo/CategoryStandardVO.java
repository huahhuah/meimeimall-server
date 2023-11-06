package cn.tedu.meimall.admin.content.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 標準VO類: 內容-類別
 */
@Data
public class CategoryStandardVO implements Serializable {
    /**
     * 資料ID
     */
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
     * 是否啓用，1=啓用，0=未啓用
     */
    private Integer enable;

    /**
     * 是否顯示在導航欄中，1=啓用，0=未啓用
     */
    private Integer isDisplay;

    /**
     * 資料創建時間
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm.ss")
    private LocalDateTime gmtCreate;

    /**
     * 資料最後修改時間
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm.ss")
    private LocalDateTime gmtModified;
}
