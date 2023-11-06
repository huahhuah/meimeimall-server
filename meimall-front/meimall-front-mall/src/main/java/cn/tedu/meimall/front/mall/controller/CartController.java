package cn.tedu.meimall.front.mall.controller;

import cn.tedu.meimall.common.pojo.authentication.CurrentPrincipal;
import cn.tedu.meimall.common.web.JsonResult;
import cn.tedu.meimall.front.mall.pojo.vo.CartListItemVO;
import cn.tedu.meimall.front.mall.service.ICartService;
import com.fasterxml.jackson.annotation.JsonFormat;
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

import java.util.List;

/**
 * 處理購物車相關請求的控制器類
 */
@Slf4j
@RestController
@RequestMapping("/carts")
@Validated
@Api(tags = "5. 購物車管理")
public class CartController {

    @Autowired
    private ICartService cartService;

    public CartController(){
        log.debug("創建控制器類對象: CartController");
    }

    @PostMapping("/add")
    @ApiOperation("添加商品到購物車")
    @ApiOperationSupport(order = 100)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "goodsId", value = "商品ID", required = true, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "goodsNum", value = "商品數量", required = true, paramType = "query", dataType = "int")
    })
    public JsonResult add(@AuthenticationPrincipal @ApiIgnore CurrentPrincipal currentPrincipal,
                          @Range(min = 1, message = "請提交合法的商品ID值！") Long goodsId,
                          @Range(min = 1, max = 100, message = "商品數量必須是1~100之間！") Integer goodsNum) {
        log.debug("開始處理【添加商品到購物車】的請求，當事人：{}，商品：{}，數量：{}", currentPrincipal, goodsId, goodsNum);
        cartService.add(currentPrincipal, goodsId, goodsNum);
        return JsonResult.ok();
    }

    @PostMapping("/delete")
    @ApiOperation("删除購物車中的商品")
    @ApiOperationSupport(order = 200)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "goodsId", value = "商品ID", required = true, paramType = "query", dataType = "long")
    })
    public JsonResult delete(@AuthenticationPrincipal @ApiIgnore CurrentPrincipal currentPrincipal,
                             @Range(min = 1, message = "請提交合法的商品ID值！") Long goodsId) {
        log.debug("開始處理【删除購物車中的商品】的請求，當事人：{}，商品：{}", currentPrincipal, goodsId);
        cartService.delete(currentPrincipal, goodsId);
        return JsonResult.ok();
    }

    @PostMapping("/goods-num/increase")
    @ApiOperation("將購物車中商品的數量增加1")
    @ApiOperationSupport(order = 310)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "goodsId", value = "商品ID", required = true, paramType = "query", dataType = "long")
    })
    public JsonResult increaseNum(@AuthenticationPrincipal @ApiIgnore CurrentPrincipal currentPrincipal,
                                  @Range(min = 1, message = "請提交合法的商品ID值！") Long goodsId) {
        log.debug("開始處理【將購物車中商品的數量增加1】的請求，當事人：{}，商品：{}", currentPrincipal, goodsId);
        Integer newNum = cartService.increaseNum(currentPrincipal, goodsId);
        return JsonResult.ok(newNum);
    }

    @PostMapping("/goods-num/increase-num")
    @ApiOperation("增加購物車中商品的數量")
    @ApiOperationSupport(order = 311)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "goodsId", value = "商品ID", required = true, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "num", value = "商品數量", required = true, paramType = "query", dataType = "int")
    })
    public JsonResult increaseNum(@AuthenticationPrincipal @ApiIgnore CurrentPrincipal currentPrincipal,
                                  @Range(min = 1, message = "請提交合法的商品ID值！") Long goodsId,
                                  @Range(min = 1, max = 100, message = "商品數量必須是1~100之間！") Integer num) {
        log.debug("開始處理【增加購物車中商品的數量】的請求，當事人：{}，商品：{}，數量：{}", currentPrincipal, goodsId, num);
        Integer newNum = cartService.increaseNum(currentPrincipal, goodsId, num);
        return JsonResult.ok(newNum);
    }

    @PostMapping("/goods-num/reduce")
    @ApiOperation("將購物車中商品的數量減少1")
    @ApiOperationSupport(order = 320)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "goodsId", value = "商品ID", required = true, paramType = "query", dataType = "long")
    })
    public JsonResult reduceNum(@AuthenticationPrincipal @ApiIgnore CurrentPrincipal currentPrincipal,
                                @Range(min = 1, message = "請提交合法的商品ID值！") Long goodsId) {
        log.debug("開始處理【將購物車中商品的數量減少1】的請求，當事人：{}，商品：{}", currentPrincipal, goodsId);
        Integer newNum = cartService.reduceNum(currentPrincipal, goodsId);
        return JsonResult.ok(newNum);
    }

    @PostMapping("/goods-num/reduce-num")
    @ApiOperation("減少購物車中商品的數量")
    @ApiOperationSupport(order = 321)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "goodsId", value = "商品ID", required = true, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "num", value = "商品數量", required = true, paramType = "query", dataType = "int")
    })
    public JsonResult reduceNum(@AuthenticationPrincipal @ApiIgnore CurrentPrincipal currentPrincipal,
                                @Range(min = 1, message = "請提交合法的商品ID值！") Long goodsId,
                                @Range(min = 1, max = 100, message = "商品數量必須是1~100之間！") Integer num) {
        log.debug("開始處理【減少購物車中商品的數量】的請求，當事人：{}，商品：{}，數量：{}", currentPrincipal, goodsId, num);
        Integer newNum = cartService.reduceNum(currentPrincipal, goodsId, num);
        return JsonResult.ok(newNum);
    }

    @GetMapping("")
    @ApiOperation("查詢購物車列表")
    @ApiOperationSupport(order = 400)
    public JsonResult list(@AuthenticationPrincipal @ApiIgnore CurrentPrincipal currentPrincipal) {
        log.debug("開始處理【查詢購物車列表】的請求，當事人：{}", currentPrincipal);
        List<CartListItemVO> list = cartService.list(currentPrincipal);
        return JsonResult.ok(list);
    }
}
