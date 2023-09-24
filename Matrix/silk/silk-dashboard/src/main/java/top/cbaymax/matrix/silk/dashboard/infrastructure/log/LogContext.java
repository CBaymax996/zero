package top.cbaymax.matrix.silk.dashboard.infrastructure.log;

import lombok.Data;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;
import java.util.*;

@Data
public class LogContext {

    private final Set<LogInfo> logInfos;


    private final Class<?> targetClass;

    private final Method targetMethod;

    private final long start;

    private final Object[] request;

    private Object response;

    private boolean success = true;

    private Throwable error;

    public LogContext(ProceedingJoinPoint pjp) {

        this.request = pjp.getArgs();
        this.targetClass = pjp.getTarget().getClass();
        this.targetMethod = ((MethodSignature) pjp.getSignature()).getMethod();

        this.start = System.currentTimeMillis();
        this.logInfos = getLogInfosFromAnnotation();
    }

    public void log() {
        // todo log to file
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "LogContext{" +
                "printContents=" + logInfos +
                ", rt=" + calculateRT() +
                ", targetClass=" + targetClass +
                ", targetMethod=" + targetMethod +
                ", request=" + Arrays.toString(request) +
                ", response=" + response +
                ", success=" + success +
                ", error=" + error +
                '}';
    }

    private Long calculateRT() {
        return System.currentTimeMillis() - this.start;
    }

    private Set<LogInfo> getLogInfosFromAnnotation() {
        SilkLog annotation = Optional.ofNullable(this.targetMethod)
                .map(f -> f.getAnnotation(SilkLog.class))
                .orElse(null);

        if (annotation == null) {
            return Collections.emptySet();
        }

        Set<LogInfo> excludes = Optional.ofNullable(annotation.excludes())
                .map(Set::of)
                .orElse(Collections.emptySet());

        return Optional.ofNullable(annotation.includes())
                .map(elements -> {
                    Set<LogInfo> set = new HashSet<>();
                    LogInfo[] logInfos = elements.length > 0 ? elements : LogInfo.values();
                    Collections.addAll(set, logInfos);
                    return set;
                })
                .filter(s -> s.removeAll(excludes))
                .orElse(Collections.emptySet());
    }
}