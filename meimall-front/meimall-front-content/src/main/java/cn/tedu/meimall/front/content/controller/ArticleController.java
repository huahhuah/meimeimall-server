package cn.tedu.meimall.front.content.controller;

import cn.tedu.meimall.common.pojo.vo.PageData;
import cn.tedu.meimall.common.web.JsonResult;
import cn.tedu.meimall.front.content.pojo.vo.ArticleListItemVO;
import cn.tedu.meimall.front.content.pojo.vo.ArticleStandardVO;
import cn.tedu.meimall.front.content.service.IArticleService;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    public ArticleController(){
        log.debug("創建控制器對象: ArticleController");
    }

    @GetMapping("/{id:[0-9]+}")
    @ApiOperation("根據ID查詢文章")
    @ApiOperationSupport(order = 410)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "文章ID", required = true, dataType = "long")
    })
    public JsonResult getStandardByID(
            @PathVariable @Range(min = 1, message = "請提交有效的文章ID值!")Long id){
        log.debug("開始處理【根據ID查詢文章】的請求,參數:{}", id);
        ArticleStandardVO queryResult = articleService.getStandardById(id);
        return JsonResult.ok(queryResult);
    }

    @GetMapping("/recommend")
    @ApiOperation("查詢推薦的文章列表")
    @ApiOperationSupport(order = 420)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "頁碼", defaultValue = "1", paramType = "query", dataType = "int")
    })
    public JsonResult listByRecommend(@Range(min = 1, message = "請提交有效的頁碼值!")Integer page){
        log.debug("開始處理【查詢推薦的文章列表】的請求,頁碼:{}", page);
        Integer pageNum = page == null ? 1 :page;
        PageData<ArticleListItemVO> pageData = articleService.listByRecommend(pageNum);
        return JsonResult.ok(pageData);
    }

    @GetMapping("/list-by-category")
    @ApiOperation("根據類別查詢文章列表")
    @ApiOperationSupport(order = 421)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "categoryId", value = "文章類別ID", defaultValue = "1", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "page", value = "頁碼", defaultValue = "1", paramType = "query", dataType = "int")

    })
    public JsonResult listByCategory(@Range(message = "請提交有效的文章類別ID值!")Long categoryId,
                                     @Range(min = 1, message = "請提交有效的頁碼值!")Integer page){
        log.debug("開始處理【跟據類別查詢文章列表】的請求,父級文章:{}, 頁碼:{}",categoryId, page);
        Integer pageNum = page == null ? 1 :page;
        PageData<ArticleListItemVO> pageData = articleService.listByCategory(categoryId, pageNum);
        return JsonResult.ok(pageData);
    }

}
