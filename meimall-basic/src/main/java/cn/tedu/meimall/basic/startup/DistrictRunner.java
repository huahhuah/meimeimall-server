package cn.tedu.meimall.basic.startup;

import cn.tedu.meimall.basic.service.IDistrictService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * 縣市區資料的緩存預熱類
 */
@Slf4j
@Component
public class DistrictRunner implements ApplicationRunner {

    @Autowired
    private IDistrictService districtService;

    public DistrictRunner(){
        log.debug("創建ApplicationRunner對象: DistrictRunner");
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.debug("開始處理【縣市區緩存預熱】，無參數");
        districtService.rebuildCache();
    }
}
