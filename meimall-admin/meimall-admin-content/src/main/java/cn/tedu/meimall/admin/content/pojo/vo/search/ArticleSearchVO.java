package cn.tedu.meimall.admin.content.pojo.vo.search;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用於處理搜索功能的文章資料的VO類
 */
@Data
@Document(indexName = "article")
public class ArticleSearchVO implements Serializable {

    /**
     * 資料ID
     */
    @Id
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
     * 標題
     */
    @Field(type = FieldType.Text, analyzer = "ik_smart", searchAnalyzer = "ik_smart")
    private String title;

    /**
     * 摘要
     */
    @Field(type = FieldType.Text, analyzer = "ik_smart", searchAnalyzer = "ik_smart")
    private String brief;

    /**
     * 關鍵詞列表，各關鍵詞使用英文的逗號分隔
     */
    @Field(type = FieldType.Text, analyzer = "ik_smart", searchAnalyzer = "ik_smart")
    private String keywords;

    /**
     * 封面圖
     */
    private String coverUrl;

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
     * 資料創建時間
     */
    @Field(type = FieldType.Date, format = {}, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm.ss")
    private LocalDateTime gmtCreate;

    /**
     * 資料最後修改時間
     */
    @Field(type = FieldType.Date, format = {}, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm.ss")
    private LocalDateTime gmtModified;
}
