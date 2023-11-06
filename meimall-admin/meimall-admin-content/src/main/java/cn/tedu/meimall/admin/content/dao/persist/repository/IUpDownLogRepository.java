package cn.tedu.meimall.admin.content.dao.persist.repository;

/**
 * 處理頂踩日誌資料的存儲庫接口
 */
public interface IUpDownLogRepository {
    /**
     * 根據資源刪除頂踩記錄
     * @param resourceType 資源類型
     * @param resourceID   資源ID
     * @return 受影響的行數
     */
    int deleteByResource(Integer resourceType, Long resourceID);
}
