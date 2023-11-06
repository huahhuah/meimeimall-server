package cn.tedu.meimall.admin.mall.controller;

import cn.tedu.meimall.admin.mall.pojo.param.CommentAddInfoParam;
import cn.tedu.meimall.admin.mall.pojo.vo.CommentInfoVO;
import cn.tedu.meimall.admin.mall.pojo.vo.CommentListItemVO;
import cn.tedu.meimall.admin.mall.service.ICommentService;
import cn.tedu.meimall.common.pojo.authentication.CurrentPrincipal;
import cn.tedu.meimall.common.pojo.vo.PageData;
import cn.tedu.meimall.common.web.JsonResult;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.constraints.NotBlank;

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

    @PostMapping("/add-new")
    @PreAuthorize("hasAuthority('/mall/category/simple')")
    @ApiOperation("添加評論")
    @ApiOperationSupport(order = 100)
    public JsonResult add(CommentAddInfoParam commentAddInfoParam){
        log.debug("開始處理【新增評論】的請求,參數:{}", commentAddInfoParam);
        commentService.add(commentAddInfoParam);
        return JsonResult.ok();
    }

    @PostMapping("/{id:[0-9]+}/delete")
    @PreAuthorize("hasAuthority('/mall/comment/delete')")
    @ApiOperation("根據ID刪除商品")
    @ApiOperationSupport(order = 200)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "商品ID", required = true, dataType = "long")
    })
    public JsonResult delete(@PathVariable @Range(min = 1, message = "請提交有效的商品ID值!") Long id) {
        log.debug("開始處理【根據ID刪除商品】的請求,參數:{}", id);
       commentService.deleteById(id);
        return JsonResult.ok();
    }

    @PostMapping("/{id:[0-9]+}/check-state/pass")
    @PreAuthorize("hasAuthority('/mall/comment/check')")
    @ApiOperation("審核通過評論")
    @ApiOperationSupport(order = 310)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "評論ID", required = true ,dataType = "long"),
            @ApiImplicitParam(name = "remarks", value = "備註信息", required = true, paramType = "query", dataType = "string")
    })
    public JsonResult passCheck(@AuthenticationPrincipal @ApiIgnore CurrentPrincipal currentPrincipal,
                                @PathVariable @Range(min = 1, message = "請提交有效的評論ID!") Long id,
                                @NotBlank(message = "請提交審核備註信息") String remarks){
        log.debug("開始處理【審核通過評論】的請求,參數:{}", id);
        commentService.passCheck(currentPrincipal, id , remarks);
        return JsonResult.ok();
    }

    @PostMapping("/{id:[0-9]+}/check-state/reject")
    @PreAuthorize("hasAuthority('/mall/comment/check')")
    @ApiOperation("拒絕審核評論")
    @ApiOperationSupport(order = 311)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "評論ID", required = true ,dataType = "long"),
            @ApiImplicitParam(name = "remarks", value = "備註信息", required = true, paramType = "query", dataType = "string")
    })
    public JsonResult rejectCheck(@AuthenticationPrincipal @ApiIgnore CurrentPrincipal currentPrincipal,
                                @PathVariable @Range(min = 1, message = "請提交有效的評論ID!") Long id,
                                @NotBlank(message = "請提交審核備註信息") String remarks){
        log.debug("開始處理【拒絕審核評論】的請求,參數:{}", id);
        commentService.rejectCheck(currentPrincipal, id , remarks);
        return JsonResult.ok();
    }

    @GetMapping("")
    @PreAuthorize("hasAuthority('/mall/comment/simple')")
    @ApiOperation("查詢商品的評論列表")
    @ApiOperationSupport(order = 420)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "頁碼", defaultValue = "1", paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "queryType", value = "查詢類型", example = "all")
    })
    public JsonResult list(@Range(min = 1, message = "請提交有效的頁碼值!") Integer page, String queryType){
        log.debug("開始處理【查詢商品的評論列表】的請求,頁碼:{}", page);
        Integer pageNum = page == null ? 1 : page;
        PageData<CommentListItemVO> pageData;
        if("all".equals(queryType)){
            pageData = commentService.list(pageNum, Integer.MAX_VALUE);
        }else {
            pageData = commentService.list(pageNum);
        }
        return JsonResult.ok(pageData);

    }

    /**
     * 根據作者ID查詢評論列表
     */
    @ApiOperation("根據作者ID查詢評論列表")
    @ApiOperationSupport(order = 420)//40統計，41查單個
    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorId",value = "作者ID",dataType = "Long"),
            @ApiImplicitParam(name = "page",value = "頁碼",dataType = "int"),
            @ApiImplicitParam(name = "queryType",value = "輸入all表示查詢所有資料",dataType = "String")
    })
    @GetMapping("/listByAuthorId/{authorId:[0-9]+}")
    public JsonResult getCommentListByAuthorId(@PathVariable @Range(min = 1,message = "查詢失敗，請提交合法的作者ID") Long authorId,
                                           Integer page,String queryType){
        if (page==null){
            page=1;
        }
        Integer pageNum = page > 0 ? page : 1;
        PageData<CommentInfoVO> pageData;
        if ("all".equals(queryType)){
            pageData = commentService.getCommentListByAuthorId(authorId,1, Integer.MAX_VALUE);
            log.debug("page:{},queryType:{}", 1, Integer.MAX_VALUE);
        }else {
            pageData = commentService.getCommentListByAuthorId(authorId,pageNum);
        }
        log.debug("pageData{}",pageData);
        return JsonResult.ok(pageData);
    }
    /**
     * 根據商品ID查詢評論列表
     */
    @ApiOperation("根據商品ID查詢評論列表")
    @ApiOperationSupport(order = 420)//40統計，41查單個
    @ApiImplicitParams({
            @ApiImplicitParam(name = "goodsId",value = "商品Id",dataType = "Long"),
            @ApiImplicitParam(name = "page",value = "頁碼",dataType = "int"),
            @ApiImplicitParam(name = "queryType",value = "輸入all表示查詢所有資料",dataType = "String")
    })
    @GetMapping("/listByUserId/{goodsId:[0-9]+}")
    public JsonResult getReviewListByUserId(@PathVariable @Range(min = 1,message = "查詢失敗，請提交合法的商品ID") Long goodsId,
                                            Integer page,String queryType){
        if (page==null){
            page=1;
        }
        Integer pageNum = page > 0 ? page : 1;
        PageData<CommentInfoVO> pageData;
        if ("all".equals(queryType)){
            pageData = commentService.getCommentListByGoodsId(goodsId,1, Integer.MAX_VALUE);
            log.debug("page:{},queryType:{}", 1, Integer.MAX_VALUE);
        }else {
            pageData = commentService.getCommentListByGoodsId(goodsId,pageNum);
        }
        log.debug("pageData{}",pageData);
        return JsonResult.ok(pageData);
    }

}
