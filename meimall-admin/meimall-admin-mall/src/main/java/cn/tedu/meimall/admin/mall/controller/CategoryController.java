package cn.tedu.meimall.admin.mall.controller;

import cn.tedu.meimall.admin.mall.pojo.param.CategoryAddNewParam;
import cn.tedu.meimall.admin.mall.pojo.param.CategoryUpdateInfoParam;
import cn.tedu.meimall.admin.mall.pojo.vo.CategoryListItemVO;
import cn.tedu.meimall.admin.mall.pojo.vo.CategoryStandardVO;
import cn.tedu.meimall.admin.mall.pojo.vo.CategoryTreeItemVO;
import cn.tedu.meimall.admin.mall.service.ICategoryService;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 處理類別相關請求的控制器類
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

    @PostMapping("/add-new")
    @PreAuthorize("hasAuthority('/mall/category/simple')")
    @ApiOperation("添加類別")
    @ApiOperationSupport(order = 100)
    public JsonResult addNew(@Valid CategoryAddNewParam categoryAddNewParam){
        log.debug("開始處理【添加類別】的請求,參數:{}", categoryAddNewParam);
        categoryService.addNew(categoryAddNewParam);
        return JsonResult.ok();
    }

    @PostMapping("/{id:[0-9]+}/delete")
    @PreAuthorize("hasAuthority('/mall/category/delete')")
    @ApiOperation("根據ID刪除類別")
    @ApiOperationSupport(order = 200)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "類別ID", required = true, dataType = "long")
    })
    public JsonResult delete(@PathVariable @Range(min= 1, message = "請提交有效的ID值!")Long id){
        log.debug("開始處理【根據ID刪除類別】的請求,參數:{}", id);
        categoryService.delete(id);
        return JsonResult.ok();
    }

    @PostMapping("/{id:[0-9]+}/enable")
    @PreAuthorize("hasAuthority('/mall/category/simple')")
    @ApiOperation("啟用類別")
    @ApiOperationSupport(order = 310)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "類別ID", required = true, dataType = "long")
    })
    public JsonResult setEnable(@PathVariable @Range(min= 1, message = "請提交有效的ID值!")Long id){
        log.debug("開始處理【啟用類別】的請求,參數:{}", id);
        categoryService.setEnable(id);
        return JsonResult.ok();
    }

    @PostMapping("/{id:[0-9]+}/disable")
    @PreAuthorize("hasAuthority('/mall/category/simple')")
    @ApiOperation("禁用類別")
    @ApiOperationSupport(order = 311)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "類別ID", required = true, dataType = "long")
    })
    public JsonResult setDisable(@PathVariable @Range(min= 1, message = "請提交有效的ID值!")Long id){
        log.debug("開始處理【禁用類別】的請求,參數:{}", id);
        categoryService.setDisable(id);
        return JsonResult.ok();
    }

    @PostMapping("/{id:[0-9]+}/display")
    @PreAuthorize("hasAuthority('/mall/category/simple')")
    @ApiOperation("顯示類別")
    @ApiOperationSupport(order = 312)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "類別ID", required = true, dataType = "long")
    })
    public JsonResult setDisplay(@PathVariable @Range(min= 1, message = "請提交有效的ID值!")Long id){
        log.debug("開始處理【顯示類別】的請求,參數:{}", id);
        categoryService.setDisplay(id);
        return JsonResult.ok();
    }


    @PostMapping("/{id:[0-9]+}/idden")
    @PreAuthorize("hasAuthority('/mall/category/simple')")
    @ApiOperation("隱藏類別")
    @ApiOperationSupport(order = 313)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "類別ID", required = true, dataType = "long")
    })
    public JsonResult setHidden(@PathVariable @Range(min= 1, message = "請提交有效的ID值!")Long id){
        log.debug("開始處理【顯示類別】的請求,參數:{}", id);
        categoryService.setHidden(id);
        return JsonResult.ok();
    }


    @PostMapping("/{id:[0-9]+}/update")
    @PreAuthorize("hasAuthority('/mall/category/simple')")
    @ApiOperation("修改類別詳情")
    @ApiOperationSupport(order = 300)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "類別ID", required = true, dataType = "long")
    })
    public JsonResult updateInfoById(@PathVariable @Range(min= 1, message = "請提交有效的ID值!")Long id,
                                     @Valid CategoryUpdateInfoParam categoryUpdateInfoParam){
        log.debug("開始處理【修改類別詳情】的請求,ID:{},新資料:{}", id, categoryUpdateInfoParam);
        categoryService.updateInfoById(id, categoryUpdateInfoParam);
        return JsonResult.ok();
    }

    @GetMapping("/{id:[0-9]+}")
    @PreAuthorize("hasAuthority('/mall/category/simple')")
    @ApiOperation("根據ID查詢類别")
    @ApiOperationSupport(order = 410)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "類别ID", required = true, dataType = "long")
    })
    public JsonResult getStandardById(
            @PathVariable @Range(min = 1, message = "請提交有效的類别ID值！") Long id) {
        log.debug("開始處理【根據ID查詢類别】的請求，參數：{}", id);
        CategoryStandardVO queryResult = categoryService.getStandardById(id);
        return JsonResult.ok(queryResult);
    }

    @GetMapping("/tree")
    @PreAuthorize("hasAuthority('/mall/category/simple')")
    @ApiOperation("查詢類别樹")
    @ApiOperationSupport(order = 420)
    public JsonResult listTree() {
        log.debug("開始處理【獲取類别樹】的業務，參數：無");
        List<CategoryTreeItemVO> categoryTree = categoryService.listTree();
        return JsonResult.ok(categoryTree);
    }

    @GetMapping("/list-by-parent")
    @PreAuthorize("hasAuthority('/mall/category/simple')")
    @ApiOperation("根據父級查詢子級列表")
    @ApiOperationSupport(order = 421)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "parentId", value = "父級類别ID", required = true, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "page", value = "頁碼", defaultValue = "1", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "queryType", value = "查詢類型", example = "all")
    })
    public JsonResult list(@Range(message = "請提交有效的父級類别ID值！") Long parentId,
                           @Range(min = 1, message = "請提交有效的頁碼值！") Integer page,
                           String queryType) {
        log.debug("開始處理【根據父級查詢子級列表】的請求，父級類别：{}，頁碼：{}", parentId, page);
        Integer pageNum = page == null ? 1 : page;
        PageData<CategoryListItemVO> pageData;
        if ("all".equals(queryType)) {
            pageData = categoryService.listByParent(parentId, pageNum, Integer.MAX_VALUE);
        } else {
            pageData = categoryService.listByParent(parentId, pageNum);
        }
        return JsonResult.ok(pageData);
    }
}
