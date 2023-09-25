package top.cbaymax.matrix.silk.dashboard.infrastructure.log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import top.cbaymax.matrix.silk.dashboard.facade.model.base.Result;

@Aspect
@Component
public class LogAspect {

    @Pointcut("@annotation(top.cbaymax.matrix.silk.dashboard.infrastructure.log.SilkLog)")
    public void logMethods() {
    }


    @Around("logMethods()")
    public Object doLog(ProceedingJoinPoint point) throws Throwable {
        LogContext logContext = new LogContext(point);
        try {
            Object response = point.proceed();
            logContext.response = response;
            if (response instanceof Result<?> formatResponse) {
                logContext.success = formatResponse.success;
            }
            return response;
        } catch (Throwable t) {
            logContext.success = false;
            logContext.error = t;
            throw t;
        } finally {
            logContext.log();
        }
    }
}
