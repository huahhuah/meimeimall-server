package cn.tedu.meimall.passport.config;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis的配置類
 *
 */
@Slf4j
@Configuration
@MapperScan({
        "cn.tedu.meimall.passport.dao.persist.mapper",
})
public class MyBatisConfiguration {

    public MyBatisConfiguration() {
        log.info("創建配置類對象：MyBatisConfiguration");
    }

}
