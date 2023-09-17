package top.cbaymax.matrix.silk.dashboard.facade.controller.config.wrapper;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import top.cbaymax.matrix.silk.dashboard.facade.domain.base.Result;
import top.cbaymax.matrix.silk.dashboard.infrastructure.error.CommonError;
import top.cbaymax.matrix.silk.dashboard.infrastructure.error.SilkError;


@ControllerAdvice
public class ExceptionWrapper {
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Result<?> exceptionHandler(Exception e) {
        if (e instanceof SilkError) {
            return Result.buildError((SilkError) e);
        }
        return Result.buildError(CommonError.unknown_internal_exception);
    }
}
