package cn.tedu.meimall.front.account.dao.persist.mapper;

import cn.tedu.meimall.front.account.pojo.entity.UserRole;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 處理用戶與角色的關聯資料的Mapper介面
 */
@Repository
public interface UserRoleMapper  {

    /**
     *  插入管理員與角色的關聯資料
     * @param userRole 管理員與角色的關聯資料
     * @return 受影響的行數
     */
    int insert(UserRole userRole);

    /**
     * 批量插入用戶與角色的關聯資料
     *
     * @param userRoleList 用戶與角色的關聯資料的列表
     * @return 受影響的行數
     */
    int insertBatch(List<UserRole> userRoleList);

    /**
     * 根據用戶id删除用戶與角色的關聯資料
     *
     * @param userId 用戶id
     * @return 受影響的行數
     */
    int deleteByUserId(Long userId);
}
