package cn.tedu.meimall.front.mall.config;

import cn.tedu.meimall.common.enumerator.ServiceCode;
import cn.tedu.meimall.common.web.JsonResult;
import cn.tedu.meimall.front.mall.filter.JwtAuthorizationFilter;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.PrintWriter;

/**
 * Spring Security的配置
 */
@Slf4j
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthorizationFilter jwtAuthorizationFilter;

    public SecurityConfiguration(){
        log.debug("創建配置類對象: SecurityConfiguration");
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 配置Security框架不使用Session
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // 將自定義的解析JWT的過濾器添加到Security框架的過濾器鏈中
        http.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);

        // 允許跨域訪問
        http.cors();

        // 處理“無認證信息確訪問需要認證的資源時”的響應
        http.exceptionHandling().authenticationEntryPoint((request, response, e) -> {
            log.warn("Spring Security捕獲到異常，由AuthenticationEntryPoint進行處理：", e);
            response.setContentType("application/json; charset=utf-8");
            String message = "操作失敗，您當前未登入！";
            JsonResult jsonResult = JsonResult.fail(ServiceCode.ERROR_UNAUTHORIZED, message);
            PrintWriter writer = response.getWriter();
            writer.println(JSON.toJSONString(jsonResult));
            writer.close();
        });

        // 白名單
        String[] urls = {
                "/favicon.ico",
                "/doc.html",
                "/**/*.css",
                "/**/*.js",
                "/swagger-resources",
                "/v2/api-docs",
                "/categories/**", // 類别的所有
                "/goods/**", // 商品的所有
                "/comments/list-by-goods", // 評論列表
        };

        // 禁用“防止偽造的跨域攻擊的防禦機制”
        http.csrf().disable();

        // 配置請求授權
        http.authorizeRequests()
                .mvcMatchers(urls) // 匹配某些請求
                .permitAll() // 許可，即不需要通過認證就可以訪問
                .anyRequest() // 任何請求，從執行效果来看，也可以視為：除了以上配置過的以外的其它請求
                .authenticated(); // 需要通過認證才可以訪問
    }

}

