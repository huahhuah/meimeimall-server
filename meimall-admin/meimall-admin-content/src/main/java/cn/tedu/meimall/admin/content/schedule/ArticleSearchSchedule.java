package cn.tedu.meimall.admin.content.schedule;

import cn.tedu.meimall.admin.content.service.IArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定時更新ES中資料的計畫任務
 */
@Slf4j
@Component
public class ArticleSearchSchedule {

    @Autowired
    private IArticleService articleService;

    // @Scheduled(fixedRate = 10 * 1000)
    @Scheduled(cron = "0 0 4 ? * MON")
    public void rebuildSearch(){
        log.debug("更新elasticsearch中的文章列表資料");
        articleService.rebuildSearch();
    }


}
