package cn.tedu.meimall.admin.mall.service;

import cn.tedu.meimall.admin.mall.pojo.vo.CheckLogListItemVO;
import cn.tedu.meimall.common.consts.data.MallConsts;
import cn.tedu.meimall.common.pojo.vo.PageData;
import org.springframework.transaction.annotation.Transactional;

/**
 * 處理審核日誌的業務介面
 */
@Transactional
public interface ICheckLogService extends MallConsts {

    /**
     * 查詢商品審核日置列表,將使用默認的每頁記錄數
     * @param pageNum 頁碼
     * @return 審核日誌列表
     */
    PageData<CheckLogListItemVO> listGoodsCheckLog(Integer pageNum);

    /**
     * 查詢商品審核日誌列表
     *
     * @param pageNum  頁碼
     * @param pageSize 每頁記錄數
     * @return 審核日誌列表
     */
    PageData<CheckLogListItemVO> listGoodsCheckLog(Integer pageNum, Integer pageSize);

    /**
     * 查詢評論審核日誌列表，將使用默認的每頁記錄數
     *
     * @param pageNum 頁碼
     * @return 審核日誌列表
     */
    PageData<CheckLogListItemVO> listCommentCheckLog(Integer pageNum);

    /**
     * 查詢評論審核日誌列表
     *
     * @param pageNum  頁碼
     * @param pageSize 每頁記錄數
     * @return 審核日誌列表
     */
    PageData<CheckLogListItemVO> listCommentCheckLog(Integer pageNum, Integer pageSize);

}
