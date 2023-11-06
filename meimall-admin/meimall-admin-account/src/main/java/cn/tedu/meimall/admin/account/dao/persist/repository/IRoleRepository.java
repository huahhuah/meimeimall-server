package cn.tedu.meimall.admin.account.dao.persist.repository;

import cn.tedu.meimall.admin.account.pojo.vo.RoleListItemVO;
import cn.tedu.meimall.common.pojo.vo.PageData;

/**
 * 處理角色資料的資料訪問接口
 */
public interface IRoleRepository {

    /**
     * 查詢角色列表
     * @param pageNum  頁碼
     * @param pageSize 每頁記錄數
     * @return 角色列表
     */
    PageData<RoleListItemVO> list(Integer pageNum, Integer pageSize);
}
