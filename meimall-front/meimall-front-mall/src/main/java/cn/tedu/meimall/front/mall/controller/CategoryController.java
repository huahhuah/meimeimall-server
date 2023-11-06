package cn.tedu.meimall.front.mall.controller;

import cn.tedu.meimall.common.pojo.vo.PageData;
import cn.tedu.meimall.common.web.JsonResult;
import cn.tedu.meimall.front.mall.pojo.vo.CategoryListItemVO;
import cn.tedu.meimall.front.mall.pojo.vo.CategoryTreeItemVO;
import cn.tedu.meimall.front.mall.service.ICategoryService;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 處理類別相關請求的控制器類別
 */
@Slf4j
@RestController
@RequestMapping("/categories")
@Validated
@Api(tags = "1. 類別管理")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    public CategoryController(){
        log.debug("創建控制器類對象: CategoryController");
    }

    @GetMapping("/tree")
    @ApiOperation("查詢類別樹")
    @ApiOperationSupport(order = 420)
    public JsonResult listTree(){
        log.debug("開始處理【獲取類別樹】的請求,參數: 無");
        List<CategoryTreeItemVO> categoryTree = categoryService.listTree();
        return JsonResult.ok(categoryTree);
    }

    @GetMapping("/list-by-parent")
    @ApiOperation("根據父級查詢子級列表")
    @ApiOperationSupport(order = 421)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "parentId", value = "父級類別ID", required = true, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "page",value = "頁碼", defaultValue = "1", paramType = "query" ,dataType = "int")
    })
    public JsonResult list(@Range(message = "請提交有效的父級類別ID值!")Long parentId,
                           @Range(min = 1, message = "請提交有效的頁碼值!")Integer page){
        log.debug("開始處理【根據父級類別查詢子級類別列表】的請求,父級類別:{},頁碼:{}", parentId, page);
        Integer pageNum = page == null ? 1 : page;
        PageData<CategoryListItemVO> pageData = categoryService.listByParent(parentId, pageNum);
        return JsonResult.ok(pageData);
    }

}
