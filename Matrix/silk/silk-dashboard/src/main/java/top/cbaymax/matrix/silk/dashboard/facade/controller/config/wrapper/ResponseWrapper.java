package top.cbaymax.matrix.silk.dashboard.facade.controller.config.wrapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Nonnull;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import top.cbaymax.matrix.silk.dashboard.facade.domain.base.Result;
import top.cbaymax.matrix.silk.dashboard.infrastructure.error.CommonError;
import top.cbaymax.matrix.silk.dashboard.infrastructure.error.SilkError;

import java.util.Optional;


@ControllerAdvice
public class ResponseWrapper implements ResponseBodyAdvice<Object> {
    private final ObjectMapper jsonMapper = new ObjectMapper();

    @Override
    public boolean supports(@Nonnull MethodParameter returnType, @Nonnull Class converterType) {

        Boolean declareOnMethod = Optional.of(returnType)
                .map(MethodParameter::getMethod)
                .map(method -> AnnotationUtils.findAnnotation(method, ResponseBody.class))
                .isPresent();

        Boolean declareOnClass = Optional.of(returnType)
                .map(MethodParameter::getDeclaringClass)
                .map(clz -> AnnotationUtils.findAnnotation(clz, ResponseBody.class))
                .isPresent();

        return declareOnMethod || declareOnClass;
    }

    @Override
    public Object beforeBodyWrite(Object body,
                                  @Nonnull MethodParameter returnType,
                                  @Nonnull MediaType selectedContentType,
                                  @Nonnull Class selectedConverterType,
                                  @Nonnull ServerHttpRequest request,
                                  @Nonnull ServerHttpResponse response) {
        if (body == null) {
            return null;
        }
        if (body instanceof Result<?>) {
            return body;
        }
        if (body instanceof String) {
            Result<String> objectResult = Result.buildSuccess((String) body);
            HttpHeaders headers = response.getHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            try {
                return jsonMapper.writeValueAsString(objectResult);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        return Result.buildSuccess(body);
    }
}
