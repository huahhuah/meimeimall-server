package cn.tedu.meimall.front.mall.converter;

import cn.tedu.meimall.front.mall.pojo.po.CartPO;
import cn.tedu.meimall.front.mall.pojo.vo.CartListItemVO;
import cn.tedu.meimall.front.mall.pojo.vo.GoodsStandardVO;

/**
 * 將商品數據轉換為購物車顯示對象的轉換器
 */
public class GoodsToCartConverter {

    /**
     * 將商品數據轉換為購物車顯示對象
     * @param cartPO 購物車數據
     * @param goods  商品數據
     * @return  購物車顯示對象
     */
    public synchronized static CartListItemVO convertToCart(CartPO cartPO, GoodsStandardVO goods){
        return new CartListItemVO()
                .setGoodsId(cartPO.getGoodsId())
                .setGoodsTitle(goods.getTitle())
                .setGoodsBrief(goods.getBrief())
                .setGoodsCoverUrl(goods.getCoverUrl())
                .setGoodsSalePrice(goods.getSalePrice())
                .setGoodsIsPutOn(goods.getIsPutOn())
                .setGoodsNum(cartPO.getGoodsNum());
    }
}
