package cn.tedu.meimall.admin.mall.service;

import cn.tedu.meimall.admin.mall.pojo.param.GoodsAddNewParam;
import cn.tedu.meimall.admin.mall.pojo.param.GoodsUpdateInfoParam;
import cn.tedu.meimall.admin.mall.pojo.vo.GoodsListItemVO;
import cn.tedu.meimall.admin.mall.pojo.vo.GoodsStandardVO;
import cn.tedu.meimall.common.consts.data.MallConsts;
import cn.tedu.meimall.common.pojo.authentication.CurrentPrincipal;
import cn.tedu.meimall.common.pojo.vo.PageData;
import org.springframework.transaction.annotation.Transactional;

/**
 * 處理商品資料的業務介面
 */
@Transactional
public interface IGoodsService extends MallConsts {

    /**
     * 發布商品
     * @param currentPrincipal 當事人
     * @param remoteAddr       IP地址
     * @param goodsAddNewParam 新的商品資料
     */
    void addNew(CurrentPrincipal currentPrincipal, String remoteAddr, GoodsAddNewParam goodsAddNewParam);

    /**
     * 根據ID刪除商品
     * @param id 嘗試刪除的商品資料的ID
     */
    void delete(Long id);

    /**
     * 審核通過商品
     * @param currentPrincipal 當事人
     * @param goodsId          嘗試審核通過的商品的ID
     * @param remarks          備註信息
     */
    void passCheck(CurrentPrincipal currentPrincipal, Long goodsId, String remarks);

    /**
     * 拒絕審核商品
     * @param currentPrincipal 當事人
     * @param goodsId          嘗試拒絕審核的商品的ID
     * @param remarks          備註信息
     */
    void rejectCheck(CurrentPrincipal currentPrincipal, Long goodsId, String remarks);

    /**
     * 上架商品
     * @param id 商品ID
     */
    void putOn(Long id);

    /**
     * 下架商品
     * @param id 商品ID
     */
    void pullOff(Long id);

    /**
     * 推薦商品
     * @param id 商品ID
     */
    void setRecommend(Long id);

    /**
     * 取消推薦商品
     * @param id 商品ID
     */
    void cancelRecommend(Long id);

    /**
     * 修改商品詳情資料
     * @param id                    被修改的商品詳情資料的ID
     * @param goodsUpdateInfoParam  商品詳情的新資料
     */
    void updateInfoById(Long id, GoodsUpdateInfoParam goodsUpdateInfoParam);

    /**
     * 根據ID查詢商品
     * @param id 商品ID
     * @return 匹配的商品資料詳情,如果沒有匹配的資料,則在返回null
     */
    GoodsStandardVO getStandardById( Long id);

    /**
     * 查詢商品列表,將使用默認的每頁記錄數
     * @param pageNum 頁碼
     * @return 商品列表
     */
    PageData<GoodsListItemVO> list(Integer pageNum);

    /**
     * 查詢商品列表
     * @param pageNum  頁碼
     * @param pageSize 每頁記錄數
     * @return 商品列表
     */
    PageData<GoodsListItemVO> list(Integer pageNum, Integer pageSize);

    /**
     * 跟據類別查詢商品列表,將使用默認的每頁記錄數
     * @param categoryId 商品類別的ID
     * @param pageNum    頁碼
     * @return  商品列表
     */
    PageData<GoodsListItemVO> listByCategory(Long categoryId, Integer pageNum);

    /**
     * 跟據類別查詢商品列表
     * @param categoryId 商品類別的ID
     * @param pageNum    頁碼
     * @param pageSize   每頁記錄數
     * @return 商品列表
     */
    PageData<GoodsListItemVO> listByCategory(Long categoryId, Integer pageNum, Integer pageSize);

    /**
     * 重建商品的搜索資料(更新ES中的商品資料)
     */
    void rebuildSearch();
}
