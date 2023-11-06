package cn.tedu.meimall.passport.dao.persist.repository;


import cn.tedu.meimall.passport.pojo.entity.LoginLog;
import cn.tedu.meimall.passport.pojo.vo.LoginLogListItemVO;
import cn.tedu.meimall.common.pojo.vo.PageData;

/**
 * 處理登入日誌資料的存儲庫接口
 *
 */
public interface ILoginLogRepository {

    /**
     * 插入登入日誌
     *
     * @param loginLog 登入日誌
     * @return 受影響的行數
     */
    int insert(LoginLog loginLog);

    /**
     * 查詢用戶登入日誌列表
     *
     * @param pageNum  頁碼
     * @param pageSize 每頁記錄數
     * @return 用戶登入日誌列表
     */
    PageData<LoginLogListItemVO> list(Integer pageNum, Integer pageSize);

}
