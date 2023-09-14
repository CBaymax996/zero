//package top.cbaymax.matrix.silk.dashboard.aspect.log;
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Component;
//import top.cbaymax.matrix.silk.dashboard.common.result.Result;
//import top.cbaymax.matrix.silk.dashboard.entity.common.SilkError;
//import top.cbaymax.matrix.silk.dashboard.entity.common.SystemError;
//
//@Aspect
//@Component
//public class LogAspect {
//
//    @Pointcut("@within(org.springframework.stereotype.Controller)||@within(org.springframework.web.bind.annotation.RestController)")
//    public void allControllerMethod() {
//    }
//
//
//    @Around("allControllerMethod()")
//    public Object afterReturning(ProceedingJoinPoint point) {
//
//        try {
//            System.out.println("aop");
//            //
//            Object result = point.proceed();
//            if (result instanceof Result<?> || result instanceof ResponseEntity<?>||result instanceof String) {
//                return result;
//            }
//            Result<Object> res = new Result<>();
//            res.setData(result);
//            res.setSuccess(true);
//            return res;
//
//        } catch (SilkError e) {
//            return Result.buildError(e);
//        } catch (Throwable e) {
//            return Result.buildError(SystemError.unknown_internal_exception);
//        }
//
//    }
//}
