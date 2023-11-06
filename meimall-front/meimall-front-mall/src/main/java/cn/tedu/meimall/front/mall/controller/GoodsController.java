package cn.tedu.meimall.front.mall.controller;

import cn.tedu.meimall.common.pojo.vo.PageData;
import cn.tedu.meimall.common.web.JsonResult;
import cn.tedu.meimall.front.mall.pojo.vo.GoodsListItemVO;
import cn.tedu.meimall.front.mall.pojo.vo.GoodsStandardVO;
import cn.tedu.meimall.front.mall.service.IGoodsService;
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
 * 處理商品相關請求的控制器類
 */
@Slf4j
@RestController
@RequestMapping("/goods")
@Validated
@Api(tags = "2. 商品管理")
public class GoodsController {

    @Autowired
    private IGoodsService goodsService;

    public GoodsController(){
        log.debug("創建控制器類對象: GoodsController");
    }

    @GetMapping("/{id:[0-9]+}")
    @ApiOperation("根據ID查詢商品")
    @ApiOperationSupport(order = 410)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "商品ID", required = true, dataType = "long")
    })
    public JsonResult getStandardById(@PathVariable @Range(min = 1, message = "請提交有效的商品ID值!")Long id){
        log.debug("開始處理【根據ID查詢商品】的請求,參數:{}", id);
        GoodsStandardVO queryResult = goodsService.getStandardById(id);
        return JsonResult.ok(queryResult);
    }

    @GetMapping("/list-by-recommend")
    @ApiOperation("查詢推薦的商品列表")
    @ApiOperationSupport(order = 420)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "頁碼", defaultValue = "1",paramType = "query", dataType = "int")
    })
    public JsonResult listByRecommend(@Range(min = 1, message = "請提交有效的頁碼值!")Integer page){
        log.debug("開始處理【查詢推薦的商品列表】的請求,頁碼:{}", page);
        Integer pageNum = page == null ? 1: page;
        PageData<GoodsListItemVO> pageData = goodsService.listByRecommend(pageNum);
        return JsonResult.ok(pageData);
    }

    @GetMapping("/list-by-category")
    @ApiOperation("跟據類別查詢商品列表")
    @ApiOperationSupport(order = 421)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "categoryId", value = "商品類別ID", required = true,paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "page", value = "頁碼", defaultValue = "1",paramType = "query", dataType = "int")
    })
    public JsonResult listByCategory(@Range(message = "請提交有效的商品類別ID值!")Long categoryId,
                                     @Range(min = 1, message = "請提交有效的頁碼值!")Integer page){
        log.debug("開始處理【跟據類別查詢商品列表】的請求,父級商品:{}, 頁碼:{}",categoryId, page);
        Integer pageNum = page == null ? 1: page;
        PageData<GoodsListItemVO> pageData = goodsService.listByCategory(categoryId, pageNum);
        return JsonResult.ok(pageData);
    }

}
