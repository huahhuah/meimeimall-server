package cn.tedu.meimall.front.mall.controller;

import cn.tedu.meimall.common.pojo.authentication.CurrentPrincipal;
import cn.tedu.meimall.common.pojo.vo.PageData;
import cn.tedu.meimall.common.web.JsonResult;
import cn.tedu.meimall.front.mall.pojo.param.CommentAddNewParam;
import cn.tedu.meimall.front.mall.pojo.vo.CommentListItemVO;
import cn.tedu.meimall.front.mall.service.ICommentService;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

/**
 * 處理評論相關請求的控制器類
 */
@Slf4j
@RestController
@RequestMapping("/comments")
@Validated
@Api(tags = "3. 評論管理")
public class CommentController {

    @Autowired
    private ICommentService commentService;

    public CommentController(){
        log.debug("創建控制器類對象: CommentController");
    }

    @PostMapping("/add-new/goods-comment")
    @ApiOperation("發表商品評論")
    @ApiOperationSupport(order = 100)
    public JsonResult addNewArticleComment(@AuthenticationPrincipal @ApiIgnore CurrentPrincipal currentPrincipal,
                                           @Valid CommentAddNewParam commentAddNewParam){
        log.debug("開始處理【發表商品評論】的請求,參數:{}", commentAddNewParam);
        commentService.addNew(currentPrincipal, commentAddNewParam);
        return JsonResult.ok();
    }

    @GetMapping("/list-by-goods")
    @ApiOperation("查詢商品的評論列表")
    @ApiOperationSupport(order = 420)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "goodsId", value = "商品ID", required = true, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "page", value = "頁碼", defaultValue = "1", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "queryType", value = "查詢類型", example = "all")
    })
    public JsonResult listByGoods(@Range(min = 1, message = "請提交有效的商品ID!") Long goodsId,
                                  @Range(min = 1 ,message = "請提交有效的頁碼值!") Integer page){
        log.debug("開始處理【查詢商品的評論列表】的請求,頁碼:{}", page);
        Integer pageNum = page == null ? 1 : page;
        PageData<CommentListItemVO> pageData = commentService.list(goodsId, pageNum);
        return JsonResult.ok(pageData);
    }

}
