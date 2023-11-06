package cn.tedu.meimall.passport.controller;


import cn.tedu.meimall.passport.pojo.vo.LoginLogListItemVO;
import cn.tedu.meimall.passport.service.ILoginLogService;
import cn.tedu.meimall.common.pojo.vo.PageData;
import cn.tedu.meimall.common.web.JsonResult;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 處理用戶登入日誌相關請求的控制器類
 *
 */
@Slf4j
@RestController
@RequestMapping("/login-logs")
@Validated
@Api(tags = "2. 登入日誌")
public class LoginLogController {

    @Autowired
    private ILoginLogService loginLogService;

    public LoginLogController() {
        log.debug("創建控制器類對象：LoginLogController");
    }

    @GetMapping("")
    @PreAuthorize("hasAuthority('/account/user/query')")
    @ApiOperation("查詢登入日誌列表")
    @ApiOperationSupport(order = 420)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "頁碼", defaultValue = "1", dataType = "long")
    })
    public JsonResult list(Integer page) {
        log.debug("開始處理【查詢登入日誌列表】的請求，頁碼：{}", page);
        Integer pageNum = page == null ? 1 : page;
        PageData<LoginLogListItemVO> pageData = loginLogService.list(pageNum);
        return JsonResult.ok(pageData);
    }

}
