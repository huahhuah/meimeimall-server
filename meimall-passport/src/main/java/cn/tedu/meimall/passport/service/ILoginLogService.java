package cn.tedu.meimall.passport.service;


import cn.tedu.meimall.passport.pojo.vo.LoginLogListItemVO;
import cn.tedu.meimall.common.pojo.vo.PageData;
import org.springframework.transaction.annotation.Transactional;

/**
 * 處理用戶登入日誌的業務接口
 *
 */
@Transactional
public interface ILoginLogService {

    /**
     * 查詢登入日誌列表，將使用默認的每頁記錄數
     *
     * @param pageNum 頁碼
     * @return 登入日誌列表
     */
    PageData<LoginLogListItemVO> list(Integer pageNum);

    /**
     * 查詢登入日誌列表
     *
     * @param pageNum  頁碼
     * @param pageSize 每頁記錄數
     * @return 登入日誌列表
     */
    PageData<LoginLogListItemVO> list(Integer pageNum, Integer pageSize);

}
