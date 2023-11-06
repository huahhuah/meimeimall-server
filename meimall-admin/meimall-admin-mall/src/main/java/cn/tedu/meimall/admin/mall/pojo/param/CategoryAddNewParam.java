package cn.tedu.meimall.admin.mall.pojo.param;

import lombok.Data;

import java.io.Serializable;

/**
 * 新增類別的參數類
 */
@Data
public class CategoryAddNewParam implements Serializable {

    /**
     * 類别名稱
     */
    private String name;

    /**
     * 父級類别ID，如果無父級，則為0
     */
    private Long parentId;

    /**
     * 深度，最頂級類别的深度為1，次級為2，以此類推
     */
    private Integer depth;

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

    /**
     * 是否啓用，1=啓用，0=未啓用
     */
    private Integer enable;

    /**
     * 是否為父級（是否包含子級），1=是父級，0=不是父級
     */
    private Integer isParent;

    /**
     * 是否顯示在導航欄中，1=啓用，0=未啓用
     */
    private Integer isDisplay;
}
