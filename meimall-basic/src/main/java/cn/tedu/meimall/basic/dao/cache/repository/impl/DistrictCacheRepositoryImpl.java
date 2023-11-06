package cn.tedu.meimall.basic.dao.cache.repository.impl;

import cn.tedu.meimall.basic.dao.cache.repository.IDistrictCacheRepository;
import cn.tedu.meimall.common.pojo.po.DistrictSimplePO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * 處理市區資料的緩存訪問實現類
 */
@Slf4j
@Repository
public class DistrictCacheRepositoryImpl implements IDistrictCacheRepository {

    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;

    @Override
    public void save(DistrictSimplePO districtSimplePO) {
        log.debug("開始處理【存儲地區資料】的緩存資料訪問，地區資料：{}", districtSimplePO);
        String key = KEY_PREFIX_ITEM + districtSimplePO.getCode();
        SetOperations<String, Serializable> opsForSet = redisTemplate.opsForSet();
        opsForSet.add(KEY_ALL_KEYS, key);
        ValueOperations<String, Serializable> opsForValue = redisTemplate.opsForValue();
        opsForValue.set(key, districtSimplePO);

    }

    @Override
    public void saveListByParent(Long parentId, List<DistrictSimplePO> districtSimplePOList) {
        log.debug("開始處理【存儲根據父級劃分的地區列表】的緩存資料訪問，父級地區ID：{}，地區列表：{}", parentId, districtSimplePOList);
        String key = KEY_PREFIX_LIST_BY_PARENT + parentId;
        SetOperations<String, Serializable> opsForSet = redisTemplate.opsForSet();
        opsForSet.add(KEY_ALL_KEYS, key);
        ListOperations<String, Serializable> opsForList = redisTemplate.opsForList();
        for (DistrictSimplePO listItem : districtSimplePOList) {
            opsForList.rightPush(key, listItem);
        }
    }

    @Override
    public void deleteAll() {
        log.debug("開始處理【删除所有的地區列表資料】的緩存資料訪問，無參數");
        SetOperations<String, Serializable> opsForSet = redisTemplate.opsForSet();
        Set keys = opsForSet.members(KEY_ALL_KEYS);
        redisTemplate.delete(keys);
    }

    @Override
    public List<DistrictSimplePO> listByParent(Long parentId) {
        log.debug("開始處理【根據父級查詢子級地區列表】的緩存資料訪問，父級地區ID：{}", parentId);
        String key = KEY_PREFIX_LIST_BY_PARENT + parentId;
        long start = 0;
        long end = -1;
        ListOperations<String, Serializable> opsForList = redisTemplate.opsForList();
        List range = opsForList.range(key, start, end);
        return range;
    }
}
