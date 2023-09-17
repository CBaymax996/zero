package top.cbaymax.matrix.silk.dashboard.infrastructure.log;

import lombok.Data;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Stream;

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
            logContext.setResponse(response);
            return response;
        } catch (Throwable t) {
            logContext.setSuccess(false);
            logContext.setError(t);
            throw t;
        } finally {
            printLog(logContext);
        }

    }

    private void printLog(LogContext logContext) {
        System.out.println(logContext);
    }


    @Data
    private static class LogContext {

        private List<LogContent> printContents;

        private Long startTime;

        private Class<?> targetClass;

        private Method targetMethod;


        private Object[] request;

        private Object response;

        private boolean success = true;

        private Throwable error;

        public LogContext(ProceedingJoinPoint pjp) throws NoSuchMethodException {
            this.startTime = System.currentTimeMillis();
            this.request = pjp.getArgs();

            this.targetClass = pjp.getTarget().getClass();
            MethodSignature signature = (MethodSignature) pjp.getSignature();
            this.targetMethod = this.targetClass.getDeclaredMethod(signature.getName(), signature.getParameterTypes());
            this.printContents = parseContentByAnnotation();
        }

        // todo
        @Override
        public String toString() {
            return "LogContext{" +
                    "printContents=" + printContents +
                    ", startTime=" + startTime +
                    ", targetClass=" + targetClass +
                    ", targetMethod=" + targetMethod +
                    ", request=" + Arrays.toString(request) +
                    ", response=" + response +
                    ", success=" + success +
                    ", error=" + error +
                    '}';
        }

        private List<LogContent> parseContentByAnnotation() {
            SilkLog annotation = Stream.of(
                            Optional.ofNullable(this.targetMethod)
                                    .map(f -> f.getAnnotation(SilkLog.class))
                                    .orElse(null),
                            Optional.ofNullable(this.targetClass)
                                    .map(c -> c.getAnnotation(SilkLog.class))
                                    .orElse(null))
                    .filter(Objects::nonNull)
                    .findFirst().orElse(null);
            if (annotation == null) {
                return Collections.emptyList();
            }

            List<LogContent> excludes = Optional.ofNullable(annotation.exclude())
                    .filter(l -> l.length != 0)
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList());
            List<LogContent> includes = Optional.ofNullable(annotation.include())
                    .filter(l -> l.length != 0)
                    .map(Arrays::asList)
                    .orElse(Arrays.asList(LogContent.values()));
            includes.removeAll(excludes);
            return includes;
        }

    }
}
