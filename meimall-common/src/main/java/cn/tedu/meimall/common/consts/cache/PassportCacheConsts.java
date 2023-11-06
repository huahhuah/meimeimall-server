package cn.tedu.meimall.common.consts.cache;

/**
 * 單點登入緩存相關常量
 */
public interface PassportCacheConsts {

    /**
     * 用戶狀態信息的Key前綴
     */
    String KEY_PREFIX_USER_STATE = "passport:user-state:";

    /**
     * 用戶狀態的Hash對象中“啓用狀態”的Key
     */
    String HASH_KEY_USER_ENABLE = "enable";
}
