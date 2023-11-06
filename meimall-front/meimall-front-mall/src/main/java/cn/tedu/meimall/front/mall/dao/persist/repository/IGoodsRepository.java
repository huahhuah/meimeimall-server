package cn.tedu.meimall.front.mall.dao.persist.repository;

import cn.tedu.meimall.common.pojo.vo.PageData;
import cn.tedu.meimall.front.mall.pojo.entity.Goods;
import cn.tedu.meimall.front.mall.pojo.vo.GoodsListItemVO;
import cn.tedu.meimall.front.mall.pojo.vo.GoodsStandardVO;

/**
 * 處理商品資料的存儲庫接口
 */
public interface IGoodsRepository {

    /**
     * 根據ID修改商品資料
     * @param goods 封裝了商品ID和新資料的對象
     * @return 受影響的行數
     */
    int update(Goods goods);

    /**
     * 根據ID查詢商品資料詳情
     * @param id 商品ID
     * @return 匹配的商品資料詳情,如果沒有匹配的資料,則返回null
     */
    GoodsStandardVO getStandardById(Long id);

    /**
     * 查詢推薦的商品資料列表
     * @param pageNum  頁碼
     * @param pageSize 每頁記錄數
     * @return  商品資料列表
     */
    PageData<GoodsListItemVO> listByCommend(Integer pageNum, Integer pageSize);

    /**
     * 跟據類別查詢商品列表
     * @param categoryId 商品類別的ID
     * @param pageNum    頁碼
     * @param pageSize   每頁記錄數
     * @return  商品列表
     */
    PageData<GoodsListItemVO> listByCategory(Long categoryId, Integer pageNum, Integer pageSize);
}
