package cn.tedu.meimall.common.consts.data;

/**
 * 內容管理相關常量
 */
public interface ContentConsts extends CommonConsts{

    /**
     * 資源類型：文章
     */
    int RESOURCE_TYPE_ARTICLE = 0;
    /**
     * 資源類型：評論
     */
    int RESOURCE_TYPE_COMMENT = 1;

    /**
     * 操作類型：踩
     */
    int OP_TYPE_DOWN = 0;
    /**
     * 操作類型：頂
     */
    int OP_TYPE_UP = 1;
}
