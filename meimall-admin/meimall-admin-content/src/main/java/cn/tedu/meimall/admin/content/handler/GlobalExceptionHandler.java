package cn.tedu.meimall.admin.content.handler;

import cn.tedu.meimall.common.enumerator.ServiceCode;
import cn.tedu.meimall.common.ex.ServiceException;
import cn.tedu.meimall.common.web.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
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
        log.info("創建全局異常處理器對象: GlobalExceptionHandler");
    }

    @ExceptionHandler
    public JsonResult handleServiceException(ServiceException e){
        log.debug("全局異常處理器開始執行ServeException");
        return JsonResult.fail(e);
    }

    @ExceptionHandler
    public JsonResult handleBindException(BindException e){
        log.debug("全局異常處理器開始處理BindException");
//        StringJoiner stringJoiner = new StringJoiner("，", "參數錯誤，", "!");
//        List<FieldError> fieldErrors = e.getFieldErrors();
//        for (FieldError fieldError : fieldErrors) {
//            String defaultMessage = fieldError.getDefaultMessage();
//            stringJoiner.add(defaultMessage);
//        }
//        String message = stringJoiner.toString();
        FieldError fieldError = e.getFieldError();
        String message = fieldError.getDefaultMessage();
        return JsonResult.fail(ServiceCode.ERROR_BAD_REQUEST, message);
    }

    @ExceptionHandler
    public JsonResult handleConstraintViolationException(ConstraintViolationException e){
        log.debug("全局異常處理器開始處理ConstraintViolationException");
        String message = null;
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        for (ConstraintViolation<?> constraintViolation : constraintViolations) {
            message = constraintViolation.getMessage();
        }
        return JsonResult.fail(ServiceCode.ERROR_BAD_REQUEST, message);
    }

    public JsonResult handleThrowable(Throwable e){
        log.debug("全局異常處理器開始處理Throwable");
        log.debug("異常跟蹤信息如下:", e);
        String message = "服務器忙,請稍後再嘗試!【看到這句時,你應該檢查服務器端的控制台,並在全局異常處理器中添加處理異常的方法】";
        return JsonResult.fail(ServiceCode.ERROR_UNKNOWN, message);
    }
}
