package demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @description 全局异常处理: 使用 @RestControllerAdvice + @ExceptionHandler 注解方式实现全
 * 局异常处理
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * @description 处理所有不可知的异常
     */
    @ExceptionHandler(Exception.class)    //申明捕获那个异常类
    public Result globalExceptionHandler(Exception e) {
        this.logger.error(e.getMessage(), e);
        return Result.isError(ErrorEnum.USER_NOT_EXIST.name(),ErrorEnum.SYSTEM_EXCEPTION.getErrorMsg());
    }

    /**
     * @description 处理所有业务异常
     */
    @ExceptionHandler(BaseBusinessException.class)
    public Result BusinessExceptionHandler(BaseBusinessException e) {
        this.logger.error(e.getMessage(),e);
        return Result.isError(e.getCode(),e.getMessage());
    }

}
