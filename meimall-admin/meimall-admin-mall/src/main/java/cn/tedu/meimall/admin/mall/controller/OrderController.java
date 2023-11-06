package cn.tedu.meimall.admin.mall.controller;

import cn.tedu.meimall.admin.mall.pojo.param.GoodsUpdateInfoParam;
import cn.tedu.meimall.admin.mall.pojo.param.OrderAddNewParam;
import cn.tedu.meimall.admin.mall.pojo.param.OrderUpdateParam;
import cn.tedu.meimall.admin.mall.pojo.vo.OrderListItemVO;
import cn.tedu.meimall.admin.mall.pojo.vo.OrderStandardVO;
import cn.tedu.meimall.admin.mall.service.IOrderService;
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

import javax.validation.Valid;

/**
 * 處理訂單相關請求的控制器類
 */
@Slf4j
@RestController
@RequestMapping("/orders")
@Validated
@Api(tags = "6. 訂單管理")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    public OrderController(){
        log.debug("創建控制器類對象: OrderController");
    }

    @PostMapping("/{id:[0-9]+}/delete")
    @ApiOperation("刪除訂單")
    @ApiOperationSupport(order = 200)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "訂單ID", required = true , dataType = "long")
    })
    public JsonResult delete(@AuthenticationPrincipal @ApiIgnore CurrentPrincipal currentPrincipal,
                             @PathVariable @Range(min = 1,message = "請提交有效的訂單ID值!" )Long id){
        log.debug("開始處理【刪除收貨地址】的請求,當事人:{},參數:{}", currentPrincipal, id);
        orderService.delete(id);
        return JsonResult.ok();
    }

    @PostMapping("/{id:[0-9]+}/update")
    @ApiOperation("修改訂單")
    @ApiOperationSupport(order = 300)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "訂單ID", required = true, dataType = "long")
    })
    public JsonResult updateInfoById(@PathVariable @Range(min= 1, message = "請提交有效的ID值!")Long id,
                                     @Valid OrderUpdateParam orderUpdateParam){
        log.debug("開始處理【修改訂單】的請求,ID:{},新數據:{}", id, orderUpdateParam);
       orderService.updateInfoById(id, orderUpdateParam);
        return JsonResult.ok();
    }

    @GetMapping("/{id:[0-9]+}")
    @ApiOperation("根據ID查詢訂單")
    @ApiOperationSupport(order = 410)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "訂單ID", required = true, dataType = "long")
    })
    public JsonResult getStandardById(@AuthenticationPrincipal @ApiIgnore CurrentPrincipal currentPrincipal,
                                      @PathVariable @Range(min = 1, message = "請輸入有效的ID值!")Long id){
        log.debug("開始處理【根據ID查詢訂單】的請求,參數:{}", id);
        OrderStandardVO order = orderService.getStandardById(currentPrincipal, id);
        return JsonResult.ok(order);
    }

    @GetMapping("")
    @ApiOperation("查詢訂單列表")
    @ApiOperationSupport(order = 421)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "頁碼", defaultValue = "1", paramType = "query", dataType = "int" )
    })
    public JsonResult listByUser(@AuthenticationPrincipal @ApiIgnore CurrentPrincipal currentPrincipal,
                                     @Range(min = 1, message = "請輸入有效的頁碼值!")Integer page){
        log.debug("開始處理【查詢訂單列表】的請求,頁碼:{}", page);
        Integer pageNum = page == null ? 1 : page;
        PageData<OrderListItemVO> pageData = orderService.listByUser(currentPrincipal, pageNum);
        return JsonResult.ok(pageData);
    }

}
