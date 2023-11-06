package cn.tedu.meimall.passport.controller;


import cn.tedu.meimall.passport.pojo.param.UserLoginInfoParam;
import cn.tedu.meimall.passport.pojo.vo.UserLoginResultVO;
import cn.tedu.meimall.passport.service.IUserService;
import cn.tedu.meimall.common.consts.web.HttpConsts;
import cn.tedu.meimall.common.pojo.authentication.CurrentPrincipal;
import cn.tedu.meimall.common.web.JsonResult;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;

/**
 * 處理用戶相關請求的控制器類
 */
@Slf4j
@RestController
@RequestMapping("/passport")
@Validated
@Api(tags = "1. 單點登入")
public class UserController implements HttpConsts {

    @Autowired
    private IUserService userService;

    public UserController() {
        log.info("創建控制器對象：UserController");
    }

    @PostMapping("/login")
    @ApiOperation("用戶登入")
    @ApiOperationSupport(order = 20)
    public JsonResult login(@Validated UserLoginInfoParam userLoginInfoParam,
                            @ApiIgnore HttpServletRequest request) {
        log.debug("開始處理【用戶登入】的請求，參數：{}", userLoginInfoParam);
        String remoteAddr = request.getRemoteAddr();
        String userAgent = request.getHeader(HEADER_USER_AGENT);
        UserLoginResultVO userLoginResultVO = userService.login(userLoginInfoParam, remoteAddr, userAgent);
        return JsonResult.ok(userLoginResultVO);
    }

    @PostMapping("/logout")
    @ApiOperation("退出登入")
    @ApiOperationSupport(order = 90)
    public JsonResult logout(@AuthenticationPrincipal @ApiIgnore CurrentPrincipal currentPrincipal) {
        log.debug("開始處理【退出登入】的請求，無參數");
        userService.logout(currentPrincipal);
        return JsonResult.ok();
    }

}
