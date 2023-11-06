package cn.tedu.meimall.attachment.handler;

import cn.tedu.meimall.common.enumerator.ServiceCode;
import cn.tedu.meimall.common.ex.ServiceException;
import cn.tedu.meimall.common.web.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局異常處理器
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    public GlobalExceptionHandler(){
        log.info("創建全局異常處理器對象: GlobalExceptionHandler");
    }

    @ExceptionHandler
    public JsonResult handleServiceException(ServiceException e){
        log.debug("全局異常處理器開始處理ServiceException");
        return JsonResult.fail(e);
    }

    @ExceptionHandler
    public JsonResult handleAccessDeniedException(AccessDeniedException e){
        log.debug("全局異常處理開始處理AccessDeniedException");
        String message = "操作失敗,當前登入的帳號無此操作權限!";
        return JsonResult.fail(ServiceCode.ERROR_FORBIDDEN, message);
    }

    @ExceptionHandler
    public JsonResult handleThrowable(Throwable e){
        log.debug("全局異常處理器開始處理Throwable");
        log.debug("異常跟蹤信息如下:", e);
        String message = "服務器忙,請稍後再嘗試!【看到這句時,你應該檢查服務器的控制檯,並在全局異常處理器中添加處理異常的方法】";
        return JsonResult.fail(ServiceCode.ERROR_UNKNOWN, message);
    }
}
