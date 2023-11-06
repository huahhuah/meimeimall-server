package cn.tedu.meimall.admin.mall.pojo.param;

import lombok.Data;

import java.io.Serializable;

/**
 * 修改類別的參數類
 */
@Data
public class CategoryUpdateInfoParam implements Serializable {

    /**
     * 類別名稱
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
     * 圖標圖片的URL
     */
    private String icon;
}
