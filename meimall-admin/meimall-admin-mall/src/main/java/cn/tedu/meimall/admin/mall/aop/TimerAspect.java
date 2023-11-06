package cn.tedu.meimall.admin.mall.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 統計各Service方法的執行耗時的切面類
 *
 */
@Slf4j
@Aspect // 標記此類是一個切面類，Spring AOP會將此類編織到匹配的連接點的執行過程中
@Component // 標記此類是一個組件類
public class TimerAspect {

    // 連接點（JoinPoint）：程序執行過程中的某個節點，可能是方法的調用，或抛出異常
    // 切入點（Point Cut）：是匹配1個或多個連接點的表達式
    // 通知（Advice）：包含了切入點表達式與執行的方法
    // -------------------------------------------------------------
    // Advice的種類（使用不同Advice時，方法的參數列表將不同）：
    // -- @Before：前置，你的程式碼將在連接點【之前】執行
    // -- @After：後置，你的程式碼將在連接點【之後】執行
    // -- @AfterReturning：返回之後，你的程式碼將在連接點【成功返回之後】執行
    // -- @AfterThrowing：抛出異常之後，你的程式碼將在連接點【抛出異常之後】執行
    // -- @Around：環繞，你的程式碼可以在連接點【之前和之後】執行
    // -------------------------------------------------------------
    // 各Advice的執行節點大致是：
    // @Around：開始
    // try {
    //      @Before
    //      連接點
    //      @AfterReturning
    // } catch (Throwable e) {
    //      @AfterThrowing
    // } finally {
    //      @After
    // }
    // @Around：結束
    // -------------------------------------------------------------
    // 關於切入點表達式：
    // -- 完整的切入點表達式格式為：[修飾符] 返回值類型 [包名]類名.方法名(參數列表) [異常]
    // -- 可以使用1個星號作為通配符，用於匹配任意内容，只會且必須匹配1次
    // -- 可以使用2個連接的小數點作為通配符，可以匹配若干次（0~n次），只能用於包名和參數列表
    //                 ↓ 返回值類型
    //                   ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ 包名
    //                                                    ↓ 類型名
    //                                                      ↓ 方法名
    @Around("execution(* cn.tedu.meimall.admin.mall.service.*.*(..))")
    //     ↓↓↓↓↓↓ 方法的返回值類型必須是Object【適用於@Around】
    //            ↓↓↓↓↓ 切面方法的名稱可以完全自定義
    //                  ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ 處理連接點的對象，方法參數必須是此類型【適用於@Around】
    public Object timer(ProceedingJoinPoint pjp) throws Throwable {
        // 獲取連接點的相關信息
        String className = pjp.getTarget().getClass().getName(); // 獲取當前連接點所歸屬的對象
        String methodName = pjp.getSignature().getName(); // 獲取當前連接點方法的名稱
        Object[] args = pjp.getArgs(); // 獲取當前連接點方法執行的參數列表
        System.out.println("className = " + className);
        System.out.println("methodName = " + methodName);
        System.out.println("args = " + Arrays.toString(args));

        long start = System.currentTimeMillis();

        // 執行連接點
        // 注意-1：調用連接點時，必須獲取返回值，且作為當前切面的返回值
        // 注意-2：調用連接點時，必須將異常抛出（或者，使用try...catch處理時，在catch程式碼再次抛出異常）
        Object result = pjp.proceed();

        long end = System.currentTimeMillis();
        System.out.println("執行耗時：" + (end - start));
        return result;
    }

}
