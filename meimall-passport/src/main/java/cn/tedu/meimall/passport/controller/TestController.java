package cn.tedu.meimall.passport.controller;


import cn.tedu.meimall.common.pojo.authentication.CurrentPrincipal;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@Deprecated
@RestController
@RequestMapping("/test")
@Api(tags = "9. 測試訪問")
public class TestController {

    @GetMapping("/simple-get")
    @ApiOperation("【無需登入】簡單的GET請求")
    @ApiOperationSupport(order = 100)
    public String simpleGet() {
        return "服務器端響應了簡單的GET請求！";
    }

    @PostMapping("/simple-post")
    @ApiOperation("【無需登入】簡單的POST請求")
    @ApiOperationSupport(order = 101)
    public String simplePost() {
        return "服務器端響應了簡單的POST請求！";
    }

    @GetMapping("/authenticated-get")
    @ApiOperation("【需要登入】簡單的GET請求")
    @ApiOperationSupport(order = 200)
    public String authenticatedGet(@AuthenticationPrincipal @ApiIgnore CurrentPrincipal principal) {
        return "【需要登入】服務器端響應了簡單的GET請求！當事人：" + principal;
    }

    @GetMapping("/authorized-get")
    @PreAuthorize("hasAuthority('/admin')")
    @ApiOperation("【需要權限】需要權限的GET請求")
    @ApiOperationSupport(order = 300)
    public String authorizedGet() {
        return "【需要權限】服務器端響應了需要權限的GET請求！";
    }

}
