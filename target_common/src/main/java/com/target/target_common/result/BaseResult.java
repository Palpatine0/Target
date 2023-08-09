package com.target.target_common.result;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BaseResult<T> {
    private Integer code;
    private String message;
    private T data;

    //result:success
    public static <T> BaseResult<T> ok() {
        return new BaseResult(CodeEnum.SUCCESS.getCode(), CodeEnum.SUCCESS.getMessage(), null);
    }

    //result:success(with data)
    public static <T> BaseResult<T> ok(T data) {
        return new BaseResult(CodeEnum.SUCCESS.getCode(), CodeEnum.SUCCESS.getMessage(), data);
    }

}
