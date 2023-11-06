package cn.tedu.meimall.basic.handler;

import cn.tedu.meimall.common.enumerator.ServiceCode;
import cn.tedu.meimall.common.ex.ServiceException;
import cn.tedu.meimall.common.web.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * 全局異常處理器
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    public GlobalExceptionHandler(){
        log.debug("創建全局異常處理器對象: GlobalExceptionHandler");
    }

    @ExceptionHandler
    public JsonResult handleServiceException(ServiceException e) {
        log.debug("全局異常處理器開始處理ServiceException");
        return JsonResult.fail(e);
    }

    @ExceptionHandler
    public JsonResult handleBindException(BindException e) {
        log.debug("全局異常處理器開始處理BindException");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("請求參數格式錯誤，");
        stringBuilder.append(e.getFieldError().getDefaultMessage());
        stringBuilder.append("！");
        String message = stringBuilder.toString();
        log.warn(message);
        return JsonResult.fail(ServiceCode.ERROR_BAD_REQUEST, message);
    }

    @ExceptionHandler
    public JsonResult handleConstraintViolationException(ConstraintViolationException e) {
        log.debug("全局異常處理器開始處理ConstraintViolationException");
        StringBuilder stringBuilder = new StringBuilder();
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        for (ConstraintViolation<?> constraintViolation : constraintViolations) {
            stringBuilder.append(constraintViolation.getMessage());
        }
        String message = stringBuilder.toString();
        return JsonResult.fail(ServiceCode.ERROR_BAD_REQUEST, message);
    }

    @ExceptionHandler
    public JsonResult handleThrowable(Throwable e) {
        log.debug("全局異常處理器開始處理Throwable");
        log.debug("異常跟蹤信息如下：", e);
        String message = "服務器忙，請稍後再嘗試！【看到這句時，你應該檢查服務器端的控制台，並在全局異常處理器中添加處理異常的方法】";
        return JsonResult.fail(ServiceCode.ERROR_UNKNOWN, message);
    }

}
