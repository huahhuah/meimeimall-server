package cn.tedu.meimall.front.account.dao.cache;

import cn.tedu.meimall.common.consts.cache.PassportCacheConsts;
import cn.tedu.meimall.common.pojo.po.UserStatePO;

/**
 * 處理用戶緩存資料的存儲庫介面
 */
public interface IUserCacheRepository extends PassportCacheConsts {

    /**
     * 從緩存中刪除用戶登入信息
     * @param userId 用戶ID
     * @return 如果刪除成功,將返回null.否則,將返回false
     */
    boolean deleteUserState(Long userId);


    /**
     * 從緩存中讀取用戶登入信息
     * @param userId 用戶ID
     * @return 匹配的用戶信息,如果沒有匹配的資料,則返回null
     */
    UserStatePO getUserState(Long userId);
}
