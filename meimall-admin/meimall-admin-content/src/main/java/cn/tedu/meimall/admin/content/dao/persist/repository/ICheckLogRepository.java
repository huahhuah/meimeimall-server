package cn.tedu.meimall.admin.content.dao.persist.repository;

import cn.tedu.meimall.admin.content.pojo.entity.CheckLog;
import cn.tedu.meimall.admin.content.pojo.vo.CheckLogListItemVO;
import cn.tedu.meimall.common.pojo.vo.PageData;

/**
 * 處理審核日誌資料的存儲庫接口
 */
public interface ICheckLogRepository {

    /**
     * 插入審核日誌資料
     * @param checkLog 審核日誌資料
     * @return 受影響的行數
     */
    int insert(CheckLog checkLog);

    /**
     * 根據資源刪除審核日誌
     * @param resourceType 資源類型
     * @param resourceId   資源ID
     * @return 受影響的行數
     */
    int deleteByResource(Integer resourceType, Long resourceId);

    /**
     * 查詢審核日誌列表
     * @param resourceType  資源類型
     * @param pageNum       頁碼
     * @param pageSize      每頁記錄數
     * @return 審核日誌列表
     */
    PageData<CheckLogListItemVO> listByResourceType(int resourceType, Integer pageNum, Integer pageSize);
}
