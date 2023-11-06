package cn.tedu.meimall.admin.mall.dao.persist.repository;

import cn.tedu.meimall.admin.mall.pojo.entity.GoodsDetail;

/**
 * 處理商品詳情資料的存儲庫介面
 */
public interface IGoodsDetailRepository {

    /**
     * 插入商品詳情資料
     * @param goodsDetail 商品詳情資料
     * @return 受影響的行數
     */
    int insert(GoodsDetail goodsDetail);

    /**
     * 根據商品ID刪除商品詳情資料
     * @param goodsId 商品ID
     * @return 受影響的行數
     */
    int deleteByGoods(Long goodsId);

    /**
     * 根據ID修改商品詳情資料
     * @param goodsDetail 封裝了商品ID和新商品詳情資料的對象
     * @return 受影響的行數
     */
    int updateByGoods(GoodsDetail goodsDetail);
}
