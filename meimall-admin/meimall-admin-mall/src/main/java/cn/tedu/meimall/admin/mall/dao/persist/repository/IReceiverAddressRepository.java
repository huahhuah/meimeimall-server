package cn.tedu.meimall.admin.mall.dao.persist.repository;


import cn.tedu.meimall.admin.mall.pojo.entity.ReceiverAddress;
import cn.tedu.meimall.admin.mall.pojo.vo.ReceiverAddressListItemVO;
import cn.tedu.meimall.admin.mall.pojo.vo.ReceiverAddressStandardVO;

import java.util.List;

/**
 * 處理收貨地址資料的存儲庫介面
 */
public interface IReceiverAddressRepository {

    /**
     * 插入收貨地址資料
     * @param receiverAddress 收貨地址資料
     * @return 受影響的行數
     */
    int insert(ReceiverAddress receiverAddress);

    /**
     * 根據ID刪除收貨地址資料
     * @param id 收貨地址ID
     * @return 受影響的行數
     */
    int deleteById(Long id);

    /**
     * 根據ID修改收貨地址資料
     * @param receiverAddress 封裝了收貨地址ID和新資料的對象
     * @return 受影響的行數
     */
    int update(ReceiverAddress receiverAddress);

    /**
     * 將用戶的所有收貨地址設置為非默認
     * @param userId 用戶ID
     * @return 受影響的行數
     */
    int updateNonDefaultByUser(Long userId);

    /**
     * 根據ID查詢收貨地址資料詳情
     * @param id 收貨地址ID
     * @return  匹配的收貨地址資料詳情,如果沒有匹配的資料,則返回null
     */
    ReceiverAddressStandardVO getStandardById(Long id);

    /**
     * 根據用戶查詢收貨地址列表
     * @param userId 收貨地址類別的ID
     * @return 收貨地址列表
     */
    List<ReceiverAddressListItemVO> listByUser(Long userId);
}
