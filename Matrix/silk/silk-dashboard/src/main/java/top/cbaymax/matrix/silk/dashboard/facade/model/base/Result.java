package top.cbaymax.matrix.silk.dashboard.facade.model.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import top.cbaymax.matrix.silk.dashboard.infrastructure.error.SilkError;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> implements Serializable {

    public boolean success;

    @JsonInclude
    public T data;

    public String errorCode;

    public String errorMessage;

    public Result() {
        this.success = true;
    }

    public Result(T data) {
        this.success = Boolean.TRUE;
        this.data = data;
    }

    public Result(SilkError error) {
        this.errorCode = error.getCode();
        this.errorMessage = error.getMessage();
    }
}
