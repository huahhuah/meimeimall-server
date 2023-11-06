package cn.tedu.meimall.front.account.controller;

import cn.tedu.meimall.common.consts.web.HttpConsts;
import cn.tedu.meimall.common.pojo.authentication.CurrentPrincipal;
import cn.tedu.meimall.common.validation.account.UserRules;
import cn.tedu.meimall.common.web.JsonResult;
import cn.tedu.meimall.front.account.pojo.param.UserRegisterParam;
import cn.tedu.meimall.front.account.pojo.param.UserUpdateInfoParam;
import cn.tedu.meimall.front.account.pojo.vo.UserSimpleInfoVO;
import cn.tedu.meimall.front.account.service.IUserService;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * 處理用戶相關請求的控制類
 */
@Slf4j
@RestController
@RequestMapping("/users")
@Validated
@Api(tags = "1.用戶管理")
public class UserController implements HttpConsts {

    @Autowired
    private IUserService userService;

    public UserController(){
        log.info("創建控制器對象: UserController");
    }

    @PostMapping("/register")
    @ApiOperation("用戶註冊")
    @ApiOperationSupport(order = 10)
    public JsonResult login(@Validated UserRegisterParam userLoginInfoParam){
        log.debug("開始處理【用戶註冊】的請求,參數:{}", userLoginInfoParam);
        userService.register(userLoginInfoParam);
        return JsonResult.ok();
    }

    @PostMapping("/info/update")
    @ApiOperation("修改基本資料")
    @ApiOperationSupport(order = 300)
    public JsonResult updateInfo(@ApiIgnore @AuthenticationPrincipal CurrentPrincipal currentPrincipal,
                                 @Valid UserUpdateInfoParam userUpdateInfoParam){
        log.debug("開始處理【修改基本資料】的請求,當事人:{}, 新基本資料:{}", currentPrincipal, userUpdateInfoParam);
        userService.updateInfo(currentPrincipal, userUpdateInfoParam);
        return JsonResult.ok();
    }

    @PostMapping("/password/update")
    @ApiOperation("修改密碼")
    @ApiOperationSupport(order = 310)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "oldPassword", value = "舊密碼",required = true, paramType = "query"),
            @ApiImplicitParam(name = "newPassword", value = "新密碼",required = true,paramType = "query")
    })
    public JsonResult updatePassword(@ApiIgnore @AuthenticationPrincipal CurrentPrincipal currentPrincipal,
                                     @Pattern(regexp = UserRules.PATTERN_PASSWORD, message = UserRules.MESSAGE_EMAIL_PATTERN)String oldPassword,
                                     @Pattern(regexp = UserRules.PATTERN_PASSWORD, message = UserRules.MESSAGE_EMAIL_PATTERN)String newPassword){
        log.debug("開始處理【修改密碼】的請求,當事人:{}, 舊密碼:{}, 新密碼: {}", currentPrincipal, oldPassword, newPassword);
        userService.updatePassword(currentPrincipal, oldPassword, newPassword);
        return JsonResult.ok();
    }

    @PostMapping("/avatar/update")
    @ApiOperation("修改頭像")
    @ApiOperationSupport(order = 320)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "avatar", value = "新頭像的路徑",required = true, paramType = "query")
    })
    public JsonResult updateAvatar(@ApiIgnore @AuthenticationPrincipal CurrentPrincipal currentPrincipal,
                                   @NotBlank(message = "請提交新的頭像路徑") String avatar){
        log.debug("開始處理【修改頭像】的請求,當事人:{}, 新頭像: {}", currentPrincipal, avatar);
        userService.updateAvatar(currentPrincipal, avatar);
        return JsonResult.ok();
    }

    @PostMapping("/phone/update")
    @ApiOperation("修改手機號碼")
    @ApiOperationSupport(order = 330)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "手機號碼",required = true, paramType = "query")
    })
    public JsonResult updatePhone(@ApiIgnore @AuthenticationPrincipal CurrentPrincipal currentPrincipal,
                                   @Pattern(regexp = UserRules.PATTERN_PHONE, message = UserRules.MESSAGE_PHONE_PATTERN) String phone){
        log.debug("開始處理【修改手機號碼】的請求,當事人:{}, 新手機號碼: {}", currentPrincipal, phone);
        userService.updatePhone(currentPrincipal, phone);
        return JsonResult.ok();
    }

    @PostMapping("/email/update")
    @ApiOperation("修改電子信箱")
    @ApiOperationSupport(order = 340)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "email", value = "電子信箱",required = true, paramType = "query")
    })
    public JsonResult updateEmail(@ApiIgnore @AuthenticationPrincipal CurrentPrincipal currentPrincipal,
                                  @Pattern(regexp = UserRules.PATTERN_EMAIL, message = UserRules.MESSAGE_EMAIL_PATTERN) String email){
        log.debug("開始處理【修改電子信箱】的請求,當事人:{}, 新電子信箱: {}", currentPrincipal, email);
        userService.updateEmail(currentPrincipal, email);
        return JsonResult.ok();
    }

    @GetMapping("/info")
    @ApiOperation("查詢當前用戶基本資料")
    @ApiOperationSupport(order = 400)
    public JsonResult getSelfSimpleInfo(@ApiIgnore @AuthenticationPrincipal CurrentPrincipal currentPrincipal){
        log.debug("開始處理【查詢當前用戶基本資料】的請求,當事人:{}", currentPrincipal);
        UserSimpleInfoVO selfSimpleInfo = userService.getSelfSimpleInfo(currentPrincipal);
        return JsonResult.ok(selfSimpleInfo);
    }
}
