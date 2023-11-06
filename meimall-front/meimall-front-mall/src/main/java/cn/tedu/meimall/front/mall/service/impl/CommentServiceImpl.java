package cn.tedu.meimall.front.mall.service.impl;

import cn.tedu.meimall.common.enumerator.ServiceCode;
import cn.tedu.meimall.common.ex.ServiceException;
import cn.tedu.meimall.common.pojo.authentication.CurrentPrincipal;
import cn.tedu.meimall.common.pojo.vo.PageData;
import cn.tedu.meimall.front.mall.dao.persist.repository.ICommentRepository;
import cn.tedu.meimall.front.mall.dao.persist.repository.IGoodsRepository;
import cn.tedu.meimall.front.mall.pojo.entity.Comment;
import cn.tedu.meimall.front.mall.pojo.entity.Goods;
import cn.tedu.meimall.front.mall.pojo.param.CommentAddNewParam;
import cn.tedu.meimall.front.mall.pojo.vo.CommentListItemVO;
import cn.tedu.meimall.front.mall.pojo.vo.GoodsStandardVO;
import cn.tedu.meimall.front.mall.service.ICommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.sql.rowset.serial.SerialException;

/**
 * 處理評論數據的業務實現類
 */
@Slf4j
@Service
public class CommentServiceImpl implements ICommentService {

    @Value("${meimall.dao.default-query-page-size}")
    private Integer defaultQueryPageSize;
    @Autowired
    private ICommentRepository commentRepository;
    @Autowired
    private IGoodsRepository goodsRepository;

    public CommentServiceImpl(){
        log.debug("創建業務類對象: CommentServiceImpl");
    }

    @Override
    public void addNew(CurrentPrincipal currentPrincipal, CommentAddNewParam commentAddNewParam) {
        log.debug("開始處理【發表評論】的業務,當事人:{}, 參數:{}",currentPrincipal, commentAddNewParam);

        Long goodsId = commentAddNewParam.getGoodsId();
        GoodsStandardVO currentGoods = goodsRepository.getStandardById(goodsId);
        if(currentGoods == null){
            String message = "發表評論失敗,商品數據不存在!";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
        }

        if(currentGoods.getIsPutOn() == PUT_ON_STATE_OFF){
            String message =  "發表評論失敗,此商品已經下架!";
            log.warn(message);
            throw  new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
        }

        // TODO 另需檢查是否購買過此商品
        // TODO 另需檢查是否已經評論過此商品

        Comment comment = new Comment();
        BeanUtils.copyProperties(commentAddNewParam , comment);
        comment.setAuthorId(currentPrincipal.getId());
        comment.setAuthorName(currentPrincipal.getUsername());
        comment.setCheckState(0);
        int rows = commentRepository.insert(comment);
        if(rows != 1){
            String message = "發表評論失敗,服務器忙,請稍後再嘗試!";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_INSERT, message);
        }

        Goods updateGoods = new Goods();
        updateGoods.setId(goodsId);
        updateGoods.setCommentCount(currentGoods.getCommentCount() +1);
        switch (commentAddNewParam.getType()){
            case COMMENT_TYPE_POSITIVE:
                updateGoods.setPositiveCommentCount(updateGoods.getPositiveCommentCount());
                break;
            case  COMMENT_TYPE_NEGATIVE:
                updateGoods.setNegativeCommentCount(updateGoods.getNegativeCommentCount());
                break;
        }
        rows = goodsRepository.update(updateGoods);
        if(rows != 1){
            String message = "發表評論失敗,服務器忙,請稍後再嘗試!";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_UPDATE, message);
        }
    }

    @Override
    public PageData<CommentListItemVO> list(Long goodsId, Integer pageNum) {
        log.debug("開始處理【查詢商品的評論列表】的業務,頁碼:{}", pageNum);
        return commentRepository.list(goodsId, pageNum, defaultQueryPageSize);
    }

    @Override
    public PageData<CommentListItemVO> list(Long goodsId, Integer pageNum, Integer pageSize) {
        log.debug("開始處理【查詢商品的評論列表】的業務,頁碼:{},每頁記錄數:{}", pageNum, pageSize);
        return commentRepository.list(goodsId, pageNum, pageSize);
    }
}
