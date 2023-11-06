package cn.tedu.meimall.common.pojo.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 分頁資料類
 */
@Data
@Accessors(chain = true)
public class PageData<T> implements Serializable {

    /**
     * 每頁記錄數
     */
    private Integer pageSize;

    /**
     * 記錄總數
     */
    private Long total;

    /**
     * 當前頁碼
     */
    private Integer currentPage;

    /**
     * 最大頁碼
     */
    private Integer maxPage;

    /**
     * 資料列表
     */
    private List<T> list;
}
