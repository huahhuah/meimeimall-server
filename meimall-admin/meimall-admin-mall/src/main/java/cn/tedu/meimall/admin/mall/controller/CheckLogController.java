package cn.tedu.meimall.admin.mall.controller;

import cn.tedu.meimall.admin.mall.pojo.vo.CheckLogListItemVO;
import cn.tedu.meimall.admin.mall.service.ICheckLogService;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 處理審核日誌相關請求的控制器類
 */
@Slf4j
@RestController
@RequestMapping("/check-logs")
@Validated
@Api(tags = "4. 審核日誌管理")
public class CheckLogController {

    @Autowired
    private ICheckLogService checkLogService;

    public CheckLogController(){
        log.debug("創建控制器類對象:  CheckLogController");
    }

    @GetMapping("/goods")
    @PreAuthorize("hasAuthority('/mall/goods/check')")
    @ApiOperation("查詢商品審核日誌列表")
    @ApiOperationSupport(order = 420)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "頁碼" , defaultValue = "1", paramType = "query", dataType = "int")
    })
    public JsonResult listGoodsCheckLog(@Range(min = 1, message = "請提交有效的頁碼值!")Integer page){
        log.debug("開始處理【查詢商品審核日誌列表】的請求,頁碼:{}", page);
        Integer pageNum = page == null ? 1 : page;
        PageData<CheckLogListItemVO> pageData = checkLogService.listGoodsCheckLog(pageNum);
        return JsonResult.ok(pageData);
    }

    @GetMapping("/comment")
    @PreAuthorize("hasAuthority('/mall/goods/check')")
    @ApiOperation("查詢商品審核日誌列表")
    @ApiOperationSupport(order = 421)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "頁碼" , defaultValue = "1", paramType = "query", dataType = "int")
    })
    public JsonResult list(@Range(min = 1, message = "請提交有效的頁碼值!")Integer page){
        log.debug("開始處理【查詢商品審核日誌列表】的請求,頁碼:{}", page);
        Integer pageNum = page == null ? 1 : page;
        PageData<CheckLogListItemVO> pageData = checkLogService.listCommentCheckLog(pageNum);
        return JsonResult.ok(pageData);
    }
}
