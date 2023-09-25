package top.cbaymax.matrix.silk.dashboard.facade.model.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import top.cbaymax.matrix.silk.dashboard.infrastructure.error.SilkError;

import java.io.Serializable;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> implements Serializable {

    public boolean success;

    public T data;

    public String errorCode;

    public String errorMessage;


    public Result(T data) {
        this.success = Boolean.TRUE;
        this.data = data;
    }

    public Result(SilkError error) {
        this.errorCode = error.code;
        this.errorMessage = error.message;
    }
}
