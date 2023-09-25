package top.cbaymax.matrix.silk.dashboard.facade.controller.config.wrapper;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import top.cbaymax.matrix.silk.dashboard.facade.model.base.Result;
import top.cbaymax.matrix.silk.dashboard.infrastructure.error.CommonError;
import top.cbaymax.matrix.silk.dashboard.infrastructure.error.SilkError;


@ControllerAdvice
public class ExceptionWrapper {
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Result<?> exceptionHandler(Exception e) {
        if (e instanceof SilkError) {
            return new Result<>((SilkError) e);
        }
        return new Result<>(new SilkError(CommonError.unknown_internal_exception));
    }
}
