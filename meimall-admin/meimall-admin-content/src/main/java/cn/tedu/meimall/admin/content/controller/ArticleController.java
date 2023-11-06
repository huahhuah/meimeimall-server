package cn.tedu.meimall.admin.content.controller;

import cn.tedu.meimall.admin.content.pojo.param.ArticleAddNewParam;
import cn.tedu.meimall.admin.content.pojo.vo.ArticleListItemVO;
import cn.tedu.meimall.admin.content.pojo.vo.ArticleStandardVO;
import cn.tedu.meimall.admin.content.service.IArticleService;
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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

/**
 * 處理文章相關請求的控制器類
 */
@Slf4j
@RestController
@RequestMapping("/articles")
@Validated
@Api(tags = "2. 文章管理")
public class ArticleController {

    @Autowired
    private IArticleService articleService;

    public ArticleController() {
        log.debug("創建控制器對象: ArticleController");
    }

    @PostMapping("/add-new")
    @PreAuthorize("hasAuthority('/content/article/simple')")
    @ApiOperation("發布文章")
    @ApiOperationSupport(order = 100)
    public JsonResult addNew(@AuthenticationPrincipal @ApiIgnore CurrentPrincipal currentPrincipal,
                             @ApiIgnore HttpServletRequest request,
                             @Valid ArticleAddNewParam articleAddNewParam) {
        log.debug("開始處理【發布文章】的請求,參數:{}", articleAddNewParam);
        String remoteAddr = request.getRemoteAddr();
        articleService.addNew(currentPrincipal, remoteAddr, articleAddNewParam);
        return JsonResult.ok();
    }

    @PostMapping("/{id:[0-9]+}/delete")
    @PreAuthorize("hasAuthority('/content/article/delete')")
    @ApiOperation("根據ID刪除文章")
    @ApiOperationSupport(order = 200)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "文章ID", required = true, dataType = "long")
    })
    public JsonResult delete(@PathVariable @Range(min = 1, message = "請輸入有效的文章ID值!") Long id) {
        log.debug("開始處理【根據ID刪除文章】的請求,參數:{}", id);
        articleService.delete(id);
        return JsonResult.ok();
    }

    @PostMapping("/{id:[0-9]+}/pass-check")
    @PreAuthorize("hasAuthority('/content/article/check')")
    @ApiOperation("審核通過文章")
    @ApiOperationSupport(order = 310)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "文章ID", required = true, dataType = "long"),
            @ApiImplicitParam(name = "remarks", value = "備注審核", required = true, paramType = "query")
    })
    public JsonResult passCheck(@AuthenticationPrincipal @ApiIgnore CurrentPrincipal currentPrincipal,
                                @PathVariable @Range(min = 1, message = "請輸入有效的文章ID值!") Long id,
                                @NotEmpty(message = "審核備注不允許為空") String remarks) {
        log.debug("開始處理【審核通過文章】的請求,當事人:{}, 文章ID:{},審核備注:{}", currentPrincipal, id, remarks);
        articleService.passCheck(currentPrincipal, id, remarks);
        return JsonResult.ok();
    }

    @PostMapping("/{id:[0-9]+}/cancel-check")
    @PreAuthorize("hasAuthority('/content/article/check')")
    @ApiOperation("拒絕審核文章")
    @ApiOperationSupport(order = 311)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "文章ID", required = true, dataType = "long"),
            @ApiImplicitParam(name = "remarks", value = "審核備注", required = true, paramType = "query")
    })
    public JsonResult rejectCheck(@AuthenticationPrincipal @ApiIgnore CurrentPrincipal currentPrincipal,
                                  @PathVariable @Range(min = 1, message = "請提交有效的文章ID值！") Long id,
                                  @NotEmpty(message = "審核備注不允許為空") String remarks) {
        log.debug("開始處理【拒絕審核文章】的請求，當事人：{}，文章ID：{}，審核備注：{}", currentPrincipal, id, remarks);
        articleService.rejectCheck(currentPrincipal, id, remarks);
        return JsonResult.ok();
    }

    @PostMapping("/{id:[0-9]+}/display")
    @PreAuthorize("hasAuthority('/content/article/simple')")
    @ApiOperation("顯示文章")
    @ApiOperationSupport(order = 312)
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "文章ID", required = true, dataType = "long")
    })
    public JsonResult setDisplay(@PathVariable @Range(min = 1, message = "請提交有效的文章ID值！") Long id) {
        log.debug("開始處理【顯示文章】的請求，參數：{}", id);
        articleService.setDisplay(id);
        return JsonResult.ok();
    }

    @PostMapping("/{id:[0-9]+}/hidden")
    @PreAuthorize("hasAuthority('/content/article/simple')")
    @ApiOperation("隱藏文章")
    @ApiOperationSupport(order = 313)
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "文章ID", required = true, dataType = "long")
    })
    public JsonResult setHidden(@PathVariable @Range(min = 1, message = "請提交有效的文章ID值！") Long id) {
        log.debug("開始處理【隱藏文章】的請求，參數：{}", id);
        articleService.setHidden(id);
        return JsonResult.ok();
    }

    @GetMapping("/{id:[0-9]+}")
    @PreAuthorize("hasAuthority('/content/article/simple')")
    @ApiOperation("根據ID查詢文章")
    @ApiOperationSupport(order = 410)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "文章ID", required = true, dataType = "long")
    })
    public JsonResult getStandardById(
            @PathVariable @Range(min = 1, message = "請提交有效的文章ID值！") Long id) {
        log.debug("開始處理【根據ID查詢文章詳情】的請求，參數：{}", id);
        ArticleStandardVO queryResult = articleService.getStandardById(id);
        return JsonResult.ok(queryResult);
    }

    @GetMapping("")
    @PreAuthorize("hasAuthority('/content/article/simple')")
    @ApiOperation("查詢文章列表")
    @ApiOperationSupport(order = 420)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "頁碼", defaultValue = "1", paramType = "query", dataType = "int")
    })
    public JsonResult list(@Range(min = 1, message = "請提交有效的頁碼值！") Integer page) {
        log.debug("開始處理【查詢文章列表】的請求，頁碼：{}", page);
        Integer pageNum = page == null ? 1 : page;
        PageData<ArticleListItemVO> pageData = articleService.list(pageNum);
        return JsonResult.ok(pageData);
    }

    @GetMapping("/list-by-category")
    @PreAuthorize("hasAuthority('/content/article/simple')")
    @ApiOperation("根據類别查詢文章列表")
    @ApiOperationSupport(order = 421)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "categoryId", value = "文章類别ID", required = true, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "page", value = "頁碼", defaultValue = "1", paramType = "query", dataType = "int")
    })
    public JsonResult listByCategory(@Range(message = "請提交有效的文章類别ID值！") Long categoryId,
                                     @Range(min = 1, message = "請提交有效的頁碼值！") Integer page) {
        log.debug("開始處理【根據類别查詢文章列表】的請求，父級文章：{}，頁碼：{}", categoryId, page);
        Integer pageNum = page == null ? 1 : page;
        PageData<ArticleListItemVO> pageData = articleService.listByCategory(categoryId, pageNum);
        return JsonResult.ok(pageData);
    }

    @PostMapping("/rebuild-search")
    @PreAuthorize("hasAuthority('/content/article/rebuild-search')")
    @ApiOperation("更新搜索服務器中的文章列表")
    @ApiOperationSupport(order = 500)
    public JsonResult rebuildSearch() {
        log.debug("開始處理【更新搜索服務器中的文章列表】的請求，無參數");
        articleService.rebuildSearch();
        return JsonResult.ok();
    }
}
