package cn.tedu.meimall.front.mall.service;

import cn.tedu.meimall.common.consts.data.MallConsts;
import cn.tedu.meimall.common.pojo.authentication.CurrentPrincipal;
import cn.tedu.meimall.common.pojo.vo.PageData;
import cn.tedu.meimall.front.mall.pojo.param.OrderAddNewParam;
import cn.tedu.meimall.front.mall.pojo.vo.OrderItemStandardVO;
import cn.tedu.meimall.front.mall.pojo.vo.OrderListItemVO;
import cn.tedu.meimall.front.mall.pojo.vo.OrderStandardVO;
import org.springframework.transaction.annotation.Transactional;

/**
 * 處理訂單資料的業務介面
 */
@Transactional
public interface IOrderService extends MallConsts {

    /**
     * 創建訂單
     * @param currentPrincipal 當事人
     * @param orderAddNewParam 創建訂單的參數類
     * @return 訂單ID
     */
    Long create(CurrentPrincipal currentPrincipal, OrderAddNewParam orderAddNewParam);

    /**
     * 根據ID查詢訂單
     * @param currentPrincipal 當事人
     * @param orderId          訂單ID
     * @return 訂單詳情
     */
    OrderStandardVO getStandardById(CurrentPrincipal currentPrincipal, Long orderId);

    /**
     * 查詢訂單列表,將使用默認的每頁記錄數
     * @param currentPrincipal 當事人
     * @param pageNum          頁碼
     * @return 訂單列表
     */
    PageData<OrderListItemVO> listByUser(CurrentPrincipal currentPrincipal, Integer pageNum);

    /**
     * 查詢訂單列表
     * @param currentPrincipal 當事人
     * @param pageNum          頁碼
     * @param pageSize         每頁記錄數
     * @return 訂單列表
     */
    PageData<OrderListItemVO> listByUser(CurrentPrincipal currentPrincipal,Integer pageNum, Integer pageSize);
}
