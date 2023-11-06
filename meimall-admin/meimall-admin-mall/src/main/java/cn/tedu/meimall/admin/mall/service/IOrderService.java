package cn.tedu.meimall.admin.mall.service;

import cn.tedu.meimall.admin.mall.pojo.param.CategoryUpdateInfoParam;
import cn.tedu.meimall.admin.mall.pojo.param.OrderAddNewParam;
import cn.tedu.meimall.admin.mall.pojo.param.OrderUpdateParam;
import cn.tedu.meimall.admin.mall.pojo.vo.OrderListItemVO;
import cn.tedu.meimall.admin.mall.pojo.vo.OrderStandardVO;
import cn.tedu.meimall.common.consts.data.MallConsts;
import cn.tedu.meimall.common.pojo.authentication.CurrentPrincipal;
import cn.tedu.meimall.common.pojo.vo.PageData;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * 處理訂單資料的業務接口
 */
@Transactional
public interface IOrderService extends MallConsts {

    /**
     * 根據ID刪除訂單
     * @param id 嘗試刪除的訂單資料的ID
     */
    void delete( Long id);

    /**
     * 修改訂單資料
     * @param id              被修改的訂單資料的ID
     * @param updateInfoParam 類別的新資料
     */
    void updateInfoById(Long id, OrderUpdateParam updateInfoParam);

    /**
     * 根據ID查詢訂單
     *
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
