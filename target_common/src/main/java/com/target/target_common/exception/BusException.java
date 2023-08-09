package com.target.target_common.exception;

import com.target.target_common.result.CodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusException extends RuntimeException implements Serializable {
    private Integer code;
    private String message;

    public BusException(CodeEnum codeEnum){
        this.code=codeEnum.getCode();
        this.message=codeEnum.getMessage();
    }
}
