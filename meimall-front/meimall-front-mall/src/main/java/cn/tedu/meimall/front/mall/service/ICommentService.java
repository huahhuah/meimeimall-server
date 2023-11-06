package cn.tedu.meimall.front.mall.service;

import cn.tedu.meimall.common.consts.data.MallConsts;
import cn.tedu.meimall.common.pojo.authentication.CurrentPrincipal;
import cn.tedu.meimall.common.pojo.vo.PageData;
import cn.tedu.meimall.front.mall.pojo.param.CommentAddNewParam;
import cn.tedu.meimall.front.mall.pojo.vo.CommentListItemVO;
import org.springframework.transaction.annotation.Transactional;

/**
 * 處理評論數據的業務介面
 */
@Transactional
public interface ICommentService extends MallConsts {

    /**
     * 發表評論
     * @param currentPrincipal   當事人
     * @param commentAddNewParam 新增的評論數據
     */
    void addNew(CurrentPrincipal currentPrincipal, CommentAddNewParam commentAddNewParam);

    /**
     * 查詢商品的評論列表,將使用默認的每頁記錄數
     * @param goodsId  商品ID
     * @param pageNum  頁碼
     * @return  商品的評論列表
     */
    PageData<CommentListItemVO> list(Long goodsId, Integer pageNum);

    /**
     * 查詢商品的評論數據列表
     * @param goodsId  商品ID
     * @param pageNum  頁碼
     * @param pageSize 每頁記錄數
     * @return  商品的評論列表
     */
    PageData<CommentListItemVO> list(Long goodsId, Integer pageNum, Integer pageSize);
}
