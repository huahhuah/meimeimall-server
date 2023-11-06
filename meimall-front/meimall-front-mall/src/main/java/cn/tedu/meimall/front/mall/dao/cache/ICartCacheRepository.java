package cn.tedu.meimall.front.mall.dao.cache;

import cn.tedu.meimall.common.consts.cache.CartCacheConst;
import cn.tedu.meimall.front.mall.pojo.po.CartPO;

import java.util.List;

/**
 * 處理購物車緩存資料的存儲庫接口
 */
public interface ICartCacheRepository extends CartCacheConst {

    /**
     * 向購物車中存入資料,即用於增加,也用於修改
     * @param userId   用戶ID
     * @param cartPO   購物車資料
     */
    void put(Long userId, CartPO cartPO);

    /**
     * 根據用戶與商品刪除購物車中的資料
     * @param userId  用戶ID
     * @param goodsId 商品ID
     * @return  成功刪除的資料
     */
    long deleteByUserAndGoods(Long userId, Long goodsId);

    /**
     * 根據用戶與商品查詢購物車中的資料
     * @param userId  用戶ID
     * @param goodsId 商品ID
     * @return 購物車資料
     */
    CartPO getByUserAndGoods(Long userId, Long goodsId);

    /**
     * 根據用戶查詢購物車資料的列表
     * @param userId 用戶ID
     * @return 匹配的用戶的購物車資料的列表
     */
    List<CartPO> listByUser(Long userId);
}
