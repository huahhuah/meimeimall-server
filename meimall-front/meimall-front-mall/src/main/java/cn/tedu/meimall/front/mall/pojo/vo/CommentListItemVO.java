package cn.tedu.meimall.front.mall.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 列表項VO類: 內容-評論
 */
@Data
public class CommentListItemVO implements Serializable {

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
     * 資料創建時間
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm.ss")
    private LocalDateTime gmtCreate;
}
