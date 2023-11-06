package cn.tedu.meimall.admin.account.dao.persist.mapper;

import cn.tedu.meimall.admin.account.pojo.entity.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 處理用戶與角色的關聯資料的Mapper接口
 */
@Repository
public interface UserRoleMapper extends BaseMapper<UserRole> {

    /**
     * 批量插入用戶與角色的關聯資料
     * @param userRoleList 若干個用戶與角色的關聯資料的集合
     * @return 受影響的行數
     */
    int insertBatch(List<UserRole> userRoleList);
}
