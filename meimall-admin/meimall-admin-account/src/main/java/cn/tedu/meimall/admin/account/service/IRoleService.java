package cn.tedu.meimall.admin.account.service;

import cn.tedu.meimall.admin.account.pojo.vo.RoleListItemVO;
import cn.tedu.meimall.common.pojo.vo.PageData;
import org.springframework.transaction.annotation.Transactional;

/**
 * 處理角色資料的業務接口
 */
@Transactional
public interface IRoleService {

    /**
     * 查詢角色列表，將使用默認的每頁記錄數
     *
     * @param pageNum  頁碼
     * @return 角色列表
     */
    PageData<RoleListItemVO> list(Integer pageNum);

    /**
     * 查詢角色列表
     *
     * @param pageNum  頁碼
     * @param pageSize 每頁記錄數
     * @return 角色列表
     */
    PageData<RoleListItemVO> list(Integer pageNum, Integer pageSize);
}
