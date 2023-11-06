package cn.tedu.meimall.common.consts.cache;

/**
 * 地區資料緩存相關常量
 */
public interface DistrictCacheConsts {
    /**
     * 緩存資料的KEY的前綴：根據父級存儲的地區列表
     */
    String KEY_PREFIX_LIST_BY_PARENT = "district:list-by-parent:";

    /**
     * 緩存資料的KEY的前缀：地區資料
     */
    String KEY_PREFIX_ITEM = "district:item:";

    /**
     * 緩存中所有列表資料的Key集合的Key
     */
    String KEY_ALL_KEYS = "district:keys";
}
