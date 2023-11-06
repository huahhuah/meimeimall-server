package cn.tedu.meimall.admin.account.dao.persist.repository;

import cn.tedu.meimall.admin.account.pojo.entity.UserRole;

import java.util.List;

/**
 * 處理用戶角色資料的資料訪問接口
 */
public interface IUserRoleRepository {

    /**
     * 批量插入用戶與角色的關聯資料
     * @param userRoleList 若干個用戶與角色的關聯資料的集合
     * @return 受影響的行數
     */
    int insertBatch(List<UserRole> userRoleList);

    /**
     * 根據用戶ID刪除用戶與角色的關聯資料
     * @param userId 用戶ID
     * @return 受影響的行數
     */
    int deleteByUserId(Long userId);
}
