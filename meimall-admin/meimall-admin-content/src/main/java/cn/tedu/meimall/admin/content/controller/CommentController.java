package cn.tedu.meimall.admin.content.controller;

import cn.tedu.meimall.admin.content.pojo.param.CommentAddNewParam;
import cn.tedu.meimall.admin.content.pojo.vo.CommentListItemVO;
import cn.tedu.meimall.admin.content.service.ICommentService;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.constraints.NotEmpty;

/**
 * 處理評論相關請求的控制類
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
        log.debug("創建控制器對象: CommentController");
    }

    @PostMapping("/add-new")
    @PreAuthorize("hasAuthority('/content/comment/simple')")
    @ApiOperation("添加評論")
    @ApiOperationSupport(order = 100)
    public JsonResult add(CommentAddNewParam commentAddNewParam){
        log.debug("開始處理【新增評論】的請求,參數:{}", commentAddNewParam);
        commentService.add(commentAddNewParam);
        return JsonResult.ok();
    }

    @PostMapping("/{id:[0-9]+}/delete")
    @PreAuthorize("hasAuthority('/content/comment/delete')")
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

    @PostMapping("/{id:[0-9]+}/pass-check")
    @PreAuthorize("hasAuthority('/content/comment/simple')")
    @ApiOperation("審核通過評論")
    @ApiOperationSupport(order = 310)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "評論ID", required = true, dataType = "long"),
            @ApiImplicitParam(name = "remarks",value = "審核備注",required = true, paramType = "query")
    })
    public JsonResult passCheck(@AuthenticationPrincipal @ApiIgnore CurrentPrincipal currentPrincipal,
                                @PathVariable @Range(min = 1, message = "請提交有效的評論ID值!") Long id,
                                @NotEmpty(message = "審核備注不允許為空") String remarks){
        log.debug("開始處理【審核通過評論】的請求,當事人:{},評論ID:{},審核備注:{}", currentPrincipal, id, remarks);
        commentService.passCheck(currentPrincipal, id , remarks);
        return JsonResult.ok();
    }

    @PostMapping("/{id:[0-9]+}/cnacel-check")
    @PreAuthorize("hasAuthority('/content/comment/simple')")
    @ApiOperation("拒絕審核評論")
    @ApiOperationSupport(order = 311)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "評論ID", required = true, dataType = "long"),
            @ApiImplicitParam(name = "remarks", value = "審核備注", required = true, paramType = "query")
    })
    public JsonResult cancelCheck(@AuthenticationPrincipal @ApiIgnore CurrentPrincipal currentPrincipal,
                                  @PathVariable @Range(min = 1, message = "請提交有效的評論ID值!") Long id,
                                  @NotEmpty(message = "審核備注不能為空") String remarks){
        log.debug("開始處理【拒絕審核評論】的請求,當事人{},評論ID:{},審核備注:{}",currentPrincipal, id, remarks);
        commentService.cancelCheck(currentPrincipal, id, remarks);
        return JsonResult.ok();
    }

    @PostMapping("/{id:[0-9]+}/display")
    @PreAuthorize("hasAuthority('/content/comment/simple')")
    @ApiOperation("顯示評論")
    @ApiOperationSupport(order = 312)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "評論ID", required = true, dataType = "long"),
    })
    public JsonResult setDisplay(@PathVariable @Range(min = 1, message = "請提交有效的評論ID值") Long id){
        log.debug("開始處理【顯示評論】的請求,參數:{}",id);
        commentService.setDisplay(id);
        return JsonResult.ok();
    }

    @PostMapping("/{id:[0-9]+}/hidden")
    @PreAuthorize("hasAuthority('/content/comment/simple')")
    @ApiOperation("隱藏評論")
    @ApiOperationSupport(order = 313)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "評論ID", required = true, dataType = "long")
    })
    public JsonResult setHidden(@PathVariable @Range(min = 1, message = "請提交有效的評論ID值!") Long id){
        log.debug("開始處理【隱藏評論】的請求，參數:{}",id);
        commentService.setHidden(id);
        return JsonResult.ok();
    }

    @GetMapping("/list-by-article")
    @PreAuthorize("hasAuthority('/content/comment/simple')")
    @ApiOperation("查詢文章列表的評論表")
    @ApiOperationSupport(order = 420)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "頁碼", defaultValue = "1", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "queryType", value = "查詢類別", example = "all")
    })
    public JsonResult listByArticle(@Range(min = 1, message = "請提交有效的頁碼值!") Integer page, String queryType){
        log.debug("開始處理【查詢文章的評論列表】的請求,頁碼:{}",page);
        Integer pageNum = page == null ? 1 : page;
        PageData<CommentListItemVO> pageData;
        if("all".equals(queryType)){
            pageData = commentService.listByArticle(pageNum, Integer.MAX_VALUE);
        }else {
            pageData = commentService.listByArticle(pageNum);
        }
        return JsonResult.ok(pageData);
    }

    @GetMapping("/list-by-comment")
    @PreAuthorize("hasAuthority('/content/comment/simple')")
    @ApiOperation("查詢評論的評論列表")
    @ApiOperationSupport(order = 421)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "頁碼",defaultValue = "1", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "queryType", value = "查詢類型",example = "all")
    })
    public JsonResult listByComment(@Range(min =1 ,message = "請提交有效的頁碼值!") Integer page, String queryType){
        log.debug("開始處理【查詢評論的評論列表】的請求,頁碼:{}",page);
        Integer pageNum = page == null ? 1 : page;
        PageData<CommentListItemVO> pageData;
        if("all".equals(queryType)){
            pageData = commentService.listByComment(pageNum, Integer.MAX_VALUE);
        }else {
            pageData = commentService.listByComment(pageNum);
        }
        return JsonResult.ok(pageData);
    }

}
