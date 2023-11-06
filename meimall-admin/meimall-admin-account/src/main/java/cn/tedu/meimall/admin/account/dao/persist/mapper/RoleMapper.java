package cn.tedu.meimall.admin.account.dao.persist.mapper;

import cn.tedu.meimall.admin.account.pojo.vo.RoleListItemVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 處理角色資料的Mapper接口
 */
@Repository
public interface RoleMapper {

    /**
     * 查詢角色列表
     * @return 角色列表
     */
    List<RoleListItemVO> list();
}
