package cn.tedu.meimall.attachment.dao.cache.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.tedu.meimall.attachment.dao.cache.IUserCacheRepository;
import cn.tedu.meimall.common.pojo.po.UserStatePO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Map;

/**
 * 處理用戶緩存資料的存儲庫實現類
 */
@Slf4j
@Repository
public class UserCacheRepositoryImpl implements IUserCacheRepository {

    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;

    public UserCacheRepositoryImpl(){
        log.info("創建緩存存儲庫對象: UserCacheRepositoryImpl");
    }

    @Override
    public boolean deleteUserState(Long userId) {
        log.debug("開始處理【從緩存中刪除用戶登入信息】的資料訪問，用戶ID:{},",userId);
        String key = KEY_PREFIX_USER_STATE + userId;
        return redisTemplate.delete(key);
    }

    @Override
    public UserStatePO getUserState(Long userId) {
        log.debug("開始處理【從緩存中讀取用戶登入信息】的資料訪問,用戶ID:{},", userId);
        String key = KEY_PREFIX_USER_STATE + userId;
        UserStatePO userStatePO = null;
        HashOperations<String, Object, Object> opsForHash = redisTemplate.opsForHash();
        Map<Object, Object> entries = opsForHash.entries(key);
        if (entries.size() != 0) {
            userStatePO = BeanUtil.mapToBean(entries, UserStatePO.class, true, null);
        }
        return userStatePO;
    }
}
