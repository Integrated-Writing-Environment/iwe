package top.xianyume.iwe.backend.handler;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import cn.dev33.satoken.util.SaResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public SaResult handleValidationException(MethodArgumentNotValidException ex) {
        // 提取校验失败的字段和错误信息
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));

        // 返回 SaResult 对象
        return SaResult.error("参数校验失败").setData(errors);
    }

    @ExceptionHandler(NotRoleException.class)
    public SaResult handleNotRoleException() {
        return SaResult.error("无角色权限");
    }

    @ExceptionHandler(NotPermissionException.class)
    public SaResult handleNotPermissionException() {
        return SaResult.error("无操作权限");
    }

    @ExceptionHandler(NotLoginException.class)
    public SaResult handleNotNotLoginException() {
        return SaResult.error("token无效！请重新登录");
    }

    @ExceptionHandler(RuntimeException.class)
    public SaResult handleRuntimeException(RuntimeException ex) {
        return SaResult.error(ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public SaResult handleIllegalArgumentException(IllegalArgumentException ex) {
        return SaResult.error(ex.getMessage());
    }

//    @ExceptionHandler(NoHandlerFoundException.class)
//    public SaResult handleNoHandlerFoundException(NoHandlerFoundException ex) {
//        return SaResult.error(ex.getMessage());
//    }
//
//    @ExceptionHandler(ConstraintViolationException.class)
//    public SaResult handleConstraintViolationException(ConstraintViolationException ex) {
//        return SaResult.error(ex.getMessage());
//    }

}
