package cn.tedu.meimall.front.mall.service;

import cn.tedu.meimall.common.consts.data.MallConsts;
import cn.tedu.meimall.common.pojo.vo.PageData;
import cn.tedu.meimall.front.mall.pojo.vo.GoodsListItemVO;
import cn.tedu.meimall.front.mall.pojo.vo.GoodsStandardVO;
import org.springframework.transaction.annotation.Transactional;

/***
 * 處理商品資料的業務介面
 */
@Transactional
public interface IGoodsService extends MallConsts {

    /**
     * 根據ID查詢商品
     * @param id 商品ID
     * @return 匹配的商品資料詳情,如果沒有匹配的資料,則返回null
     */
    GoodsStandardVO getStandardById(Long id);

    /**
     * 查詢推薦的商品列表,將使用默認的每頁記錄數
     * @param pageNum 頁碼
     * @return 商品列表
     */
    PageData<GoodsListItemVO> listByRecommend(Integer pageNum);

    /**
     * 查詢推薦的商品列表
     * @param pageNum  頁碼
     * @param pageSize 每頁記錄數
     * @return 商品列表
     */
    PageData<GoodsListItemVO> listByRecommend(Integer pageNum, Integer pageSize);

    /**
     * 跟據類別查詢商品列表,將使用默認的每頁記錄數
     * @param pageNum 頁碼
     * @return 商品列表
     */
    PageData<GoodsListItemVO> listByCategory(Long categoryId, Integer pageNum);

    /**
     * 跟據類別查詢商品列表
     * @param pageNum  頁碼
     * @param pageSize 每頁記錄數
     * @return 商品列表
     */
    PageData<GoodsListItemVO> listByCategory(Long categoryId, Integer pageNum, Integer pageSize);

}
