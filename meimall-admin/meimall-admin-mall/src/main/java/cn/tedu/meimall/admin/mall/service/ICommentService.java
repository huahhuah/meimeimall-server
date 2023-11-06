package cn.tedu.meimall.admin.mall.service;

import cn.tedu.meimall.admin.mall.pojo.param.CommentAddInfoParam;
import cn.tedu.meimall.admin.mall.pojo.param.CommentUpdateInfoParam;
import cn.tedu.meimall.admin.mall.pojo.vo.CommentInfoVO;
import cn.tedu.meimall.admin.mall.pojo.vo.CommentListItemVO;
import cn.tedu.meimall.common.consts.data.MallConsts;
import cn.tedu.meimall.common.pojo.authentication.CurrentPrincipal;
import cn.tedu.meimall.common.pojo.vo.PageData;
import org.springframework.transaction.annotation.Transactional;

/**
 * 處理評論數據的業務介面
 */
@Transactional
public interface ICommentService extends MallConsts {

    /**
     * 新增評論
     * @param commentAddInfoParam 評論的參數
     */
    void add(CommentAddInfoParam commentAddInfoParam);

    /**
     * 根據ID刪除評論
     * @param id ID
     */
    void deleteById(Long id);

    /**
     * 更改評論
     * @param commentUpdateInfoParam  評論的參數
     */
    void update(CommentUpdateInfoParam commentUpdateInfoParam);

    /**
     * 審核通過評論
     * @param currentPrincipal 當事人
     * @param goodsId          嘗試審核通過的評論的ID
     * @param remarks          備註信息
     */
    void passCheck(CurrentPrincipal currentPrincipal, Long goodsId, String remarks);

    /**
     * 拒絕審核評論
     * @param currentPrincipal 當事人
     * @param goodsId          嘗試拒絕審核的評論ID
     * @param remarks          備註信息
     */
    void rejectCheck(CurrentPrincipal currentPrincipal, Long goodsId, String remarks);

    /**
     * 查詢商品的評論列表,將使用默認的每頁記錄數
     * @param pageNum 頁碼
     * @return 商品的評論列表
     */
    PageData<CommentListItemVO> list(Integer pageNum);

    /**
     * 查詢商品的評論列表
     * @param pageNum  頁碼
     * @param pageSize 每頁記錄數
     * @return 商品的評論列表
     */
    PageData<CommentListItemVO> list(Integer pageNum, Integer pageSize);

    /**
     * 根據作者ID查詢評論,將使用默認的每頁記錄數
     * @param pageNum  頁碼
     * @return 評論數據列表
     */
    PageData<CommentInfoVO> getCommentListByAuthorId(Long authorId, Integer pageNum);

    /**
     * 根據作者ID查詢評論
     * @param authorId 作者I
     * @param pageNum  頁碼
     * @param pageSize 每頁記錄數
     * @return 評論數據列表
     */
    PageData<CommentInfoVO> getCommentListByAuthorId(Long authorId, Integer pageNum, Integer pageSize);

    /**
     * 根據商品ID查詢評論,將使用默認的每頁記錄數
     * @param pageNum  頁碼
     * @return 評論數據列表
     */
    PageData<CommentInfoVO> getCommentListByGoodsId(Long goodsId,Integer pageNum);

    /**
     * 根據商品ID查詢評論
     * @param goodsId  商品ID
     * @param pageNum  頁碼
     * @param pageSize 每頁記錄數
     * @return 評論數據列表
     */
    PageData<CommentInfoVO> getCommentListByGoodsId(Long goodsId, Integer pageNum, Integer pageSize);

}
