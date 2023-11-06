package cn.tedu.meimall.admin.content.config;

import com.github.xiaoymin.knife4j.spring.extension.OpenApiExtensionResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * knife4j配置類
 */
@Slf4j
@Configuration
@EnableSwagger2WebMvc
public class Knife4jConfiguration {
    /**
     * 【重要】指定Controller包路徑
     */
    private static final String BASE_PACKAGE = "cn.tedu.meimall";
    /**
     * 組名
     */
    private static final String GROUP_NAME = "廚房一隻咩";
    /**
     * 主機名
     */
    private static final String HOST = "http://java.tedu.cn";
    /**
     * 標題
     */
    private static final String TITLE = "廚房一隻咩-資訊管理後臺服務-在線API文檔";
    /**
     * 簡介
     */
    private static final String DESCRIPTION = "廚房一隻咩-資訊管理後臺服務-在線API文檔";
    /**
     * 服務條款URL
     */
    private static final String TERMS_OF_SERVICE_URL = "http://www.apache.org/licenses/LICENSE-2.0";
    /**
     * 聯繫人
     */
    private static final String CONTACT_NAME = "Java教學研發部";
    /**
     * 聯繫網址
     */
    private static final String CONTACT_URL = "http://java.tedu.cn";
    /**
     * 聯繫信箱
     */
    private static final String CONTACT_EMAIL = "java@tedu.cn";
    /**
     * 版本號
     */
    private static final String VERSION = "2.0";

    @Autowired
    private OpenApiExtensionResolver openApiExtensionResolver;

    public Knife4jConfiguration() {
        log.debug("創建配置類對象：Knife4jConfiguration");
    }

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .host(HOST)
                .apiInfo(apiInfo())
                .groupName(GROUP_NAME)
                .select()
                .apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE))
                .paths(PathSelectors.any())
                .build()
                .extensions(openApiExtensionResolver.buildExtensions(GROUP_NAME));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(TITLE)
                .description(DESCRIPTION)
                .termsOfServiceUrl(TERMS_OF_SERVICE_URL)
                .contact(new Contact(CONTACT_NAME, CONTACT_URL, CONTACT_EMAIL))
                .version(VERSION)
                .build();
    }
}
