package cn.tedu.meimall.admin.mall.service;

import cn.tedu.meimall.admin.mall.pojo.param.ReceiverAddressAddNewParam;
import cn.tedu.meimall.admin.mall.pojo.param.ReceiverAddressUpdateParam;
import cn.tedu.meimall.admin.mall.pojo.vo.ReceiverAddressListItemVO;
import cn.tedu.meimall.admin.mall.pojo.vo.ReceiverAddressStandardVO;
import cn.tedu.meimall.common.pojo.authentication.CurrentPrincipal;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 處理收貨地址資料的業務頁面
 */
@Transactional
public interface IReceiverAddressService {

    /**
     * 添加收貨地址
     * @param currentPrincipal  當事人
     * @param receiverAddressAddNewParam   添加收貨地址的參數類
     */
    void addNew(CurrentPrincipal currentPrincipal, ReceiverAddressAddNewParam receiverAddressAddNewParam);

    /**
     * 刪除收貨地址
     * @param currentPrincipal 當事人
     * @param id               收貨地址ID
     */
    void delete(CurrentPrincipal currentPrincipal, Long id);

    /**
     * 修改收貨地址
     * @param currentPrincipal           當事人
     * @param receiverAddressUpdateParam 封裝了貨地址ID和新資料的對象
     */
    void updateInfoById(CurrentPrincipal currentPrincipal, Long id, ReceiverAddressUpdateParam receiverAddressUpdateParam);

    /**
     * 設置默認收貨地址
     * @param currentPrincipal 當事人
     * @param id               需要設置為默認狀態的收貨地址ID
     */
    void setDefault(CurrentPrincipal currentPrincipal, Long id);

    /**
     * 根據ID查詢收貨地址
     * @param currentPrincipal 當事人
     * @param id               收貨地址ID
     * @return 匹配的收貨地址資料詳情,如果沒有匹配的資料,則返回null
     */
    ReceiverAddressStandardVO getStandardById(CurrentPrincipal currentPrincipal, Long id);

    /**
     * 查詢收貨地址列表
     * @param currentPrincipal 當事人
     * @return 收貨地址列表
     */
    List<ReceiverAddressListItemVO> listByUser(CurrentPrincipal currentPrincipal);
}
