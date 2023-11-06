package cn.tedu.meimall.attachment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;



@SpringBootApplication(exclude = DataSourceAutoConfiguration.class) //排除掉數據庫自動加載
public class MeimallAttachmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(MeimallAttachmentApplication.class, args);
    }

}
