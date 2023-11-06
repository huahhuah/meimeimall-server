package cn.tedu.meimall.front.mall.dao.cache.impl;

import cn.tedu.meimall.front.mall.dao.cache.ICartCacheRepository;
import cn.tedu.meimall.front.mall.pojo.po.CartPO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 處理購物車資料的緩存訪問實現類
 */
@Slf4j
@Repository
public class CartCacheRepositoryImpl implements ICartCacheRepository {

    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;

    public CartCacheRepositoryImpl(){
        log.info("創建緩存存儲庫對象: CartCacheRepositoryImpl");
    }

    @Override
    public void put(Long userId, CartPO cartPO) {
        log.debug("開始處理【向購物車中存入資料】的緩存資料訪問，用戶ID：{}，購物車資料：{}", userId, cartPO);
        String key = KEY_PREFIX_CART + userId;
        HashOperations<String, Object, Object> opsForHash = redisTemplate.opsForHash();
        opsForHash.put(key, "" + cartPO.getGoodsId(), cartPO.getGoodsNum());
    }

    @Override
    public long deleteByUserAndGoods(Long userId, Long goodsId) {
        log.debug("開始處理【根據用戶與商品删除購物車中的資料】的緩存資料訪問，用戶ID：{}，商品ID：{}", userId, goodsId);
        String key = KEY_PREFIX_CART + userId;
        HashOperations<String, Object, Object> opsForHash = redisTemplate.opsForHash();
        return opsForHash.delete(key, "" + goodsId);
    }

    @Override
    public CartPO getByUserAndGoods(Long userId, Long goodsId) {
        log.debug("開始處理【根據用戶與商品查詢購物車中的資料】的緩存資料訪問，用戶ID：{}，商品ID：{}", userId, goodsId);
        CartPO cartPO = null;
        String key = KEY_PREFIX_CART + userId;
        HashOperations<String, Object, Object> opsForHash = redisTemplate.opsForHash();
        Object goodsNum = opsForHash.get(key, "" + goodsId);
        if (goodsNum != null) {
            cartPO = new CartPO(goodsId, goodsNum);
        }
        return cartPO;
    }

    @Override
    public List<CartPO> listByUser(Long userId) {
        log.debug("開始處理【根據用戶查詢購物車資料的列表】的緩存資料訪問，用戶ID：{}", userId);
        String key = KEY_PREFIX_CART + userId;
        HashOperations<String, Object, Object> opsForHash = redisTemplate.opsForHash();
        Map<Object, Object> entries = opsForHash.entries(key);

        List<CartPO> cartList = new ArrayList<>();
        for (Object goodsId : entries.keySet()) {
            Object goodsNum = entries.get(goodsId);
            CartPO cartPO = new CartPO(goodsId, goodsNum);
            cartList.add(cartPO);
        }
        return cartList;
    }
}
