package top.cbaymax.matrix.silk.dashboard.infrastructure.wrapper;

import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import top.cbaymax.matrix.silk.dashboard.facade.domain.base.Result;

import java.lang.reflect.Method;

//@Aspect
//@ControllerAdvice
public class ControllerPackingAspect implements ResponseBodyAdvice {
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        Method method = returnType.getMethod();
        ResponseBody annotation = AnnotationUtils.findAnnotation(method, ResponseBody.class);
        if (annotation != null) {
            return true;
        }
        return false;
    }

    @Override
    public Object beforeBodyWrite(Object body,
                                  MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class selectedConverterType,
                                  ServerHttpRequest request,
                                  ServerHttpResponse response) {
        if (body == null) {
            return null;
        }
        if (body instanceof Result<?>) {
            return body;
        }
        if (body instanceof String) {

            Result<String> objectResult = Result.buildSuccess((String)body);
            return body;

        }
        return Result.buildSuccess(body);
    }

}
