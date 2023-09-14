package top.cbaymax.matrix.silk.dashboard.facade.domain.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.cbaymax.matrix.silk.dashboard.infrastructure.error.ErrorCode;
import top.cbaymax.matrix.silk.dashboard.infrastructure.error.SilkError;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> implements Serializable {

    private boolean success;

    private T data;

    private String errorCode;

    private String errorMessage;

    public static <T> Result<T> buildSuccess(T data) {
        return new Result<>(true, data, null, null);
    }

    public static <T> Result<T> buildError(ErrorCode error) {
        return new Result<>(false, null, error.getCode(), error.getMessage());
    }

    public static <T> Result<T> buildError(SilkError error) {
        return new Result<>(false, null, error.getCode(), error.getMessage());
    }

}
