package cn.tedu.meimall.admin.mall.controller;

import cn.tedu.meimall.admin.mall.pojo.param.CategoryUpdateInfoParam;
import cn.tedu.meimall.admin.mall.pojo.param.GoodsAddNewParam;
import cn.tedu.meimall.admin.mall.pojo.param.GoodsUpdateInfoParam;
import cn.tedu.meimall.admin.mall.pojo.vo.GoodsListItemVO;
import cn.tedu.meimall.admin.mall.pojo.vo.GoodsStandardVO;
import cn.tedu.meimall.admin.mall.service.ICategoryService;
import cn.tedu.meimall.admin.mall.service.IGoodsService;
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
import javax.validation.constraints.NotBlank;

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

    public GoodsController() {
        log.debug("創建控制器類對象: GoodsController");
    }

    @PostMapping("/add-new")
    @PreAuthorize("hasAuthority('/mall/goods/simple')")
    @ApiOperation("添加商品")
    @ApiOperationSupport(order = 100)
    public JsonResult addNew(@AuthenticationPrincipal @ApiIgnore CurrentPrincipal currentPrincipal,
                             @ApiIgnore HttpServletRequest request,
                             @Valid GoodsAddNewParam goodsAddNewParam) {
        log.debug("開始處理【添加商品】的請求,參數:{}", goodsAddNewParam);
        String remoteAddr = request.getRemoteAddr();
        goodsService.addNew(currentPrincipal, remoteAddr, goodsAddNewParam);
        return JsonResult.ok();
    }

    @PostMapping("/{id:[0-9]+}/delete")
    @PreAuthorize("hasAuthority('/mall/goods/delete')")
    @ApiOperation("根據ID刪除商品")
    @ApiOperationSupport(order = 200)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "商品ID", required = true, dataType = "long")
    })
    public JsonResult delete(@PathVariable @Range(min = 1, message = "請提交有效的商品ID值!") Long id) {
        log.debug("開始處理【根據ID刪除商品】的請求,參數:{}", id);
        goodsService.delete(id);
        return JsonResult.ok();
    }

    @PostMapping("/{id:[0-9]+}/check-state/pass")
    @PreAuthorize("hasAuthority('/mall/goods/check')")
    @ApiOperation("審核通過商品")
    @ApiOperationSupport(order = 310)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "商品ID", required = true, dataType = "long"),
            @ApiImplicitParam(name = "remarks", value = "備註信息", required = true, paramType = "query", dataType = "String")
    })
    public JsonResult passCheck(@AuthenticationPrincipal @ApiIgnore CurrentPrincipal currentPrincipal,
                                @PathVariable @Range(min = 1, message = "請提交有效的商品ID值!") Long id,
                                @NotBlank(message = "請提交審核備註信息") String remarks) {
        log.debug("開始處理【審核通過商品】的請求,參數:{}", id);
        goodsService.passCheck(currentPrincipal, id, remarks);
        return JsonResult.ok();
    }

    @PostMapping("/{id:[0-9]+}/check-state/reject")
    @PreAuthorize("hasAuthority('/mall/goods/check')")
    @ApiOperation("拒絕審核商品")
    @ApiOperationSupport(order = 311)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "商品ID", required = true, dataType = "long"),
            @ApiImplicitParam(name = "remarks", value = "備註信息", required = true, paramType = "query", dataType = "String")
    })
    public JsonResult rejectCheck(@AuthenticationPrincipal @ApiIgnore CurrentPrincipal currentPrincipal,
                                  @PathVariable @Range(min = 1, message = "請提交有效的商品ID值!") Long id,
                                  @NotBlank(message = "請提交審核備註信息") String remarks) {
        log.debug("開始處理【審核通過商品】的請求,參數:{}", id);
        goodsService.rejectCheck(currentPrincipal, id, remarks);
        return JsonResult.ok();
    }

    @PostMapping("/{id:[0-9]+}/put-on")
    @PreAuthorize("hasAuthority('/mall/goods/simple')")
    @ApiOperation("上架商品")
    @ApiOperationSupport(order = 310)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "商品ID", required = true, dataType = "long"),
    })
    public JsonResult putOn(@PathVariable @Range(min = 1, message = "請提交有效的商品ID值!") Long id) {
        log.debug("開始處理【上架商品】的請求,參數:{}", id);
        goodsService.putOn(id);
        return JsonResult.ok();
    }

    @PostMapping("/{id:[0-9]+}/pull-off")
    @PreAuthorize("hasAuthority('/mall/goods/simple')")
    @ApiOperation("下架商品")
    @ApiOperationSupport(order = 321)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "商品ID", required = true, dataType = "long"),
    })
    public JsonResult pullOff(@PathVariable @Range(min = 1, message = "請提交有效的商品ID值!") Long id) {
        log.debug("開始處理【上架商品】的請求,參數:{}", id);
        goodsService.pullOff(id);
        return JsonResult.ok();
    }

    @PostMapping("/{id:[0-9]+}/set-recommend")
    @PreAuthorize("hasAuthority('/mall/goods/simple')")
    @ApiOperation("推薦商品")
    @ApiOperationSupport(order = 330)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "商品ID", required = true, dataType = "long"),
    })
    public JsonResult setRecommend(@PathVariable @Range(min = 1, message = "請提交有效的商品ID值!") Long id) {
        log.debug("開始處理【推薦商品】的請求,參數:{}", id);
        goodsService.setRecommend(id);
        return JsonResult.ok();
    }

    @PostMapping("/{id:[0-9]+}/cancle-recommend")
    @PreAuthorize("hasAuthority('/mall/goods/simple')")
    @ApiOperation("取消推薦商品")
    @ApiOperationSupport(order = 331)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "商品ID", required = true, dataType = "long"),
    })
    public JsonResult cancelRecommend(@PathVariable @Range(min = 1, message = "請提交有效的商品ID值!") Long id) {
        log.debug("開始處理【取消推薦商品】的請求,參數:{}", id);
        goodsService.cancelRecommend(id);
        return JsonResult.ok();
    }

    @PostMapping("/{id:[0-9]+}/update")
    @PreAuthorize("hasAuthority('/mall/goods/simple')")
    @ApiOperation("修改商品詳情")
    @ApiOperationSupport(order = 300)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "類別ID", required = true, dataType = "long")
    })
    public JsonResult updateInfoById(@PathVariable @Range(min= 1, message = "請提交有效的ID值!")Long id,
                                     @Valid GoodsUpdateInfoParam goodsUpdateInfoParam){
        log.debug("開始處理【修改商品詳情】的請求,ID:{},新資料:{}", id, goodsUpdateInfoParam);
        goodsService.updateInfoById(id, goodsUpdateInfoParam);
        return JsonResult.ok();
    }


    @GetMapping("/{id:[0-9]+}")
    @PreAuthorize("hasAuthority('/mall/goods/simple')")
    @ApiOperation("根據ID查詢商品")
    @ApiOperationSupport(order = 410)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "商品ID", required = true, dataType = "long"),
    })
    public JsonResult getStandardById(@PathVariable @Range(min = 1, message = "請提交有效的商品ID值!") Long id) {
        log.debug("開始處理【根據ID查詢商品】的請求,參數:{}", id);
        GoodsStandardVO queryResult = goodsService.getStandardById(id);
        return JsonResult.ok(queryResult);
    }

    @GetMapping("")
    @PreAuthorize("hasAuthority('/mall/goods/simple')")
    @ApiOperation("查詢商品列表")
    @ApiOperationSupport(order = 420)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "頁碼", defaultValue = "1", paramType = "query", dataType = "int"),
    })
    public JsonResult list(@Range(min = 1, message = "請提交有效的商品ID值!") Integer page) {
        log.debug("開始處理【查詢商品列表】的請求,參數:{}", page);
        Integer pageNum = page == null ? 1 : page;
        PageData<GoodsListItemVO> pageData = goodsService.list(pageNum);
        return JsonResult.ok(pageData);
    }


    @GetMapping("/list-by-category")
    @PreAuthorize("hasAuthority('/mall/goods/simple')")
    @ApiOperation("跟據類別查詢商品列表")
    @ApiOperationSupport(order = 421)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "categoryId", value = "商品類別ID", required = true, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "page", value = "頁碼", defaultValue = "1", paramType = "query", dataType = "int")
    })
    public JsonResult listByCategory(@Range(message = "請提交有效的商品類別ID值!")Long categoryId,
                                     @Range(min = 1, message = "請提交有效的商品ID值!") Integer page) {
        log.debug("開始處理【跟據類別查詢商品列表】的請求,父級商品:{}, 頁碼:{}", categoryId, page);
        Integer pageNum = page == null ? 1 : page;
        PageData<GoodsListItemVO> pageData = goodsService.listByCategory(categoryId, pageNum);
        return JsonResult.ok(pageData);
    }

    @PostMapping("/rebuild-search")
    @PreAuthorize("hasAuthority('/mall/goods/rebuild-search')")
    @ApiOperation("重建商品的搜索資料")
    @ApiOperationSupport(order = 500)
    public JsonResult rebuildSearch() {
        log.debug("開始處理【重建商品的搜索資料】的請求");
        goodsService.rebuildSearch();
        return JsonResult.ok();
    }
}



