package top.cbaymax.matrix.silk.dashboard.facade.controller.config.wrapper;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.Errors;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import top.cbaymax.matrix.silk.dashboard.facade.model.base.Result;
import top.cbaymax.matrix.silk.dashboard.infrastructure.error.CommonError;
import top.cbaymax.matrix.silk.dashboard.infrastructure.error.SilkError;

import java.util.Optional;


@ControllerAdvice
public class ExceptionWrapper {
    @ResponseBody
    @ExceptionHandler(Throwable.class)
    public Result<?> exceptionHandler(Throwable e) {

        // spring validate
        if (e instanceof MethodArgumentNotValidException) {
            SilkError silkError = Optional.of(((MethodArgumentNotValidException) e).getBindingResult())
                    .map(Errors::getFieldError)
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .map(customMessage ->
                            SilkError.builder(CommonError.invalid_param)
                                    .message(customMessage)
                                    .error(e)
                                    .build())
                    .orElse(new SilkError(CommonError.invalid_param));
            return new Result<>(silkError);
        }
        if (e instanceof MissingServletRequestParameterException) {
            return new Result<>(new SilkError(CommonError.invalid_param));
        }
        // custom error
        if (e instanceof SilkError) {
            return new Result<>((SilkError) e);
        }
        // unknown error
        return new Result<>(SilkError.builder(CommonError.unknown_internal_exception).error(e).build());
    }
}
